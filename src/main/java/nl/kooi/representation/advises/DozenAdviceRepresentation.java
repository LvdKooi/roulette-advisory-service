package nl.kooi.representation.advises;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DozenAdviceRepresentation extends AdviceRepresentation {
    @NotNull
    public BigDecimal firstDozen;

    @NotNull
    public BigDecimal secondDozen;

    @NotNull
    public BigDecimal thirdDozen;

}
