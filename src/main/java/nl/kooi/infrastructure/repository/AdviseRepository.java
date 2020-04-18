package nl.kooi.infrastructure.repository;


import nl.kooi.infrastructure.entity.AdviseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviseRepository extends JpaRepository<AdviseEntity, Integer> {
    AdviseEntity findFirstBySessionIdOrderByIdDesc(int sessionId);
}
