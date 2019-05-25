package nl.kooi.infrastructure.repository;


import nl.kooi.infrastructure.model.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Integer>{

   Collection<Outcome> findBySessionIdOrderByIdAsc(int id);
}
