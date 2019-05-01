package nl.kooi.representation.advice;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RedBlackAdviceRepresentation {
    @NotNull
    public BigDecimal red;

    @NotNull
    public BigDecimal black;

}
