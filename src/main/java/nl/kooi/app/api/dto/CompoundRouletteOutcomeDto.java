package nl.kooi.app.api.dto;

import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CompoundRouletteOutcomeDto {
    @NotNull
    public Integer outcome;

@NotNull
    public List<RouletteOutcome> outcomeList;
}
