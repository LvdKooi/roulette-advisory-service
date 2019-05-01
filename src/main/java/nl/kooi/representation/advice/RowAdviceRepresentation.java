package nl.kooi.representation.advice;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RowAdviceRepresentation {
    @NotNull
    public BigDecimal lowRow;

    @NotNull
    public BigDecimal midRow;

    @NotNull
    public BigDecimal hiRow;

}
