package nl.kooi.infrastructure.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sessions")
public class Sessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToMany(mappedBy = "sessionId", cascade = CascadeType.ALL)
    private Set<Outcomes> outcomes;

    public Sessions(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Outcomes> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(Set<Outcomes> outcomes) {
        this.outcomes = outcomes;
    }

    @Override
    public String toString() {
        String result = String.format(
                "Category[id=%d, name='%s']%n",
                id);
        if (outcomes != null) {
            for(Outcomes outcomes : this.outcomes) {
                result += String.format(
                        "Outcomes[id=%d, title='%s']%n",
                        outcomes.getId(), outcomes.getOutcome());
            }
        }

        return result;
    }
}
