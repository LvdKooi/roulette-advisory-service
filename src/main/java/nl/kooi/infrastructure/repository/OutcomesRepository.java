package nl.kooi.infrastructure.repository;


import nl.kooi.infrastructure.model.Outcomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomesRepository extends JpaRepository<Outcomes, Integer>{
}
