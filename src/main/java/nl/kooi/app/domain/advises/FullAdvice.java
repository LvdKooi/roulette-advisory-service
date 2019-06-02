package nl.kooi.app.domain.advises;

import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.domain.advises.game.Game;
import nl.kooi.app.domain.advises.game.roulette.rouletteonetoone.HalfAdvice;
import nl.kooi.app.domain.advises.game.roulette.rouletteonetoone.OddEvenAdvice;
import nl.kooi.app.domain.advises.game.roulette.rouletteonetoone.RedBlackAdvice;
import nl.kooi.app.domain.advises.game.roulette.roulettetwotoone.DozenAdvice;
import nl.kooi.app.domain.advises.game.roulette.roulettetwotoone.ColumnAdvice;
import nl.kooi.app.domain.model.Outcome;
import nl.kooi.representation.advises.FullAdviceRepresentation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FullAdvice {

    private String chipValue;
    private RouletteDomainObject roulette;
    private Collection<Outcome> outcomeList;
    private List<Game> gameArray;
    private BigDecimal totalProfit = new BigDecimal(0).setScale(2);

    public FullAdvice(String chipValue, RouletteDomainObject roulette, Collection<Outcome> outcomeList) {
        this.chipValue = chipValue;
        this.roulette = roulette;
        this.outcomeList = outcomeList;

        gameArray = new ArrayList<>();
        gameArray.add(new OddEvenAdvice(chipValue, 'D', 8));
        gameArray.add(new RedBlackAdvice(chipValue, 'D', 8));
        gameArray.add(new HalfAdvice(chipValue, 'D', 8));
        gameArray.add(new DozenAdvice(chipValue, 'D'));
        gameArray.add(new ColumnAdvice(chipValue, 'D'));

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
        if (game instanceof DozenAdvice)
            representation.dozenAdvice = ((DozenAdvice) game).toRepresentation();
        if (game instanceof ColumnAdvice)
            representation.columnAdvice = ((ColumnAdvice) game).toRepresentation();
        if (game instanceof RedBlackAdvice)
            representation.redBlackAdvice = ((RedBlackAdvice) game).toRepresentation();
        if (game instanceof OddEvenAdvice)
            representation.oddEvenAdvice = ((OddEvenAdvice) game).toRepresentation();
        if (game instanceof HalfAdvice)
            representation.halfAdvice = ((HalfAdvice) game).toRepresentation();
        return representation;
    }

    public BigDecimal getTotalProfit(){
        gameArray.stream().forEach(g -> totalProfit = totalProfit.add(g.getProfit()));

        return totalProfit;
    }


}
