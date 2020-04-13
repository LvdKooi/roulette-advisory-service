package nl.kooi.app.domain.advises;

import lombok.Getter;
import nl.kooi.app.domain.game.Game;
import nl.kooi.app.domain.outcome.Outcome;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FullAdvice {

    private String chipValue;
    private Collection<Outcome> outcomeList;
    @Getter
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
            gameArray.forEach(g -> g.setHits(singleOutcome));
        }
    }

    public BigDecimal getTotalProfit() {
        gameArray.forEach(g -> totalProfit = totalProfit.add(g.getProfit()));
        return totalProfit;
    }

}
