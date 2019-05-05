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

import java.util.ArrayList;

import static com.sun.media.jfxmedia.logging.Logger.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RequestMapping(path = "/roulette-betting-system")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@RestController
public class RouletteBettingSystemController {


    private String chipValue;

    private RouletteDomainObject roulette = new RouletteDomainObject(0);

    private ArrayList<Integer> outcomeList = new ArrayList();

    @RequestMapping(path = "/startgame", method = PUT, produces = "application/json")
    public void startGame(@RequestParam("chipvalue") String chipValue) {
        this.chipValue = chipValue;
    }

    @RequestMapping(path = "/startgame/outcome", method = PUT, produces = "application/json")
    public FullAdviceRepresentation setOutcome(@RequestParam("outcome") int outcome) {
        outcomeList.add(outcome);

        Game gameArray[] =
                {new OddEven(chipValue, 'D', 4),
                        new RedBlack(chipValue, 'D', 4),
                        new Halfs(chipValue, 'D', 4),
                        new DozenGame(chipValue, 'D'),
                        new RowGame(chipValue, 'D')};

        for(int singleOutcome : outcomeList) {
            roulette.setOutcome(singleOutcome);
            for (Game game : gameArray) {
                game.setHits(roulette);
            }
        }

        FullAdviceRepresentation representation = new FullAdviceRepresentation();
        for (Game game : gameArray) {
            representation = toRepresentationHelper(game, representation);
        }
        return representation;

    }

//    @RequestMapping(path = "/startgame/advice", method = GET, produces = "application/json")
//    public FullAdviceRepresentation getAdvice() {
//        FullAdviceRepresentation representation = new FullAdviceRepresentation();
//        for (Game game : gameArray) {
//            representation = toRepresentationHelper(game, representation);
//        }
//        return representation;
//    }

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