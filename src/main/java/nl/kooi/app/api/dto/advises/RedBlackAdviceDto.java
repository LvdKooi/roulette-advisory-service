package nl.kooi.app.api.dto.advises;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RedBlackAdviceDto extends AdviceDto {
    @NotNull
    public BigDecimal red;

    @NotNull
    public BigDecimal black;

}
