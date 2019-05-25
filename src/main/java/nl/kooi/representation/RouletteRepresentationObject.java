package nl.kooi.representation;

import javax.validation.constraints.NotNull;

public class RouletteRepresentationObject {
    @NotNull
    public Integer outcome;

    @NotNull
    public RouletteOutcome redBlack;

    @NotNull
    public RouletteOutcome oddEven;

    @NotNull
    public RouletteOutcome half;

    @NotNull
    public RouletteOutcome row;

    @NotNull
    public RouletteOutcome dozen;

    @NotNull
    public Boolean isZero;
}
