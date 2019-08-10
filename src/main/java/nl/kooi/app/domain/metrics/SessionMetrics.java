package nl.kooi.app.domain.metrics;

import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.domain.model.Outcome;
import nl.kooi.representation.RouletteOutcome;
import nl.kooi.representation.metrics.SessionMetricsRepresentation;

import java.math.BigDecimal;
import java.util.Collection;

public class SessionMetrics {
    private RouletteDomainObject roulette;
    private Collection<Outcome> outcomes;
    private BigDecimal biggestLoss;
    private BigDecimal biggestProfit;
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
        this.roulette = new RouletteDomainObject(0);
        totalRounds = outcomes.stream().count();
        outcomes.stream().forEach(outcome -> new CounterHelper(outcome));

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

    public SessionMetricsRepresentation toRepresentation() {
        SessionMetricsRepresentation representation = new SessionMetricsRepresentation();
        representation.totalNumberOfRound = totalRounds;
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
        return representation;

    }


    private static BigDecimal roundsToPercentage(long numberOfHits, long numberOfRounds) {

        return (new BigDecimal(numberOfHits).divide(new BigDecimal(numberOfRounds))).multiply(new BigDecimal(100)).setScale(2);
    }


}