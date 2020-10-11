package nl.kooi.app.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.SessionDto;
import nl.kooi.app.domain.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "users/{userId}/sessions/")
@RestController
@Slf4j
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionDto> create(@PathVariable int userId, @Valid @RequestBody SessionDto sessionDto) {
        if (sessionDto.getUserId() == 0) {
            sessionDto.setUserId(userId);
        }
        return ResponseEntity.ok(Mapper.map(sessionService.save(Mapper.map(sessionDto))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDto> findById(@PathVariable int userId, @PathVariable int id) {
        return ResponseEntity.ok(Mapper.map(sessionService.findByIdAndUserId(id, userId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int userId, @PathVariable int id) {
        sessionService.findByIdAndUserId(id, userId);
        sessionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
