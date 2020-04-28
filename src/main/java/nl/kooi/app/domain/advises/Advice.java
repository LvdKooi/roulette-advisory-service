package nl.kooi.app.domain.advises;

import lombok.Getter;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.NavigableMap;

public class Advice {
    @Getter
    private NavigableMap<RouletteOutcome, BigDecimal> adviceMap;

    public Advice(NavigableMap<RouletteOutcome, BigDecimal> adviceMap) {
        this.adviceMap = adviceMap;
    }
}
