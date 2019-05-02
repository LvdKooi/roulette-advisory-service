package nl.kooi.representation.advises;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RedBlackAdviceRepresentation extends AdviceRepresentation {
    @NotNull
    public BigDecimal red;

    @NotNull
    public BigDecimal black;

}
