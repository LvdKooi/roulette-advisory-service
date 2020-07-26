package nl.kooi.app.api;

import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.api.dto.AdviceDto;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.domain.service.OutcomeAdviceService;
import nl.kooi.app.domain.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "users/{userId}/sessions/{sessionId}/advices")
@RestController
@Slf4j
public class AdviceController {
    @Autowired
    SessionService sessionService;

    @Autowired
    OutcomeAdviceService outcomeAdviceService;

    @GetMapping("/last-advice")
    public AdviceDto getAdvice(@PathVariable("userId") int userId, @PathVariable("sessionId") int sessionId) {
        sessionService.findByIdAndUserId(sessionId, userId);
        return Mapper.map(outcomeAdviceService.findLastAdvice(sessionId));
    }
}
