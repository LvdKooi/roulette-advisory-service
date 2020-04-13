package nl.kooi.app.api;

import lombok.extern.slf4j.Slf4j;
import nl.kooi.infrastructure.entity.OutcomeEntity;
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
    public ResponseEntity<List<OutcomeEntity>> findAll() {
        return ResponseEntity.ok(outcomeRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody OutcomeEntity outcomeEntity) {
        return ResponseEntity.ok(outcomeRepository.save(outcomeEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutcomeEntity> findById(@PathVariable int id) {
        Optional<OutcomeEntity> stock = outcomeRepository.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutcomeEntity> update(@PathVariable int id, @Valid @RequestBody OutcomeEntity outcomeEntity) {
        if (!outcomeRepository.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(outcomeRepository.save(outcomeEntity));
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
