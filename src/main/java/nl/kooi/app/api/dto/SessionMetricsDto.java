package nl.kooi.app.api.dto;

import lombok.Data;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.NavigableMap;

@Data
public class SessionMetricsDto {
    private NavigableMap<RouletteOutcome, BigDecimal> outcomePercentageMap;
    private Long totalRounds;
    private BigDecimal currentProfit;
    private BigDecimal leastProfit;
    private BigDecimal topProfit;
}
