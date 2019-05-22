package nl.kooi.infrastructure.model;

        import lombok.Data;
        import org.springframework.data.jpa.domain.support.AuditingEntityListener;

        import javax.persistence.*;
        import java.util.Date;

@Data
@Entity
@Table(name = "outcomes")
@EntityListeners(AuditingEntityListener.class)
public class Outcomes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Sessions session;
    private int outcome;

}
