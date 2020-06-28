package nl.kooi.app.domain.service;

import lombok.RequiredArgsConstructor;
import nl.kooi.app.domain.Mapper;
import nl.kooi.app.domain.advice.Advice;
import nl.kooi.app.domain.game.RouletteGame;
import nl.kooi.app.domain.metric.SessionMetrics;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;
import nl.kooi.app.exception.NotFoundException;
import nl.kooi.infrastructure.repository.AdviceRepository;
import nl.kooi.infrastructure.repository.OutcomeRepository;
import nl.kooi.infrastructure.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.*;

@RequiredArgsConstructor
@Service
public class OutcomeAdviceService {

    @Autowired
    private OutcomeRepository outcomeRepository;

    @Autowired
    private AdviceRepository adviceRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public Outcome saveOutcomeAndAdvise(int userId, int sessionId, int number) {
        var sessionEntity = sessionRepository.findByIdAndUserId(sessionId, userId)
                .orElseThrow(() -> new NotFoundException("Session Id not found"));

        var rouletteGame = new RouletteGame(sessionEntity.getChipValue());

        var outcomes = findOutcomesBySessionIdOrderByIdAsc(sessionId);

        outcomes.add(outcomes.size(), new Outcome(sessionId,
                number));

        rouletteGame.setHits(outcomes);

        var outcome = new Outcome(sessionId,
                number,
                rouletteGame.getTotalProfit());

        var outcomeEntity = outcomeRepository.save(Mapper.map(outcome));
        var adviseEntity = Mapper.map(rouletteGame.getAdvice());
        adviseEntity.setSession(sessionEntity);
        adviseEntity.setCausingOutcome(outcomeEntity);

        adviceRepository.save(adviseEntity);

        return Mapper.map(outcomeEntity);
    }

    public List<Outcome> findOutcomesBySessionIdOrderByIdAsc(int sessionId) {
        return outcomeRepository.findBySessionIdOrderByIdAsc(sessionId)
                .stream()
                .map(Mapper::map)
                .collect(Collectors.toList());
    }

    public Advice findLastAdvice(int sessionId) {
        if (adviceRepository.findFirstBySessionIdOrderByIdDesc(sessionId) != null) {
            return Mapper.map(adviceRepository.findFirstBySessionIdOrderByIdDesc(sessionId));
        }
        throw new NotFoundException("No advises found for session.");
    }

    public Outcome findLastOutcome(int sessionId) {
        if (outcomeRepository.findFirstBySessionIdOrderByIdDesc(sessionId) != null) {
            return Mapper.map(outcomeRepository.findFirstBySessionIdOrderByIdDesc(sessionId));
        }

        throw new NotFoundException("No outcomes found for session.");
    }

    public void deleteLastOutcome(int sessionId) {
        findLastOutcome(sessionId);
        adviceRepository.deleteById(findLastAdvice(sessionId).getId());
        outcomeRepository.deleteById(findLastOutcome(sessionId).getId());
    }

    public SessionMetrics getSessionsMetrics(int sessionId) {
        var outcomeCountersMap = new HashMap<RouletteOutcome, Long>();
        outcomeCountersMap.put(RED, outcomeRepository.countByRedAndSessionId(true, sessionId));
        outcomeCountersMap.put(BLACK, outcomeRepository.countByBlackAndSessionId(true, sessionId));
        outcomeCountersMap.put(ODD, outcomeRepository.countByOddAndSessionId(true, sessionId));
        outcomeCountersMap.put(EVEN, outcomeRepository.countByEvenAndSessionId(true, sessionId));
        outcomeCountersMap.put(FIRST_HALF, outcomeRepository.countByFirstHalfAndSessionId(true, sessionId));
        outcomeCountersMap.put(SECOND_HALF, outcomeRepository.countBySecondHalfAndSessionId(true, sessionId));
        outcomeCountersMap.put(FIRST_COLUMN, outcomeRepository.countByFirstColumnAndSessionId(true, sessionId));
        outcomeCountersMap.put(SECOND_COLUMN, outcomeRepository.countBySecondColumnAndSessionId(true, sessionId));
        outcomeCountersMap.put(THIRD_COLUMN, outcomeRepository.countByThirdColumnAndSessionId(true, sessionId));
        outcomeCountersMap.put(FIRST_DOZEN, outcomeRepository.countByFirstDozenAndSessionId(true, sessionId));
        outcomeCountersMap.put(SECOND_DOZEN, outcomeRepository.countBySecondDozenAndSessionId(true, sessionId));
        outcomeCountersMap.put(THIRD_DOZEN, outcomeRepository.countByThirdDozenAndSessionId(true, sessionId));
        outcomeCountersMap.put(ZERO, outcomeRepository.countByZeroAndSessionId(true, sessionId));

        var totalRounds = outcomeRepository.countBySessionId(sessionId);

        var leastProfit = outcomeRepository.getLeastProfitAmount(sessionId);

        var topProfit = outcomeRepository.getHighestProfitAmount(sessionId);

        var currentProfit = findLastOutcome(sessionId).getTotalProfit();

        return new SessionMetrics(outcomeCountersMap, totalRounds, currentProfit, leastProfit, topProfit);
    }

}
