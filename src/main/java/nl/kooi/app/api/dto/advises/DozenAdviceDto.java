package nl.kooi.app.api.dto.advises;

import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
public class DozenAdviceDto {
    @NotNull
    public BigDecimal firstDozen;

    @NotNull
    public BigDecimal secondDozen;

    @NotNull
    public BigDecimal thirdDozen;

}
