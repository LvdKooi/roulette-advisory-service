package nl.kooi.app.api.dto.advises;

import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
public class ColumnAdviceDto {
    @NotNull
    public BigDecimal firstColumn;

    @NotNull
    public BigDecimal secondColumn;

    @NotNull
    public BigDecimal thirdColumn;

}
