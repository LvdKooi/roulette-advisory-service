package nl.kooi.infrastructure.repository;

import nl.kooi.infrastructure.entity.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {

    Collection<Outcome> findBySessionIdOrderByIdAsc(int id);

    @Query(value = "select min(cast(total_profit as signed)) from outcomes where session_id = ?1",
            nativeQuery = true)
    Long getLeastProfitAmount(int sessionId);


    @Query(value = "select max(cast(total_profit as signed)) from outcomes where session_id = ?1",
            nativeQuery = true)
    Long getHighestProfitAmount(int sessionId);

}