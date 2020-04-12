package nl.kooi.infrastructure.repository;


import nl.kooi.infrastructure.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
Optional<Session> findByIdAndUserId(int id, int userId);

}
