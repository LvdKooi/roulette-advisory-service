package nl.kooi.app;


import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.domain.advises.Game.Game;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.Halfs;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.OddEven;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.RedBlack;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne.DozenGame;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne.RowGame;
import nl.kooi.app.exceptions.nonExistingSessionsException;
import nl.kooi.infrastructure.model.Outcome;
import nl.kooi.infrastructure.model.Session;
import nl.kooi.infrastructure.repository.OutcomeRepository;
import nl.kooi.infrastructure.repository.SessionRepository;
import nl.kooi.representation.advises.FullAdviceRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
        if( !session.isPresent()){
            throw new nonExistingSessionsException("Session Id not found");
        }




        Outcome outcomes = new Outcome();
        outcomes.setSession(session.get());
        outcomes.setOutcome(outcome);

        outcomeRepository.save(outcomes);

        Collection<Outcome> outcomeList = outcomeRepository.findBySessionIdOrderByIdAsc(sessionId);


        String chipValue = session.get().getChipValue();

        Game gameArray[] =
                {new OddEven(chipValue, 'D', 4),
                        new RedBlack(chipValue, 'D', 4),
                        new Halfs(chipValue, 'D', 4),
                        new DozenGame(chipValue, 'D'),
                        new RowGame(chipValue, 'D')};

        synchronized (roulette) { // thread-safe usage of the roulette object
            for (Outcome singleOutcome : outcomeList) {

                roulette.setOutcome(singleOutcome.getOutcome());
                System.out.println(singleOutcome);
                for (Game game : gameArray) {
                    game.setHits(roulette);
                }
            }
        }

        FullAdviceRepresentation representation = new FullAdviceRepresentation();
        for (Game game : gameArray) {
            representation = toRepresentationHelper(game, representation);
        }
        return representation;

    }


    private static FullAdviceRepresentation toRepresentationHelper(Game game, FullAdviceRepresentation representation) {
        if (game instanceof DozenGame)
            representation.dozenAdvice = ((DozenGame) game).toRepresentation();
        if (game instanceof RowGame)
            representation.rowAdvice = ((RowGame) game).toRepresentation();
        if (game instanceof RedBlack)
            representation.redBlackAdvice = ((RedBlack) game).toRepresentation();
        if (game instanceof OddEven)
            representation.oddEvenAdvice = ((OddEven) game).toRepresentation();
        if (game instanceof Halfs)
            representation.halfAdvice = ((Halfs) game).toRepresentation();
        return representation;
    }

}
