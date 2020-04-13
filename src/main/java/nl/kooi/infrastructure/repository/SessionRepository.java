package nl.kooi.infrastructure.repository;


import nl.kooi.infrastructure.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {
    Optional<SessionEntity> findByIdAndUserId(int id, int userId);

}
