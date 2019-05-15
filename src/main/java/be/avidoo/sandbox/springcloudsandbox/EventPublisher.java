package be.avidoo.sandbox.springcloudsandbox;

import be.avidoo.sandbox.springcloudsandbox.person.events.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@EnableBinding(Source.class)
@Component
@RequiredArgsConstructor
public class EventPublisher {

    private final Source source;
    private final ObjectMapper objectMapper;


    /**
     * Published the message naar een Exchange (naam van de exchange is configureerbaar in de properties)
     */
    public void sendEvent(DomainEvent domainEvent) throws JsonProcessingException {
        log.info("About to send event: " + domainEvent);

        String json = objectMapper.writeValueAsString(domainEvent);

        Message<String> message = MessageBuilder.withPayload(json).setHeader("myHeader", "headerValue").build();
        source.output().send(message);
    }
}
