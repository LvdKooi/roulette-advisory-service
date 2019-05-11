package nl.kooi.app;
import lombok.extern.slf4j.Slf4j;
import nl.kooi.infrastructure.repository.SessionsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class RouletteBettingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RouletteBettingSystemApplication.class, args);
    }

}