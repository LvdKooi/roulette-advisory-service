package nl.kooi.representation.advises;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OddEvenAdviceRepresentation {
    @NotNull
    public BigDecimal odd;

    @NotNull
    public BigDecimal even;

}
