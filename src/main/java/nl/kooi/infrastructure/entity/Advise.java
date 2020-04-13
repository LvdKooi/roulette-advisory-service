package nl.kooi.infrastructure.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "advises")
@EntityListeners(AuditingEntityListener.class)
public class Advise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Session session;
    @OneToOne
    private Outcome causing_outcome;
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
}
