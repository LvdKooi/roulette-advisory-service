package nl.kooi.app.domain.session;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Session {
    private int id;
    @NonNull
    private int userId;
    @NonNull
    private BigDecimal chipValue;
    private Instant dateTime;
}
