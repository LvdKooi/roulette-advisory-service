package nl.kooi.infrastructure.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
@Data
@Entity
public class Sessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String chipValue;
    private Date dateTime;

}
