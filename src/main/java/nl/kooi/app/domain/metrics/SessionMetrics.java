package nl.kooi.app.domain.metrics;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.domain.Mapper;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.infrastructure.entity.OutcomeEntity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Getter
public class SessionMetrics {
    private Outcome roulette;
    private int sessionId;
    private int outcomeId;
    private Collection<OutcomeEntity> outcomeEntities;
    private Collection<BigDecimal> profits;
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
        profits = outcomeEntities.stream().map(OutcomeEntity::getTotalProfit).collect(Collectors.toList());
    }


    public class CounterHelper {

        public CounterHelper(OutcomeEntity outcomeEntity) {
            roulette = Mapper.map(outcomeEntity);
            if (roulette.getZero()) {
                totalZero++;
            } else {
                totalBlack = roulette.getBlack() ? ++totalBlack : totalBlack;
                totalRed = roulette.getRed() ? ++totalRed : totalRed;
                totalOdd = roulette.getOdd() ? ++totalOdd : totalOdd;
                totalEven = roulette.getEven() ? ++totalEven : totalEven;
                totalFirstHalf = roulette.getFirstHalf() ? ++totalFirstHalf : totalFirstHalf;
                totalSecondHalf = roulette.getSecondHalf() ? ++totalSecondHalf : totalSecondHalf;
                totalFirstDozen = roulette.getFirstDozen() ? ++totalFirstDozen : totalFirstDozen;
                totalSecondDozen = roulette.getSecondDozen() ? ++totalSecondDozen : totalSecondDozen;
                totalThirdDozen = roulette.getThirdDozen() ? ++totalThirdDozen : totalThirdDozen;
                totalFirstColumn = roulette.getFirstColumn() ? ++totalFirstColumn : totalFirstColumn;
                totalSecondColumn = roulette.getSecondColumn() ? ++totalSecondColumn : totalSecondColumn;
                totalThirdColumn = roulette.getThirdColumn() ? ++totalThirdColumn : totalThirdColumn;
            }
        }
    }

}