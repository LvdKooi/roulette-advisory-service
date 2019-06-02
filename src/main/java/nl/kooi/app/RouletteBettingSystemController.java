package nl.kooi.app;


import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.domain.advises.FullAdvice;
import nl.kooi.app.exceptions.NonExistingSessionException;
import nl.kooi.app.domain.model.Outcome;
import nl.kooi.app.domain.model.Session;
import nl.kooi.infrastructure.repository.OutcomeRepository;
import nl.kooi.infrastructure.repository.SessionRepository;
import nl.kooi.representation.RouletteOutcome;
import nl.kooi.representation.RouletteRepresentationObject;
import nl.kooi.representation.advises.FullAdviceRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Slf4j
@RequestMapping(path = "/roulette-betting-system")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@RestController
public class RouletteBettingSystemController {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    OutcomeRepository outcomeRepository;

    private static RouletteDomainObject roulette = new RouletteDomainObject(0);

    private ArrayList<Integer> outcomeList = new ArrayList<>();

    @RequestMapping(path = "/{userId}/startgame", method = PUT, produces = "application/json")
    public ResponseEntity ResponseEntity(@PathVariable("userId") int userId, @RequestParam("chipvalue") String chipValue) {

        Session session = new Session();
        session.setChipValue(chipValue);
        session.setUserId(userId);
        return ResponseEntity.ok(sessionRepository.save(session));

    }

    @RequestMapping(path = "/{userId}/{sessionsId}/outcome", method = PUT, produces = "application/json")
    public FullAdviceRepresentation setOutcome(@PathVariable("userId") Integer userId, @PathVariable("sessionsId") Integer sessionId, @RequestParam("outcome") int outcome) {

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


}
