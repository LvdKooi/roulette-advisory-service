package nl.kooi.app.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.api.dto.AdviceDto;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.SessionMetricsDto;
import nl.kooi.app.domain.service.OutcomeAdviceService;
import nl.kooi.app.exception.NotFoundException;
import nl.kooi.app.persistence.entity.SessionEntity;
import nl.kooi.app.persistence.repository.SessionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RequestMapping(path = "/roulette-betting-system")
@RestController
@Slf4j
@RequiredArgsConstructor
public class RouletteBettingSystemController {

    private final SessionRepository sessionRepository;

    private final OutcomeAdviceService outcomeAdviceService;

    @RequestMapping(path = "/{userId}/startgame", method = PUT, produces = "application/json")
    public ResponseEntity<SessionEntity> startGame(@PathVariable("userId") int userId, @RequestParam("chipvalue") String chipValue) {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setChipValue(new BigDecimal(chipValue));
        sessionEntity.setUserId(userId);
        return ResponseEntity.ok(sessionRepository.save(sessionEntity));
    }

    @RequestMapping(path = "/{userId}/sessions/{sessionsId}/outcomes/", method = PUT, produces = "application/json")
    public AdviceDto setOutcome(@PathVariable("userId") Integer userId, @PathVariable("sessionsId") Integer sessionId, @RequestParam("outcome") int outcome) {

        outcomeAdviceService.saveOutcomeAndAdvise(userId, sessionId, outcome);

        return Mapper.map(outcomeAdviceService.findLastAdvice(sessionId));

    }

    @RequestMapping(path = "/{userId}/sessions/{sessionsId}/metrics/", method = GET, produces = "application/json")
    public SessionMetricsDto getMetrics(@PathVariable("userId") Integer userId, @PathVariable("sessionsId") Integer sessionId) {
        Optional<SessionEntity> session = sessionRepository.findByIdAndUserId(sessionId, userId);
        if (session.isEmpty()) {
            throw new NotFoundException("Session Id not found");
        }
        return Mapper.map(outcomeAdviceService.getSessionsMetrics(sessionId));

    }

    @RequestMapping(path = "/testrun", method = POST, produces = "application/json")
    public SessionMetricsDto doTestRun(@RequestParam("numberOfRounds") int rounds) {

        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setChipValue(BigDecimal.ONE);
        sessionEntity.setUserId(1234);

        int id = sessionRepository.save(sessionEntity).getId();

        log.info("Id of testsession is: {}", id);
        log.info("UserId of testsession is: {}", "1234");

        for (int i = 0; i < rounds; i++) {

            setOutcome(1234, id, (int) (Math.random() * 37));

        }

        return getMetrics(1234, id);

    }


}
