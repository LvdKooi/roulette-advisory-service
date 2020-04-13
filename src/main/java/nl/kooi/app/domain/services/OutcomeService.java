package nl.kooi.app.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.var;
import nl.kooi.app.domain.Mapper;
import nl.kooi.app.domain.outcome.Outcome;
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
    private SessionRepository sessionRepository;
    @Autowired
    private RouletteOutcomeService rouletteOutcomeRepository;

    public int saveOutcome(int sessionId, String profit, int number) {
        Outcome outcome = new Outcome(sessionId, 0, number, profit, rouletteOutcomeRepository.getCompoundRouletteOutcome(number));
        var outcomeEntity = outcomeRepository.save(Mapper.map(outcome));
        return outcomeEntity.getId();
    }

    public List<Outcome> findBySessionIdOrderByIdAsc(int sessionId) {
        return outcomeRepository.findBySessionIdOrderByIdAsc(sessionId)
                .stream()
                .map(Mapper::map)
                .collect(Collectors.toList());
    }
}
