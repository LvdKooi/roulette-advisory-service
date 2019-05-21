package nl.kooi.infrastructure.repository;


import lombok.RequiredArgsConstructor;
import nl.kooi.infrastructure.model.Sessions;
import nl.kooi.infrastructure.repository.SessionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface SessionsService {

    public List<Sessions> findAll();

    public Optional<Sessions> findById(int id);

    public Sessions save(Sessions session);

    public void deleteById(int id);
}
