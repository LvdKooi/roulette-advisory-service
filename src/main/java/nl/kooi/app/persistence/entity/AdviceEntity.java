package nl.kooi.app.persistence.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "advices")
@EntityListeners(AuditingEntityListener.class)
public class AdviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private SessionEntity session;
    @OneToOne
    private OutcomeEntity causingOutcome;
    private BigDecimal redAdvice;
    private BigDecimal blackAdvice;
    private BigDecimal oddAdvice;
    private BigDecimal evenAdvice;
    private BigDecimal firstHalfAdvice;
    private BigDecimal secondHalfAdvice;
    private BigDecimal firstColumnAdvice;
    private BigDecimal secondColumnAdvice;
    private BigDecimal thirdColumnAdvice;
    private BigDecimal firstDozenAdvice;
    private BigDecimal secondDozenAdvice;
    private BigDecimal thirdDozenAdvice;
}
