package nl.kooi.app.persistence.repository;


import nl.kooi.app.persistence.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {
    Optional<SessionEntity> findByIdAndUserId(int id, int userId);

    List<SessionEntity> findByUserId(int userId);

}
