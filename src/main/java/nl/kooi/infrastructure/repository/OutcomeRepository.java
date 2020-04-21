package nl.kooi.infrastructure.repository;

import nl.kooi.infrastructure.entity.OutcomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;

@Repository
public interface OutcomeRepository extends JpaRepository<OutcomeEntity, Integer> {

    Collection<OutcomeEntity> findBySessionIdOrderByIdAsc(int id);

    OutcomeEntity findFirstBySessionIdOrderByIdDesc(int id);

    long countBySessionId(int sessionId);

    long countByRedAndSessionId(boolean trueFalse, int sessionId);

    long countByBlackAndSessionId(boolean trueFalse, int sessionId);

    long countByOddAndSessionId(boolean trueFalse, int sessionId);

    long countByEvenAndSessionId(boolean trueFalse, int sessionId);

    long countByFirstHalfAndSessionId(boolean trueFalse, int sessionId);

    long countBySecondHalfAndSessionId(boolean trueFalse, int sessionId);

    long countByFirstDozenAndSessionId(boolean trueFalse, int sessionId);

    long countBySecondDozenAndSessionId(boolean trueFalse, int sessionId);

    long countByThirdDozenAndSessionId(boolean trueFalse, int sessionId);

    long countByFirstColumnAndSessionId(boolean trueFalse, int sessionId);

    long countBySecondColumnAndSessionId(boolean trueFalse, int sessionId);

    long countByThirdColumnAndSessionId(boolean trueFalse, int sessionId);

    long countByZeroAndSessionId(boolean trueFalse, int sessionId);


    @Query(value = "select min(cast(total_profit as signed)) from outcomes where session_id = ?1",
            nativeQuery = true)
    BigDecimal getLeastProfitAmount(int sessionId);


    @Query(value = "select max(cast(total_profit as signed)) from outcomes where session_id = ?1",
            nativeQuery = true)
    BigDecimal getHighestProfitAmount(int sessionId);

}