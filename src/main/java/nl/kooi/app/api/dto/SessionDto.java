package nl.kooi.app.api.dto;

import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Data
public class SessionDto {
    private int userId;
    private BigDecimal chipValue;
    private LocalDateTime dateTime;
}
