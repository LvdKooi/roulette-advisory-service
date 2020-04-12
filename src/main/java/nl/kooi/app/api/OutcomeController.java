package nl.kooi.app.api;

import lombok.extern.slf4j.Slf4j;

import nl.kooi.infrastructure.entity.Outcome;
import nl.kooi.infrastructure.repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/outcomes")
@RestController
@Slf4j

public class OutcomeController {

    @Autowired
    OutcomeRepository outcomeRepository;

    @GetMapping
    public ResponseEntity<List<Outcome>> findAll() {
        return ResponseEntity.ok(outcomeRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Outcome outcome) {
        return ResponseEntity.ok(outcomeRepository.save(outcome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Outcome> findById(@PathVariable int id) {
        Optional<Outcome> stock = outcomeRepository.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Outcome> update(@PathVariable int id, @Valid @RequestBody Outcome outcome) {
        if (!outcomeRepository.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(outcomeRepository.save(outcome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (!outcomeRepository.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.notFound().build();
        }

        outcomeRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
