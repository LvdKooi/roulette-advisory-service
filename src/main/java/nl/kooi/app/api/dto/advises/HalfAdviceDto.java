package nl.kooi.app.api.dto.advises;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class HalfAdviceDto extends AdviceDto {
    @NotNull
    public BigDecimal firstHalf;

    @NotNull
    public BigDecimal secondHalf;

}
