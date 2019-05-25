package nl.kooi.infrastructure.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "sessions")
@EntityListeners(AuditingEntityListener.class)
public class Session implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String chipValue;

    @CreationTimestamp
    private LocalDateTime dateTime;



}
