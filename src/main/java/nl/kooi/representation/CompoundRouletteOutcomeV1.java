package nl.kooi.representation;

import nl.kooi.app.domain.RouletteOutcome;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CompoundRouletteOutcomeV1 {
    @NotNull
    public Integer outcome;

@NotNull
    public List<RouletteOutcome> outcomeList;
}
