package nl.kooi.representation.metrics;

import nl.kooi.representation.metrics.rouletteonetoone.HalfMetricsV1;
import nl.kooi.representation.metrics.rouletteonetoone.OddEvenMetricsV1;
import nl.kooi.representation.metrics.rouletteonetoone.RedBlackMetricsV1;
import nl.kooi.representation.metrics.roulettetwotoone.ColumnMetricsV1;
import nl.kooi.representation.metrics.roulettetwotoone.DozenMetricsV1;

import java.math.BigDecimal;

public class SessionMetricsV1 {
    public long totalNumberOfRounds;
    public HalfMetricsV1 halfMetrics;
    public OddEvenMetricsV1 oddEvenMetrics;
    public RedBlackMetricsV1 redBlackMetrics;
    public ColumnMetricsV1 columnMetrics;
    public DozenMetricsV1 dozenMetrics;
    public BigDecimal percentageZero;
    public BigDecimal currentProfit;
    public BigDecimal leastProfit;
    public BigDecimal topProfit;

    public SessionMetricsV1() {
        halfMetrics = new HalfMetricsV1();
        oddEvenMetrics = new OddEvenMetricsV1();
        redBlackMetrics = new RedBlackMetricsV1();
        columnMetrics = new ColumnMetricsV1();
        dozenMetrics = new DozenMetricsV1();
    }

}
