package nl.kooi.app.api.dto.advises;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ColumnAdviceDto extends AdviceDto {
    @NotNull
    public BigDecimal firstColumn;

    @NotNull
    public BigDecimal secondColumn;

    @NotNull
    public BigDecimal thirdColumn;

}
