package nl.kooi.infrastructure.repository;


import nl.kooi.infrastructure.model.Outcomes;
import nl.kooi.infrastructure.model.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OutcomesRepository extends JpaRepository<Outcomes, Integer>{
   Collection<Outcomes> findBySessionId(int id);
}
