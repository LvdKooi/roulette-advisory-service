package nl.kooi.representation.advises.roulettetwotoone;

import nl.kooi.representation.advises.AdviceRepresentation;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DozenAdviceV1 extends AdviceRepresentation {
    @NotNull
    public BigDecimal firstDozen;

    @NotNull
    public BigDecimal secondDozen;

    @NotNull
    public BigDecimal thirdDozen;

}
