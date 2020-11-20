package nl.kooi.app.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.SessionDto;
import nl.kooi.app.domain.service.SessionService;
import nl.kooi.app.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "users/{userId}/sessions")
@RestController
@Slf4j
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public SessionDto create(@PathVariable int userId, @Valid @RequestBody SessionDto sessionDto) {
        if (sessionDto.getUserId() == 0) {
            sessionDto.setUserId(userId);
        }

        if (sessionService.findByUserId(userId).isEmpty()) {
            throw new NotFoundException("User with id " + userId + " does not exist.");
        }

        return Mapper.map(sessionService.save(Mapper.map(sessionDto)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SessionDto findById(@PathVariable int userId, @PathVariable int id) {
        return Mapper.map(sessionService.findByIdAndUserId(id, userId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int userId, @PathVariable int id) {
        sessionService.findByIdAndUserId(id, userId);
        sessionService.deleteById(id);
    }
}
