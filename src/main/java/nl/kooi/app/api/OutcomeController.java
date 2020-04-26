package nl.kooi.app.api;

import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.OutcomeDto;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.services.OutcomeAdviceService;
import nl.kooi.app.domain.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "users/{userId}/sessions/{sessionId}/outcomes")
@RestController
@Slf4j
public class OutcomeController {

    @Autowired
    OutcomeAdviceService outcomeAdviceService;
    @Autowired
    private SessionService sessionService;

    @GetMapping
    public ResponseEntity<List<Outcome>> findAllBySessionId(@PathVariable int userId, @PathVariable int sessionId) {
        sessionService.findByIdAndUserId(sessionId, userId);
        return ResponseEntity.ok(outcomeAdviceService.findOutcomesBySessionIdOrderByIdAsc(sessionId));
    }

    @PostMapping
    public ResponseEntity<Outcome> create(@Valid @RequestBody OutcomeDto outcomeDto, @PathVariable int userId, @PathVariable int sessionId) {
        sessionService.findByIdAndUserId(sessionId, userId);
        return ResponseEntity.ok(outcomeAdviceService.saveOutcomeAndAdvise(userId, sessionId, outcomeDto.getOutcome()));
    }

    @GetMapping("/last-outcome")
    public ResponseEntity<OutcomeDto> findLastOutcome(@PathVariable int sessionId, @PathVariable int userId) {
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
