package nl.kooi.app.domain.advises;

import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.domain.advises.Game.Game;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.Halfs;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.OddEven;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.RedBlack;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne.DozenGame;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne.RowGame;
import nl.kooi.app.domain.model.Outcome;
import nl.kooi.representation.advises.FullAdviceRepresentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FullAdvice {

    private String chipValue;
    private RouletteDomainObject roulette;
    private Collection<Outcome> outcomeList;
    private List<Game> gameArray;

    public FullAdvice(String chipValue, RouletteDomainObject roulette, Collection<Outcome> outcomeList) {
        this.chipValue = chipValue;
        this.roulette = roulette;
        this.outcomeList = outcomeList;

        gameArray = new ArrayList<>();
        gameArray.add(new OddEven(chipValue, 'D', 4));
        gameArray.add(new RedBlack(chipValue, 'D', 4));
        gameArray.add(new Halfs(chipValue, 'D', 4));
        gameArray.add(new DozenGame(chipValue, 'D'));
        gameArray.add(new RowGame(chipValue, 'D'));

        for (Outcome singleOutcome : outcomeList) {
            roulette.setOutcome(singleOutcome.getOutcome());
           gameArray.stream().forEach(g -> g.setHits(roulette));
            }
        }

    public FullAdviceRepresentation toRepresentation() {

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
