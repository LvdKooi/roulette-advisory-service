package nl.kooi.representation.advises.rouletteonetoone;

import nl.kooi.representation.advises.AdviceRepresentation;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OddEvenAdviceV1 extends AdviceRepresentation {
    @NotNull
    public BigDecimal odd;

    @NotNull
    public BigDecimal even;

}
