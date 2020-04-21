package nl.kooi.app.api.dto.metrics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.NavigableMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionMetricsDto {
    @Setter
    private NavigableMap<RouletteOutcome, BigDecimal> outcomePercentageMap;
    private Long totalRounds;
    private BigDecimal currentProfit;
    private BigDecimal leastProfit;
    private BigDecimal topProfit;
}
