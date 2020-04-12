package nl.kooi.app.api;


import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.advises.FullAdviceDto;
import nl.kooi.app.api.dto.metrics.SessionMetricsDto;
import nl.kooi.app.domain.advises.FullAdvice;
import nl.kooi.app.domain.metrics.SessionMetrics;
import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.app.exceptions.SessionNotFoundException;
import nl.kooi.infrastructure.entity.Outcome;
import nl.kooi.infrastructure.entity.Session;
import nl.kooi.infrastructure.repository.OutcomeRepository;
import nl.kooi.infrastructure.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@Slf4j
@RequestMapping(path = "/roulette-betting-system")
@RestController
public class RouletteBettingSystemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouletteBettingSystemController.class.getName());

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    OutcomeRepository outcomeRepository;

    private ArrayList<Integer> outcomeList = new ArrayList<>();

    @RequestMapping(path = "/{userId}/startgame", method = PUT, produces = "application/json")
    public ResponseEntity startGame(@PathVariable("userId") int userId, @RequestParam("chipvalue") String chipValue) {

        Session session = new Session();
        session.setChipValue(chipValue);
        session.setUserId(userId);
        return ResponseEntity.ok(sessionRepository.save(session));

    }

    @RequestMapping(path = "/{userId}/sessions/{sessionsId}/outcomes/", method = PUT, produces = "application/json")
    public FullAdviceDto setOutcome(@PathVariable("userId") Integer userId, @PathVariable("sessionsId") Integer sessionId, @RequestParam("outcome") int outcome) {

        CompoundRouletteOutcome.validateOutcome(outcome);

        Optional<Session> session = sessionRepository.findByIdAndUserId(sessionId, userId);
        if (!session.isPresent()) {
            throw new SessionNotFoundException("Session Id not found");
        }

        String chipValue = session.get().getChipValue();
        Outcome outcomes = new Outcome();
        outcomes.setSession(session.get());
        outcomes.setOutcome(outcome);
        outcomes.setTotalProfit("0");

        int id = outcomeRepository.save(outcomes).getId();

        LOGGER.info("Id of outcome is: {}", id);

        Collection<Outcome> outcomeList = outcomeRepository.findBySessionIdOrderByIdAsc(sessionId);

        outcomes = outcomeRepository.findById(id).get();

        FullAdvice fullAdvice = new FullAdvice(chipValue, outcomeList);

        outcomes.setTotalProfit(fullAdvice.getTotalProfit().toString());

        outcomeRepository.deleteById(id);

        outcomeRepository.save(outcomes);

        outcomeList = outcomeRepository.findBySessionIdOrderByIdAsc(sessionId);

        return Mapper.map(new FullAdvice(chipValue, outcomeList));

    }

    @RequestMapping(path = "/{userId}/sessions/{sessionsId}/metrics/", method = GET, produces = "application/json")
    public SessionMetricsDto getMetrics(@PathVariable("userId") Integer userId, @PathVariable("sessionsId") Integer sessionId) {
        Optional<Session> session = sessionRepository.findByIdAndUserId(sessionId, userId);
        if (!session.isPresent()) {
            throw new SessionNotFoundException("Session Id not found");
        }
        return Mapper.map(new SessionMetrics(outcomeRepository.findBySessionIdOrderByIdAsc(sessionId)));

    }

    @RequestMapping(path = "/testrun", method = POST, produces = "application/json")
    public SessionMetricsDto doTestRun(@RequestParam("numberOfRounds") int rounds) {

        Session session = new Session();
        session.setChipValue("1");
        session.setUserId(1234);

        int id = sessionRepository.save(session).getId();

        LOGGER.info("Id of testsession is: {}", id);
        LOGGER.info("UserId of testsession is: {}", "1234");

        for (int i = 0; i < rounds; i++) {

            setOutcome(1234, id, (int) (Math.random() * 37));

        }

        return getMetrics(1234, id);

    }


}
