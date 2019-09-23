package nl.kooi.app.domain.metrics;

import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.domain.CompoundRouletteOutcome;
import nl.kooi.app.domain.model.Outcome;
import nl.kooi.representation.RouletteOutcome;
import nl.kooi.representation.metrics.SessionMetricsV1;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import static java.math.RoundingMode.HALF_UP;

@Slf4j
public class SessionMetrics {
    private CompoundRouletteOutcome roulette;
    private int sessionId;
    private int outcomeId;
    private Collection<Outcome> outcomes;
    private Collection<Double> profits;
    private long totalRounds;
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

    public SessionMetrics(Collection<Outcome> outcomes) {
        this.outcomes = outcomes;
        this.roulette = new CompoundRouletteOutcome(0);
        totalRounds = outcomes.stream().count();
        outcomes.forEach(outcome -> new CounterHelper(outcome));
        sessionId = outcomes.stream().findAny().get().getSession().getId();
        outcomeId = outcomes.stream().sorted((a, b) -> b.getId() - a.getId()).findFirst().get().getId();
        profits = outcomes.stream().map(o -> Double.valueOf(o.getTotalProfit())).collect(Collectors.toList());
    }


    public class CounterHelper {

        public CounterHelper(Outcome outcome) {
            roulette.setOutcome(outcome.getOutcome());
            if (roulette.isZero()) {
                totalZero++;
            } else {
                totalBlack = roulette.getRedBlack() == RouletteOutcome.BLACK ? ++totalBlack : totalBlack;
                totalRed = roulette.getRedBlack() == RouletteOutcome.RED ? ++totalRed : totalRed;
                totalOdd = roulette.getOddEven() == RouletteOutcome.ODD ? ++totalOdd : totalOdd;
                totalEven = roulette.getOddEven() == RouletteOutcome.EVEN ? ++totalEven : totalEven;
                totalFirstHalf = roulette.getHalf() == RouletteOutcome.FIRST ? ++totalFirstHalf : totalFirstHalf;
                totalSecondHalf = roulette.getHalf() == RouletteOutcome.SECOND ? ++totalSecondHalf : totalSecondHalf;
                totalFirstDozen = roulette.getDozen() == RouletteOutcome.FIRST ? ++totalFirstDozen : totalFirstDozen;
                totalSecondDozen = roulette.getDozen() == RouletteOutcome.SECOND ? ++totalSecondDozen : totalSecondDozen;
                totalThirdDozen = roulette.getDozen() == RouletteOutcome.THIRD ? ++totalThirdDozen : totalThirdDozen;
                totalFirstColumn = roulette.getColumn() == RouletteOutcome.FIRST ? ++totalFirstColumn : totalFirstColumn;
                totalSecondColumn = roulette.getColumn() == RouletteOutcome.SECOND ? ++totalSecondColumn : totalSecondColumn;
                totalThirdColumn = roulette.getColumn() == RouletteOutcome.THIRD ? ++totalThirdColumn : totalThirdColumn;
            }
        }
    }

    public SessionMetricsV1 toRepresentationV1() {
        SessionMetricsV1 representation = new SessionMetricsV1();
        representation.totalNumberOfRounds = totalRounds;
        Assert.isTrue(totalRounds > 0, "Total rounds in SessionMetrics is corrupt or no outcomes have been recorded yet");
        representation.redBlackMetrics.percentageBlack = roundsToPercentage(totalBlack, totalRounds);
        representation.redBlackMetrics.percentageRed = roundsToPercentage(totalRed, totalRounds);
        representation.oddEvenMetrics.percentageOdd = roundsToPercentage(totalOdd, totalRounds);
        representation.oddEvenMetrics.percentageEven = roundsToPercentage(totalEven, totalRounds);
        representation.halfMetrics.percentageFirstHalf = roundsToPercentage(totalFirstHalf, totalRounds);
        representation.halfMetrics.percentageSecondHalf = roundsToPercentage(totalSecondHalf, totalRounds);
        representation.dozenMetrics.percentageFirstDozen = roundsToPercentage(totalFirstDozen, totalRounds);
        representation.dozenMetrics.percentageSecondDozen = roundsToPercentage(totalSecondDozen, totalRounds);
        representation.dozenMetrics.percentageThirdDozen = roundsToPercentage(totalThirdDozen, totalRounds);
        representation.columnMetrics.percentageFirstColumn = roundsToPercentage(totalFirstColumn, totalRounds);
        representation.columnMetrics.percentageSecondColumn = roundsToPercentage(totalSecondColumn, totalRounds);
        representation.columnMetrics.percentageThirdColumn = roundsToPercentage(totalThirdColumn, totalRounds);
        representation.percentageZero = roundsToPercentage(totalZero, totalRounds);
        representation.currentProfit = new BigDecimal(outcomes.stream().filter(o -> o.getId() == outcomeId).findFirst().get().getTotalProfit());
        representation.leastProfit = new BigDecimal(profits.stream().min(Comparator.naturalOrder()).get());
        representation.topProfit = new BigDecimal(profits.stream().max(Comparator.naturalOrder()).get());
        return representation;

    }


    private static BigDecimal roundsToPercentage(long numberOfHits, long numberOfRounds) {
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        return new BigDecimal(numberOfHits).divide(new BigDecimal(numberOfRounds), mc).multiply(new BigDecimal(100), mc).setScale(3, HALF_UP);
    }


}