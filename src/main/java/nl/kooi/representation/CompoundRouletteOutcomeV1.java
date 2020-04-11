package nl.kooi.representation;

import nl.kooi.app.domain.RouletteOutcome;

import javax.validation.constraints.NotNull;

public class CompoundRouletteOutcomeV1 {
    @NotNull
    public Integer outcome;

    @NotNull
    public RouletteOutcome redBlack;

    @NotNull
    public RouletteOutcome oddEven;

    @NotNull
    public RouletteOutcome half;

    @NotNull
    public RouletteOutcome column;

    @NotNull
    public RouletteOutcome dozen;

    @NotNull
    public Boolean isZero;
}
