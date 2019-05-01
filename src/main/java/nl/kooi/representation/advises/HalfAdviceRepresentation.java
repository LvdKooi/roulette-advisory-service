package nl.kooi.representation.advises;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class HalfAdviceRepresentation {
    @NotNull
    public BigDecimal firstHalf;

    @NotNull
    public BigDecimal secondHalf;

}
