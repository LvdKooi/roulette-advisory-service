package nl.kooi.representation.advises;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DozenAdviceRepresentation {
    @NotNull
    public BigDecimal lowDozen;

    @NotNull
    public BigDecimal midDozen;

    @NotNull
    public BigDecimal hiDozen;

}
