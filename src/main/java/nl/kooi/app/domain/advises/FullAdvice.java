package nl.kooi.app.domain.advises;

import lombok.Getter;
import nl.kooi.app.domain.game.*;
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
        gameArray.add(new OddEvenGame(chipValue));
        gameArray.add(new RedBlackGame(chipValue));
        gameArray.add(new HalfGame(chipValue));
        gameArray.add(new DozenGame(chipValue));
        gameArray.add(new ColumnGame(chipValue));

        for (Outcome singleOutcome : outcomeList) {
            gameArray.forEach(g -> g.setHits(singleOutcome));
        }
    }

    public BigDecimal getTotalProfit() {
        gameArray.forEach(g -> totalProfit = totalProfit.add(g.getProfit()));
        return totalProfit;
    }

}
