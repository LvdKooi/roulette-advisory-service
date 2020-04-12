package nl.kooi.app.api.dto.advises;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DozenAdviceDto extends AdviceDto {
    @NotNull
    public BigDecimal firstDozen;

    @NotNull
    public BigDecimal secondDozen;

    @NotNull
    public BigDecimal thirdDozen;

}
