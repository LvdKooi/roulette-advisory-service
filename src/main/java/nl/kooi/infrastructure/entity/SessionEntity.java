package nl.kooi.infrastructure.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "sessions")
@EntityListeners(AuditingEntityListener.class)
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String chipValue;

    @CreationTimestamp
    private LocalDateTime dateTime;


}
