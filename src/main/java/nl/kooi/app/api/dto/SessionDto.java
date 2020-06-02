package nl.kooi.app.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class SessionDto {
    private int id;
    private int userId;
    @NotNull(message = "A session needs a chipValue")
    private BigDecimal chipValue;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssZ", timezone = "UTC")
    private Instant dateTime;
}
