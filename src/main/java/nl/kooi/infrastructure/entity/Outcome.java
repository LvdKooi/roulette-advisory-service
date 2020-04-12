package nl.kooi.infrastructure.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "outcomes")
@EntityListeners(AuditingEntityListener.class)
public class Outcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Session session;
    private int outcome;
    private String totalProfit;

    @CreationTimestamp
    private LocalDateTime dateTime;

}
