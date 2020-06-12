package nl.kooi.infrastructure.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity
@Table(name = "outcomes")
@EntityListeners(AuditingEntityListener.class)
public class OutcomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private SessionEntity session;
    private Integer outcome;
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
    private Boolean thirdDozen;
    private Boolean zero;

    private BigDecimal totalProfit;

    @CreationTimestamp
    private Instant dateTime;


}
