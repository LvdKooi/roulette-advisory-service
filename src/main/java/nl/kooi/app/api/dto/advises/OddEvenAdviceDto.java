package nl.kooi.app.api.dto.advises;

import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
public class OddEvenAdviceDto {
    @NotNull
    public BigDecimal odd;

    @NotNull
    public BigDecimal even;

}
