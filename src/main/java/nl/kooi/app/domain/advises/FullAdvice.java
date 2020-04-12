package nl.kooi.app.domain.advises;

import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.app.domain.game.Game;
import nl.kooi.infrastructure.entity.Outcome;
import nl.kooi.representation.advises.FullAdviceV1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FullAdvice {

    private String chipValue;
    private Collection<Outcome> outcomeList;
    private List<Game> gameArray;
    private BigDecimal totalProfit = new BigDecimal(0).setScale(2);

    public FullAdvice(String chipValue, Collection<Outcome> outcomeList) {
        this.chipValue = chipValue;
        this.outcomeList = outcomeList;

        gameArray = new ArrayList<>();
        gameArray.add(new OddEvenAdvice(chipValue, 8));
        gameArray.add(new RedBlackAdvice(chipValue, 8));
        gameArray.add(new HalfAdvice(chipValue, 8));
        gameArray.add(new DozenAdvice(chipValue));
        gameArray.add(new ColumnAdvice(chipValue));

        for (Outcome singleOutcome : outcomeList) {
                gameArray.forEach(g -> g.setHits(new CompoundRouletteOutcome(singleOutcome.getOutcome())));
            }
        }


    public FullAdviceV1 toRepresentationV1() {

        FullAdviceV1 representation = new FullAdviceV1();
        for (Game game : gameArray) {
            representation = toRepresentationHelper(game, representation);
        }
        return representation;

    }

    private static FullAdviceV1 toRepresentationHelper(Game game, FullAdviceV1 representation) {
        if (game instanceof DozenAdvice)
            representation.dozenAdvice = ((DozenAdvice) game).toRepresentationV1();
        if (game instanceof ColumnAdvice)
            representation.columnAdvice = ((ColumnAdvice) game).toRepresentationV1();
        if (game instanceof RedBlackAdvice)
            representation.redBlackAdvice = ((RedBlackAdvice) game).toRepresentationV1();
        if (game instanceof OddEvenAdvice)
            representation.oddEvenAdvice = ((OddEvenAdvice) game).toRepresentationV1();
        if (game instanceof HalfAdvice)
            representation.halfAdvice = ((HalfAdvice) game).toRepresentationV1();
        return representation;
    }

    public BigDecimal getTotalProfit(){
        gameArray.stream().forEach(g -> totalProfit = totalProfit.add(g.getProfit()));

        return totalProfit;
    }


}
