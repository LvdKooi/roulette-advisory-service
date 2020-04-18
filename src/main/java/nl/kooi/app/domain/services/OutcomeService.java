package nl.kooi.app.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.var;
import nl.kooi.app.domain.Mapper;
import nl.kooi.app.domain.advises.FullAdvise;
import nl.kooi.app.domain.game.RouletteGame;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.exceptions.SessionNotFoundException;
import nl.kooi.infrastructure.repository.AdviseRepository;
import nl.kooi.infrastructure.repository.OutcomeRepository;
import nl.kooi.infrastructure.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutcomeService {

    @Autowired
    private OutcomeRepository outcomeRepository;

    @Autowired
    private RouletteOutcomeService rouletteOutcomeRepository;

    @Autowired
    private AdviseRepository adviseRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public Outcome saveOutcomeAndAdvise(int userId, int sessionId, int number) {
        var sessionEntity = sessionRepository.findByIdAndUserId(sessionId, userId)
                .orElseThrow(() -> new SessionNotFoundException("Session Id not found"));

        var rouletteGame = new RouletteGame(sessionEntity.getChipValue());

        rouletteGame.setHits(findOutcomesBySessionIdOrderByIdAsc(sessionId));

        var outcome = new Outcome(sessionId,
                number,
                rouletteGame.getTotalProfit(),
                rouletteOutcomeRepository.getCompoundRouletteOutcome(number));

        var outcomeEntity = outcomeRepository.save(Mapper.map(outcome));
        var adviseEntity = Mapper.map(rouletteGame.getAdvise());
        adviseEntity.setSession(sessionEntity);
        adviseEntity.setCausingOutcome(outcomeEntity);

        adviseRepository.save(adviseEntity);

        return Mapper.map(outcomeEntity);
    }

    public List<Outcome> findOutcomesBySessionIdOrderByIdAsc(int sessionId) {
        return outcomeRepository.findBySessionIdOrderByIdAsc(sessionId)
                .stream()
                .map(Mapper::map)
                .collect(Collectors.toList());
    }

    public FullAdvise findLastAdvice(int sessionId) {
        return Mapper.map(adviseRepository.findFirstBySessionIdOrderByIdDesc(sessionId));

    }

}
