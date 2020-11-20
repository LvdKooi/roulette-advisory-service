package nl.kooi.app.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.SessionMetricsDto;
import nl.kooi.app.domain.service.OutcomeAdviceService;
import nl.kooi.app.domain.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "users/{userId}/sessions/{sessionId}/metrics")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MetricController {
    private final SessionService sessionService;
    private final OutcomeAdviceService outcomeAdviceService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SessionMetricsDto getMetrics(@PathVariable("userId") int userId, @PathVariable("sessionId") int sessionId) {
        sessionService.findByIdAndUserId(sessionId, userId);
        return Mapper.map(outcomeAdviceService.getSessionsMetrics(sessionId));
    }

}
