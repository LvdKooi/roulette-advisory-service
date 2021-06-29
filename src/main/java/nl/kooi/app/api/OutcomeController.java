package nl.kooi.app.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.OutcomeDto;
import nl.kooi.app.domain.service.OutcomeAdviceService;
import nl.kooi.app.domain.service.SessionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RequestMapping(path = "users/{userId}/sessions/{sessionId}/outcomes")
@RestController
@Slf4j
@RequiredArgsConstructor
public class OutcomeController {
    private final OutcomeAdviceService outcomeAdviceService;
    private final SessionService sessionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<OutcomeDto> findAllBySessionId(@PathVariable int userId, @PathVariable int sessionId, Pageable pageable) {
        sessionService.findByIdAndUserId(sessionId, userId);
        var outcomesPage = outcomeAdviceService.findOutcomesBySessionIdOrderByIdAsc(sessionId, pageable);
        var outcomes = outcomesPage.stream()
                .map(Mapper::map)
                .collect(Collectors.toList());
        return new PageImpl<>(outcomes, pageable, outcomesPage.getTotalElements());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public OutcomeDto create(@PathVariable int userId, @PathVariable int sessionId, @Valid @RequestBody OutcomeDto outcomeDto) {
        sessionService.findByIdAndUserId(sessionId, userId);
        return Mapper.map(outcomeAdviceService.saveOutcomeAndAdvise(userId, sessionId, outcomeDto.getOutcome()));
    }

    @GetMapping("/last-outcome")
    @ResponseStatus(HttpStatus.OK)
    public OutcomeDto findLastOutcome(@PathVariable int userId, @PathVariable int sessionId) {
        sessionService.findByIdAndUserId(sessionId, userId);
        return Mapper.map(outcomeAdviceService.findLastOutcome(sessionId));
    }

    @DeleteMapping("/last-outcome")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLastOutcome(@PathVariable int userId, @PathVariable int sessionId) {
        sessionService.findByIdAndUserId(sessionId, userId);
        outcomeAdviceService.deleteLastOutcome(sessionId);
    }
}
