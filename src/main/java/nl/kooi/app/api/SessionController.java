package nl.kooi.app.api;

import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.SessionDto;
import nl.kooi.app.domain.services.SessionService;
import nl.kooi.app.domain.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "users/{userId}/sessions/")
@RestController
@Slf4j

public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<Session> create(@Valid @RequestBody SessionDto sessionDto) {
        return ResponseEntity.ok(sessionService.save(Mapper.map(sessionDto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(Mapper.map(sessionService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        sessionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
