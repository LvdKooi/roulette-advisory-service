package nl.kooi.app;

import lombok.extern.slf4j.Slf4j;
import nl.kooi.infrastructure.model.Outcomes;
import nl.kooi.infrastructure.model.Sessions;
import nl.kooi.infrastructure.repository.OutcomesRepository;
import nl.kooi.infrastructure.repository.SessionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/outcomes")
@RestController
@Slf4j

public class OutcomesController {

    @Autowired
    OutcomesRepository outcomesRepository;

    @GetMapping
    public ResponseEntity<List<Outcomes>> findAll() {
        return ResponseEntity.ok(outcomesRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Outcomes outcomes) {
        return ResponseEntity.ok(outcomesRepository.save(outcomes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Outcomes> findById(@PathVariable int id) {
        Optional<Outcomes> stock = outcomesRepository.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Outcomes> update(@PathVariable int id, @Valid @RequestBody Outcomes outcomes) {
        if (!outcomesRepository.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(outcomesRepository.save(outcomes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (!outcomesRepository.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        outcomesRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
