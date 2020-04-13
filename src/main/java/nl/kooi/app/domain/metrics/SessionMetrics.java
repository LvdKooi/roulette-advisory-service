package nl.kooi.app.domain.metrics;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.infrastructure.entity.OutcomeEntity;

import java.util.Collection;
import java.util.stream.Collectors;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.*;

@Slf4j
@Getter
public class SessionMetrics {
    private CompoundRouletteOutcome roulette;
    private int sessionId;
    private int outcomeId;
    private Collection<OutcomeEntity> outcomeEntities;
    private Collection<Double> profits;
    private long totalNumberOfRounds;
    private long totalFirstHalf;
    private long totalSecondHalf;
    private long totalOdd;
    private long totalEven;
    private long totalRed;
    private long totalBlack;
    private long totalZero;
    private long totalFirstColumn;
    private long totalSecondColumn;
    private long totalThirdColumn;
    private long totalFirstDozen;
    private long totalSecondDozen;
    private long totalThirdDozen;

    public SessionMetrics(Collection<OutcomeEntity> outcomeEntities) {
        this.outcomeEntities = outcomeEntities;
        totalNumberOfRounds = outcomeEntities.size();
        outcomeEntities.forEach(CounterHelper::new);
        sessionId = outcomeEntities.stream().findAny().get().getSession().getId();
        outcomeId = outcomeEntities.stream().min((a, b) -> b.getId() - a.getId()).get().getId();
        profits = outcomeEntities.stream().map(o -> Double.valueOf(o.getTotalProfit())).collect(Collectors.toList());
    }


    public class CounterHelper {

        public CounterHelper(OutcomeEntity outcomeEntity) {
            roulette = new CompoundRouletteOutcome(outcomeEntity.getOutcome());
            if (roulette.getOutcomeBooleanMap().get(ZERO)) {
                totalZero++;
            } else {
                totalBlack = roulette.getOutcomeBooleanMap().get(BLACK) ? ++totalBlack : totalBlack;
                totalRed = roulette.getOutcomeBooleanMap().get(RED) ? ++totalRed : totalRed;
                totalOdd = roulette.getOutcomeBooleanMap().get(ODD) ? ++totalOdd : totalOdd;
                totalEven = roulette.getOutcomeBooleanMap().get(EVEN) ? ++totalEven : totalEven;
                totalFirstHalf = roulette.getOutcomeBooleanMap().get(FIRST_HALF) ? ++totalFirstHalf : totalFirstHalf;
                totalSecondHalf = roulette.getOutcomeBooleanMap().get(SECOND_HALF) ? ++totalSecondHalf : totalSecondHalf;
                totalFirstDozen = roulette.getOutcomeBooleanMap().get(FIRST_DOZEN) ? ++totalFirstDozen : totalFirstDozen;
                totalSecondDozen = roulette.getOutcomeBooleanMap().get(SECOND_DOZEN) ? ++totalSecondDozen : totalSecondDozen;
                totalThirdDozen = roulette.getOutcomeBooleanMap().get(THIRD_DOZEN) ? ++totalThirdDozen : totalThirdDozen;
                totalFirstColumn = roulette.getOutcomeBooleanMap().get(FIRST_COLUMN) ? ++totalFirstColumn : totalFirstColumn;
                totalSecondColumn = roulette.getOutcomeBooleanMap().get(SECOND_COLUMN) ? ++totalSecondColumn : totalSecondColumn;
                totalThirdColumn = roulette.getOutcomeBooleanMap().get(THIRD_COLUMN) ? ++totalThirdColumn : totalThirdColumn;
            }
        }
    }

}