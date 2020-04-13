package nl.kooi.infrastructure.repository;


import nl.kooi.infrastructure.entity.Advise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviseRepository extends JpaRepository<Advise, Integer> {

}
