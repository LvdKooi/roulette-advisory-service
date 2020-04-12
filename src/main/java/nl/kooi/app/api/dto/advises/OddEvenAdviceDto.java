package nl.kooi.app.api.dto.advises;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OddEvenAdviceDto extends AdviceDto {
    @NotNull
    public BigDecimal odd;

    @NotNull
    public BigDecimal even;

}
