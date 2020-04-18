package nl.kooi.app.domain.advises;

import lombok.Getter;
import nl.kooi.app.domain.game.*;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.*;

public class FullAdvice {
    @Getter
    private List<Game> gameArray;
    private BigDecimal totalProfit = new BigDecimal(0).setScale(2);
    @Getter
    private Map<RouletteOutcome, BigDecimal> adviceMap;

    public FullAdvice(String chipValue, Collection<Outcome> outcomeList) {
        gameArray = new ArrayList<>();
        gameArray.add(new OddEvenGame(chipValue));
        gameArray.add(new RedBlackGame(chipValue));
        gameArray.add(new HalfGame(chipValue));
        gameArray.add(new DozenGame(chipValue));
        gameArray.add(new ColumnGame(chipValue));

        adviceMap = new HashMap<>();
        for (Outcome singleOutcome : outcomeList) {
            System.out.println(singleOutcome);
            gameArray.forEach(g -> g.setHits(singleOutcome));
        }

        gameArray.forEach(x -> adviceMap.putAll(x.getAdvice()));
    }


    public BigDecimal getTotalProfit() {
        gameArray.forEach(g -> totalProfit = totalProfit.add(g.getProfit()));
        return totalProfit;
    }

}
