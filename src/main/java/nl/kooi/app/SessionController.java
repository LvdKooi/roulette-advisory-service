package nl.kooi.app;

import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.domain.model.Session;
import nl.kooi.infrastructure.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/sessions")
@RestController
@Slf4j

public class SessionController {

    @Autowired
    SessionRepository sessionRepository;

    @GetMapping
    public ResponseEntity<List<Session>> findAll() {
        return ResponseEntity.ok(sessionRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Session session) {
        return ResponseEntity.ok(sessionRepository.save(session));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> findById(@PathVariable int id) {
        Optional<Session> stock = sessionRepository.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> update(@PathVariable int id, @Valid @RequestBody Session session) {
        if (!sessionRepository.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(sessionRepository.save(session));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (!sessionRepository.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        sessionRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
