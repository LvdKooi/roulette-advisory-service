package nl.kooi.app;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories("nl.kooi.infrastructure.repository")
@EntityScan("nl.kooi.infrastructure.entity")
public class RouletteBettingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RouletteBettingSystemApplication.class, args);
    }

}