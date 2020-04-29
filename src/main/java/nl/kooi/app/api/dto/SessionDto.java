package nl.kooi.app.api.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class SessionDto {
    private int id;
    private int userId;
    @NotNull(message = "A session needs a chipValue")
    private BigDecimal chipValue;
}
