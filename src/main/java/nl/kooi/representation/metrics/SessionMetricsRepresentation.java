package nl.kooi.representation.metrics;

import nl.kooi.representation.metrics.rouletteonetoone.HalfMetricsRepresentation;
import nl.kooi.representation.metrics.rouletteonetoone.OddEvenMetricsRepresentation;
import nl.kooi.representation.metrics.rouletteonetoone.RedBlackMetricsRepresentation;
import nl.kooi.representation.metrics.roulettetwotoone.ColumnMetricsRepresentation;
import nl.kooi.representation.metrics.roulettetwotoone.DozenMetricsRepresentation;

import java.math.BigDecimal;

public class SessionMetricsRepresentation {
    public long totalNumberOfRound;
    public HalfMetricsRepresentation halfMetrics;
    public OddEvenMetricsRepresentation oddEvenMetrics;
    public RedBlackMetricsRepresentation redBlackMetrics;
    public ColumnMetricsRepresentation columnMetrics;
    public DozenMetricsRepresentation dozenMetrics;
    public BigDecimal percentageZero;

    public SessionMetricsRepresentation() {
        halfMetrics = new HalfMetricsRepresentation();
        oddEvenMetrics = new OddEvenMetricsRepresentation();
        redBlackMetrics = new RedBlackMetricsRepresentation();
        columnMetrics = new ColumnMetricsRepresentation();
        dozenMetrics = new DozenMetricsRepresentation();

    }

}
