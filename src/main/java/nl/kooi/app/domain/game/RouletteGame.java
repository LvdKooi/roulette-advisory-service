package nl.kooi.app.domain.game;

import lombok.Getter;
import nl.kooi.app.domain.advice.Advice;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class RouletteGame {
    private List<Game> gameArray;
    private BigDecimal totalProfit = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
    @Getter
    private Map<RouletteOutcome, BigDecimal> adviceMap;

    public RouletteGame(BigDecimal chipValue) {
        gameArray = new ArrayList<>();
        gameArray.add(new OddEvenGame(chipValue));
        gameArray.add(new RedBlackGame(chipValue));
        gameArray.add(new HalfGame(chipValue));
        gameArray.add(new DozenGame(chipValue));
        gameArray.add(new ColumnGame(chipValue));
    }

    public void setHits(List<Outcome> outcomeList) {
        for (Outcome singleOutcome : outcomeList) {
            gameArray.forEach(g -> g.setHits(singleOutcome));
        }
    }

    public Advice getAdvise() {
        NavigableMap<RouletteOutcome, BigDecimal> adviceMap = new TreeMap<>();

        gameArray.forEach(game -> adviceMap.putAll(game.getAdvice()));

        return new Advice(adviceMap);
    }

    public BigDecimal getTotalProfit() {
        gameArray.forEach(game -> totalProfit = totalProfit.add(game.getProfit()));
        return totalProfit;
    }

}
