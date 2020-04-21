package nl.kooi.app.domain.metrics;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static java.math.RoundingMode.HALF_UP;

@Slf4j
@Getter
public class SessionMetrics {
    private NavigableMap<RouletteOutcome, BigDecimal> outcomePercentageMap;
    private Long totalRounds;
    private BigDecimal currentProfit;
    private BigDecimal leastProfit;
    private BigDecimal topProfit;


    public SessionMetrics(Map<RouletteOutcome, Long> outcomeCountersMap, Long totalRounds, BigDecimal currentProfit, BigDecimal leastProfit, BigDecimal topProfit) {
        this.totalRounds = totalRounds;
        this.currentProfit = currentProfit;
        this.leastProfit = leastProfit;
        this.topProfit = topProfit;
        setOutcomePercentageMap(outcomeCountersMap, totalRounds);

    }

    private void setOutcomePercentageMap(Map<RouletteOutcome, Long> outcomeCountersMap, Long totalRounds) {
        outcomePercentageMap = new TreeMap<>();
        outcomeCountersMap.forEach((key, value) -> outcomePercentageMap.put(key, roundsToPercentage(value, totalRounds)));

    }

    private BigDecimal roundsToPercentage(long numberOfHits, long numberOfRounds) {
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        return new BigDecimal(numberOfHits).divide(new BigDecimal(numberOfRounds), mc).multiply(new BigDecimal(100), mc).setScale(3, HALF_UP);
    }

}