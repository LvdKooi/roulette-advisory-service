package nl.kooi.representation.advises;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RowAdviceRepresentation extends AdviceRepresentation {
    @NotNull
    public BigDecimal lowRow;

    @NotNull
    public BigDecimal midRow;

    @NotNull
    public BigDecimal hiRow;

}
