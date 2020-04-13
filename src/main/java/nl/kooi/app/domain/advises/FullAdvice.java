package nl.kooi.app.domain.advises;

import lombok.Getter;
import nl.kooi.app.domain.game.Game;
import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.infrastructure.entity.OutcomeEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FullAdvice {

    private String chipValue;
    private Collection<OutcomeEntity> outcomeEntityList;
    @Getter
    private List<Game> gameArray;
    private BigDecimal totalProfit = new BigDecimal(0).setScale(2);

    public FullAdvice(String chipValue, Collection<OutcomeEntity> outcomeEntityList) {
        this.chipValue = chipValue;
        this.outcomeEntityList = outcomeEntityList;

        gameArray = new ArrayList<>();
        gameArray.add(new OddEvenAdvice(chipValue, 8));
        gameArray.add(new RedBlackAdvice(chipValue, 8));
        gameArray.add(new HalfAdvice(chipValue, 8));
        gameArray.add(new DozenAdvice(chipValue));
        gameArray.add(new ColumnAdvice(chipValue));

        for (OutcomeEntity singleOutcomeEntity : outcomeEntityList) {
            gameArray.forEach(g -> g.setHits(new CompoundRouletteOutcome(singleOutcomeEntity.getOutcome())));
        }
    }

    public BigDecimal getTotalProfit() {
        gameArray.forEach(g -> totalProfit = totalProfit.add(g.getProfit()));
        return totalProfit;
    }

}
