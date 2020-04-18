package nl.kooi.app.api.dto.advises;

import lombok.Data;
import lombok.Setter;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.Map;

@Setter
@Data
public class FullAdviseDto {
    private Map<RouletteOutcome, BigDecimal> adviceMap;
}
