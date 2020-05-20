package nl.kooi.app.domain.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.NavigableMap;

@Getter
@ToString
@AllArgsConstructor
public class Advice {
    private int id;

    private NavigableMap<RouletteOutcome, BigDecimal> adviceMap;

    public Advice(NavigableMap<RouletteOutcome, BigDecimal> adviceMap) {
        this.adviceMap = adviceMap;
    }
}
