package nl.kooi.app.domain.metric;

import lombok.var;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcomeUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import static java.math.RoundingMode.HALF_UP;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SessionMetricsTest {

    private SessionMetrics sessionMetrics;

    @BeforeEach
    public void initTestDependencies() {
        var outcomeCounterMap = RouletteOutcomeUtilities
                .getCompoundRouletteOutcome(1)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> 10L));

        sessionMetrics = new SessionMetrics(outcomeCounterMap, 100L, BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.TEN);

    }

    @Test
    public void sessionMetricsPercentagesTest() {

        for (var entryset : sessionMetrics.getOutcomePercentageMap().entrySet()) {

            assertThat("Percentage of " + entryset.getKey() + "doesn't match expectation.", entryset.getValue(), equalTo(BigDecimal.TEN.setScale(3, HALF_UP)));
        }
    }

    @Test
    public void sessionMetricsProfitsTest() {
        assertThat("CurrentProfit doesn't match expectation.", sessionMetrics.getCurrentProfit(), equalTo(BigDecimal.ONE));
        assertThat("LeastProfit doesn't match expectation.", sessionMetrics.getLeastProfit(), equalTo(BigDecimal.ZERO));
        assertThat("TopProfit doesn't match expectation.", sessionMetrics.getTopProfit(), equalTo(BigDecimal.TEN));
    }

}
