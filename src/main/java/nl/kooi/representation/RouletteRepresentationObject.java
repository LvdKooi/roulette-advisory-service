package nl.kooi.representation;

import javax.validation.constraints.NotNull;

public class RouletteRepresentationObject {
    @NotNull
    public Integer outcome;

    @NotNull
    public Outcome redBlack;

    @NotNull
    public Outcome oddEven;

    @NotNull
    public Outcome half;

    @NotNull
    public Outcome row;

    @NotNull
    public Outcome dozen;

    @NotNull
    public Boolean isZero;
}
