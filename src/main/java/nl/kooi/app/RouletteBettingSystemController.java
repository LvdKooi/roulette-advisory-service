package nl.kooi.app;

import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.domain.advises.Game.Game;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.Halfs;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.OddEven;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.RedBlack;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne.DozenGame;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne.RowGame;
import nl.kooi.representation.Outcome;
import nl.kooi.representation.RouletteRepresentationObject;
import nl.kooi.representation.advises.FullAdviceRepresentation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RequestMapping(path = "/roulette-betting-system")
@RestController
public class RouletteBettingSystemController {
    private Game[] gameArray;
    private static RouletteDomainObject rouletteDomainObject = new RouletteDomainObject(0);

    @RequestMapping(path = "/startgame", method = PUT, produces = "application/json")
    public void startGame(@RequestParam("chipvalue") String chipValue) {
        gameArray = new Game[]{new OddEven(chipValue, 'D', 4, rouletteDomainObject), new RedBlack(chipValue, 'D', 4, rouletteDomainObject), new Halfs(chipValue, 'D', 4, rouletteDomainObject), new DozenGame(chipValue, 'D', rouletteDomainObject), new RowGame(chipValue, 'D', rouletteDomainObject)};
    }

    @RequestMapping(path = "/startgame/outcome", method = PUT, produces = "application/json")
    public void setOutcome(@RequestParam("outcome") int outcome) {
        for (Game game : gameArray) {
            game.setHits(outcome);
        }
    }

    @RequestMapping(path = "/startgame/advice", method = GET, produces = "application/json")
    public FullAdviceRepresentation getAdvice() {
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
