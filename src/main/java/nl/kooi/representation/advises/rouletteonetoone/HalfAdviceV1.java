package nl.kooi.representation.advises.rouletteonetoone;

import nl.kooi.representation.advises.AdviceRepresentation;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class HalfAdviceV1 extends AdviceRepresentation {
    @NotNull
    public BigDecimal firstHalf;

    @NotNull
    public BigDecimal secondHalf;

}
