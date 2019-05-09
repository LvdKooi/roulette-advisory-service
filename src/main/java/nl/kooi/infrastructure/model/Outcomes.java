package nl.kooi.infrastructure.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Outcomes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int outcome;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Sessions sessionId;

    public Outcomes() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOutcome() {
        return outcome;
    }

    public void setOutcome(int outcome) {
        this.outcome = outcome;
    }

    public Sessions getSessionId() {
        return sessionId;
    }

    public void setSessionId(Sessions sessionId) {
        this.sessionId = sessionId;
    }
}
