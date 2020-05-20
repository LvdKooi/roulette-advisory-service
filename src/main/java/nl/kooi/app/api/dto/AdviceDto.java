package nl.kooi.app.api.dto;

import lombok.Data;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.NavigableMap;

@Data
public class AdviceDto {
    private int id;
    private NavigableMap<RouletteOutcome, BigDecimal> adviceMap;
}
