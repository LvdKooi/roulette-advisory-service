package nl.kooi.app.api;


import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.advises.FullAdviseDto;
import nl.kooi.app.api.dto.metrics.SessionMetricsDto;
import nl.kooi.app.domain.metrics.SessionMetrics;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.services.OutcomeService;
import nl.kooi.app.exceptions.SessionNotFoundException;
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

import java.math.BigDecimal;
import java.util.ArrayList;
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
        sessionEntity.setChipValue(new BigDecimal(chipValue));
        sessionEntity.setUserId(userId);
        return ResponseEntity.ok(sessionRepository.save(sessionEntity));

    }

    @RequestMapping(path = "/{userId}/sessions/{sessionsId}/outcomes/", method = PUT, produces = "application/json")
    public FullAdviseDto setOutcome(@PathVariable("userId") Integer userId, @PathVariable("sessionsId") Integer sessionId, @RequestParam("outcome") int outcome) {

        Outcome.validateOutcome(outcome);

        outcomeService.saveOutcomeAndAdvise(userId, sessionId, outcome);

        return Mapper.map(outcomeService.findLastAdvice(sessionId));

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
        sessionEntity.setChipValue(BigDecimal.ONE);
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
