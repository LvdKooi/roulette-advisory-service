package nl.kooi.app.persistence.repository;


import nl.kooi.app.persistence.entity.AdviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviceRepository extends JpaRepository<AdviceEntity, Integer> {
    AdviceEntity findFirstBySessionIdOrderByIdDesc(int sessionId);
}
