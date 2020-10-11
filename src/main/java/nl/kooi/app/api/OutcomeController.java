package nl.kooi.app.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.OutcomeDto;
import nl.kooi.app.domain.service.OutcomeAdviceService;
import nl.kooi.app.domain.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(path = "users/{userId}/sessions/{sessionId}/outcomes")
@RestController
@Slf4j
@RequiredArgsConstructor
public class OutcomeController {
    private final OutcomeAdviceService outcomeAdviceService;
    private final SessionService sessionService;

    @GetMapping
    public ResponseEntity<List<OutcomeDto>> findAllBySessionId(@PathVariable int userId, @PathVariable int sessionId) {
        sessionService.findByIdAndUserId(sessionId, userId);
        return ResponseEntity.ok(outcomeAdviceService.findOutcomesBySessionIdOrderByIdAsc(sessionId)
                .stream()
                .map(Mapper::map)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<OutcomeDto> create(@PathVariable int userId, @PathVariable int sessionId, @Valid @RequestBody OutcomeDto outcomeDto) {
        sessionService.findByIdAndUserId(sessionId, userId);
        return ResponseEntity.ok(Mapper.map(outcomeAdviceService.saveOutcomeAndAdvise(userId, sessionId, outcomeDto.getOutcome())));
    }

    @GetMapping("/last-outcome")
    public ResponseEntity<OutcomeDto> findLastOutcome(@PathVariable int userId, @PathVariable int sessionId) {
        sessionService.findByIdAndUserId(sessionId, userId);
        return ResponseEntity.ok(Mapper.map(outcomeAdviceService.findLastOutcome(sessionId)));
    }


    @DeleteMapping("/last-outcome")
    public ResponseEntity deleteLastOutcome(@PathVariable int userId, @PathVariable int sessionId) {
        sessionService.findByIdAndUserId(sessionId, userId);
        outcomeAdviceService.deleteLastOutcome(sessionId);
        return ResponseEntity.ok().build();
    }
}
