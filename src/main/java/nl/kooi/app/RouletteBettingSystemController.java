package nl.kooi.app;


import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.domain.CompoundRouletteOutcomeObject;
import nl.kooi.app.domain.advises.FullAdvice;
import nl.kooi.app.domain.metrics.SessionMetrics;
import nl.kooi.app.exceptions.NonExistingSessionException;
import nl.kooi.app.domain.model.Outcome;
import nl.kooi.app.domain.model.Session;
import nl.kooi.infrastructure.repository.OutcomeRepository;
import nl.kooi.infrastructure.repository.SessionRepository;
import nl.kooi.representation.advises.FullAdviceRepresentationV1;
import nl.kooi.representation.metrics.SessionMetricsRepresentationV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RequestMapping(path = "/roulette-betting-system/V1")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@RestController
public class RouletteBettingSystemController {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    OutcomeRepository outcomeRepository;

    private static CompoundRouletteOutcomeObject roulette = new CompoundRouletteOutcomeObject(0);

    private ArrayList<Integer> outcomeList = new ArrayList<>();

    @RequestMapping(path = "/{userId}/startgame", method = PUT, produces = "application/json")
    public ResponseEntity startGame(@PathVariable("userId") int userId, @RequestParam("chipvalue") String chipValue) {

        Session session = new Session();
        session.setChipValue(chipValue);
        session.setUserId(userId);
        return ResponseEntity.ok(sessionRepository.save(session));

    }

    @RequestMapping(path = "/{userId}/{sessionsId}/outcome", method = PUT, produces = "application/json")
    public FullAdviceRepresentationV1 setOutcome(@PathVariable("userId") Integer userId, @PathVariable("sessionsId") Integer sessionId, @RequestParam("outcome") int outcome) {

        roulette.setOutcome(outcome); // to validate outcome;

        Optional<Session> session = sessionRepository.findByIdAndUserId(sessionId, userId);
        if (!session.isPresent()) {
            throw new NonExistingSessionException("Session Id not found");
        }

        String chipValue = session.get().getChipValue();
        Outcome outcomes = new Outcome();
        outcomes.setSession(session.get());
        outcomes.setOutcome(outcome);
        outcomes.setTotalProfit("0");

        int id = outcomeRepository.save(outcomes).getId();

        Collection<Outcome> outcomeList = outcomeRepository.findBySessionIdOrderByIdAsc(sessionId);

        outcomes = outcomeRepository.findById(id).get();

        FullAdvice fullAdvice = new FullAdvice(chipValue, roulette, outcomeList);

        outcomes.setTotalProfit(fullAdvice.getTotalProfit().toString());

        outcomeRepository.deleteById(id);

        outcomeRepository.save(outcomes);

        outcomeList = outcomeRepository.findBySessionIdOrderByIdAsc(sessionId);

        synchronized (roulette) {
            return new FullAdvice(chipValue, roulette, outcomeList).toRepresentation();
        }
    }

    @RequestMapping(path = "/{userId}/{sessionsId}/metrics", method = GET, produces = "application/json")
    public SessionMetricsRepresentationV1 getMetrics(@PathVariable("userId") Integer userId, @PathVariable("sessionsId") Integer sessionId) {
        Optional<Session> session = sessionRepository.findByIdAndUserId(sessionId, userId);
        if (!session.isPresent()) {
            throw new NonExistingSessionException("Session Id not found");
        }
        return new SessionMetrics(outcomeRepository.findBySessionIdOrderByIdAsc(sessionId)).toRepresentation();

    }

    @RequestMapping(path = "/testrun", method = POST, produces = "application/json")
    public Long doTestRun(@RequestParam("numberOfRounds") int rounds) {

        Session session = new Session();
        session.setChipValue("1");
        session.setUserId(1234);

        int id = sessionRepository.save(session).getId();

        for (int i = 0; i < rounds; i++) {

            setOutcome(1234, id, (int) (Math.random() * 37));

        }

        return outcomeRepository.getLeastProfitAmount(id);

    }


}
