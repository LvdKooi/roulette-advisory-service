package nl.kooi.infrastructure.repository;


import nl.kooi.infrastructure.model.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface SessionsRepository extends JpaRepository<Sessions, Integer>{
}
