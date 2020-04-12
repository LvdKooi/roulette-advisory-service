package nl.kooi.app.api.dto.metrics;

import lombok.Setter;

import java.math.BigDecimal;


public class SessionMetricsDto {
    @Setter
    public long totalNumberOfRounds;
    public HalfMetricsDto halfMetrics;
    public OddEvenMetricsDto oddEvenMetrics;
    public RedBlackMetricsDto redBlackMetrics;
    public ColumnMetricsDto columnMetrics;
    public DozenMetricsDto dozenMetrics;
    public BigDecimal percentageZero;
    public BigDecimal currentProfit;
    public BigDecimal leastProfit;
    public BigDecimal topProfit;

    public SessionMetricsDto() {
        halfMetrics = new HalfMetricsDto();
        oddEvenMetrics = new OddEvenMetricsDto();
        redBlackMetrics = new RedBlackMetricsDto();
        columnMetrics = new ColumnMetricsDto();
        dozenMetrics = new DozenMetricsDto();
    }

}
