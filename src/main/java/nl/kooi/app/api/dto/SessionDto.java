package nl.kooi.app.api.dto;

import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Data
public class SessionDto {
    private int id;
    private int userId;
    @NotNull(message = "A session needs a chipValue")
    private BigDecimal chipValue;
}
