package nl.kooi.app.api;


import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.advises.FullAdviceDto;
import nl.kooi.app.api.dto.metrics.SessionMetricsDto;
import nl.kooi.app.domain.advises.FullAdvice;
import nl.kooi.app.domain.metrics.SessionMetrics;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.services.OutcomeService;
import nl.kooi.app.exceptions.SessionNotFoundException;
import nl.kooi.infrastructure.entity.OutcomeEntity;
import nl.kooi.infrastructure.entity.SessionEntity;
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

    @Autowired
    OutcomeService outcomeService;

    private ArrayList<Integer> outcomeList = new ArrayList<>();

    @RequestMapping(path = "/{userId}/startgame", method = PUT, produces = "application/json")
    public ResponseEntity startGame(@PathVariable("userId") int userId, @RequestParam("chipvalue") String chipValue) {

        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setChipValue(chipValue);
        sessionEntity.setUserId(userId);
        return ResponseEntity.ok(sessionRepository.save(sessionEntity));

    }

    @RequestMapping(path = "/{userId}/sessions/{sessionsId}/outcomes/", method = PUT, produces = "application/json")
    public FullAdviceDto setOutcome(@PathVariable("userId") Integer userId, @PathVariable("sessionsId") Integer sessionId, @RequestParam("outcome") int outcome) {

        Outcome.validateOutcome(outcome);

        Optional<SessionEntity> session = sessionRepository.findByIdAndUserId(sessionId, userId);
        if (!session.isPresent()) {
            throw new SessionNotFoundException("Session Id not found");
        }

        String chipValue = session.get().getChipValue();
        OutcomeEntity outcomes = new OutcomeEntity();
        outcomes.setSession(session.get());
        outcomes.setOutcome(outcome);
        outcomes.setTotalProfit("0");

        int id = outcomeService.saveOutcome(sessionId, "0", outcome);

        LOGGER.info("Id of outcome is: {}", id);

        Collection<Outcome> outcomeList = outcomeService.findBySessionIdOrderByIdAsc(sessionId);
        System.out.println(outcomeList);
        outcomes = outcomeRepository.findById(id).get();

        FullAdvice fullAdvice = new FullAdvice(chipValue, outcomeList);

        outcomes.setTotalProfit(fullAdvice.getTotalProfit().toString());

        outcomeRepository.deleteById(id);

        outcomeRepository.save(outcomes);

        outcomeList = outcomeService.findBySessionIdOrderByIdAsc(sessionId);
        System.out.println(Mapper.map(fullAdvice));
        return Mapper.map(new FullAdvice(chipValue, outcomeList));

    }

    @RequestMapping(path = "/{userId}/sessions/{sessionsId}/metrics/", method = GET, produces = "application/json")
    public SessionMetricsDto getMetrics(@PathVariable("userId") Integer userId, @PathVariable("sessionsId") Integer sessionId) {
        Optional<SessionEntity> session = sessionRepository.findByIdAndUserId(sessionId, userId);
        if (!session.isPresent()) {
            throw new SessionNotFoundException("Session Id not found");
        }
        return Mapper.map(new SessionMetrics(outcomeRepository.findBySessionIdOrderByIdAsc(sessionId)));

    }

    @RequestMapping(path = "/testrun", method = POST, produces = "application/json")
    public SessionMetricsDto doTestRun(@RequestParam("numberOfRounds") int rounds) {

        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setChipValue("1");
        sessionEntity.setUserId(1234);

        int id = sessionRepository.save(sessionEntity).getId();

        LOGGER.info("Id of testsession is: {}", id);
        LOGGER.info("UserId of testsession is: {}", "1234");

        for (int i = 0; i < rounds; i++) {

            setOutcome(1234, id, (int) (Math.random() * 37));

        }

        return getMetrics(1234, id);

    }


}
