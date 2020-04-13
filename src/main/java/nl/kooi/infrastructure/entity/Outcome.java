package nl.kooi.infrastructure.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
    private Boolean red;
    private Boolean black;
    private Boolean odd;
    private Boolean even;
    private Boolean firstHalf;
    private Boolean secondHalf;
    private Boolean firstColumn;
    private Boolean secondColumn;
    private Boolean thirdColumn;
    private Boolean firstDozen;
    private Boolean secondDozen;
    private Boolean zero;

    private String totalProfit;

    @CreationTimestamp
    private LocalDateTime dateTime;


}
