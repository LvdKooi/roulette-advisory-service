package nl.kooi.infrastructure.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "advises")
@EntityListeners(AuditingEntityListener.class)
public class AdviseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private SessionEntity session;
    @OneToOne
    private OutcomeEntity causingOutcome;
    private Integer redAdvice;
    private Integer blackAdvice;
    private Integer oddAdvice;
    private Integer evenAdvice;
    private Integer firstHalfAdvice;
    private Integer secondHalfAdvice;
    private Integer firstColumnAdvice;
    private Integer secondColumnAdvice;
    private Integer thirdColumnAdvice;
    private Integer firstDozenAdvice;
    private Integer secondDozenAdvice;
    private Integer thirdDozenAdvice;
}
