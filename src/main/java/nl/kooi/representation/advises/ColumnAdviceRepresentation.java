package nl.kooi.representation.advises;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ColumnAdviceRepresentation extends AdviceRepresentation {
    @NotNull
    public BigDecimal firstColumn;

    @NotNull
    public BigDecimal secondColumn;

    @NotNull
    public BigDecimal thirdColumn;

}
