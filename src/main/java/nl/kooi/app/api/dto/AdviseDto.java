package nl.kooi.app.api.dto;

import lombok.Data;
import lombok.Setter;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.NavigableMap;

@Setter
@Data
public class AdviseDto {
    private NavigableMap<RouletteOutcome, BigDecimal> adviceMap;
}
