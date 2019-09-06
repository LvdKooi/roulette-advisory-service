package nl.kooi.representation.advises.roulettetwotoone;

import nl.kooi.representation.advises.AdviceRepresentation;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ColumnAdviceV1 extends AdviceRepresentation {
    @NotNull
    public BigDecimal firstColumn;

    @NotNull
    public BigDecimal secondColumn;

    @NotNull
    public BigDecimal thirdColumn;

}
