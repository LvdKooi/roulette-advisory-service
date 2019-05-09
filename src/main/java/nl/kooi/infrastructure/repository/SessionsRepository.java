package nl.kooi.infrastructure.repository;


import nl.kooi.infrastructure.model.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionsRepository extends JpaRepository<Sessions, Integer>{
}
