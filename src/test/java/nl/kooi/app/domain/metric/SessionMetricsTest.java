package nl.kooi.app.domain.metric;

import nl.kooi.app.domain.rouletteoutcome.RouletteOutcomeUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import static java.math.RoundingMode.HALF_UP;
import static org.assertj.core.api.Assertions.assertThat;


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

            assertThat(entryset.getValue()).isEqualTo(BigDecimal.TEN.setScale(3, HALF_UP));
        }
    }

    @Test
    public void sessionMetricsProfitsTest() {
        assertThat(sessionMetrics.getCurrentProfit()).isEqualTo(BigDecimal.ONE);
        assertThat(sessionMetrics.getLeastProfit()).isEqualTo(BigDecimal.ZERO);
        assertThat(sessionMetrics.getTopProfit()).isEqualTo(BigDecimal.TEN);
    }

}
