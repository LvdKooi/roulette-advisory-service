package nl.kooi.app;

import lombok.extern.slf4j.Slf4j;
import nl.kooi.infrastructure.model.Sessions;
import nl.kooi.infrastructure.repository.SessionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/sessions")
@RestController
@Slf4j

public class SessionsController {

    @Autowired
    SessionsRepository sessionsRepository;

    @GetMapping
    public ResponseEntity<List<Sessions>> findAll() {
        return ResponseEntity.ok(sessionsRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Sessions session) {
        return ResponseEntity.ok(sessionsRepository.save(session));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sessions> findById(@PathVariable int id) {
        Optional<Sessions> stock = sessionsRepository.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sessions> update(@PathVariable int id, @Valid @RequestBody Sessions session) {
        if (!sessionsRepository.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(sessionsRepository.save(session));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (!sessionsRepository.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        sessionsRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
