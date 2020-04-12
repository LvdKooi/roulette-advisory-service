package nl.kooi.app.api.dto.advises;

import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
public class HalfAdviceDto {
    @NotNull
    public BigDecimal firstHalf;

    @NotNull
    public BigDecimal secondHalf;

}
