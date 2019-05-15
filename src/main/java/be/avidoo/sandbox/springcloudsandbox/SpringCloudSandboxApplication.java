package be.avidoo.sandbox.springcloudsandbox;

import be.avidoo.sandbox.springcloudsandbox.person.events.PersonCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class SpringCloudSandboxApplication {

    @Autowired
    private EventPublisher eventPublisher;

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSandboxApplication.class, args);
    }


    @Scheduled(fixedDelay = 2500L)
    public void generateEvents() throws JsonProcessingException {
        log.info("Generating new Event");

        PersonCreatedEvent personCreatedEvent = PersonCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("John")
                .when(Instant.now())
                .build();

        eventPublisher.sendEvent(personCreatedEvent);
    }

}
