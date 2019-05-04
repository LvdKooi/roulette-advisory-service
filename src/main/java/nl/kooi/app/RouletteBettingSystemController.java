package nl.kooi.app;

import com.sun.media.jfxmedia.logging.Logger;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sun.media.jfxmedia.logging.Logger.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RequestMapping(path = "/roulette-betting-system")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@RestController
public class RouletteBettingSystemController {

    private Game gameArray[] =
            {new OddEven("0", 'D', 4),
            new RedBlack("0", 'D', 4),
            new Halfs("0", 'D', 4),
            new DozenGame("0", 'D'),
            new RowGame("0", 'D')};

    private RouletteDomainObject roulette = new RouletteDomainObject(0);

    @RequestMapping(path = "/startgame", method = PUT, produces = "application/json")
    public void startGame(@RequestParam("chipvalue") String chipValue) {
        for (Game game : gameArray) {
            game.setChipValue(chipValue);
        }
    }

    @RequestMapping(path = "/startgame/outcome", method = PUT, produces = "application/json")
    public void setOutcome(@RequestParam("outcome") int outcome) {
        roulette.setOutcome(outcome);
        for (Game game : gameArray) {
            game.setHits(roulette);
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
