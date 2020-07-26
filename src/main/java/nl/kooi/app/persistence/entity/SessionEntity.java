package nl.kooi.app.persistence.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;


@Data
@Entity
@Table(name = "sessions")
@EntityListeners(AuditingEntityListener.class)
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private BigDecimal chipValue;

    @CreationTimestamp
    private Instant dateTime;


}
