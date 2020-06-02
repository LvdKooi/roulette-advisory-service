package nl.kooi.app.domain.session;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private int id;
    private int userId;
    private BigDecimal chipValue;
    private Instant dateTime;
}
