package nl.kooi.infrastructure.repository;


import nl.kooi.infrastructure.entity.AdviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviceRepository extends JpaRepository<AdviceEntity, Integer> {
    AdviceEntity findFirstBySessionIdOrderByIdDesc(int sessionId);
}
