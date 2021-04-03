package nl.kooi.app.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.api.dto.AdviceDto;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.domain.service.OutcomeAdviceService;
import nl.kooi.app.domain.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AdviceController implements nl.kooi.app.api.controller.AdviceApi {
    private final SessionService sessionService;
    private final OutcomeAdviceService outcomeAdviceService;

    @Override
    public ResponseEntity<AdviceDto> getAdvice(Integer userId, Integer sessionId) {
        sessionService.findByIdAndUserId(sessionId, userId);
        return ResponseEntity.ok(Mapper.map(outcomeAdviceService.findLastAdvice(sessionId)));
    }
}

