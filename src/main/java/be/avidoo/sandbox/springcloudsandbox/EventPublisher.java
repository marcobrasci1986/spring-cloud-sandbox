package be.avidoo.sandbox.springcloudsandbox;

import be.avidoo.sandbox.springcloudsandbox.person.events.DomainEvent;
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

    /**
     * Published the message naar een Exchange (naam van de exchange is configureerbaar in de properties)
     */
    public void sendEvent(DomainEvent domainEvent) {
        log.info("About to send event: " + domainEvent);

        Message<DomainEvent> message = MessageBuilder.withPayload(domainEvent).setHeader("myHeader", "headerValue").build();
        source.output().send(message);
    }
}
