package be.avidoo.sandbox.springcloudsandbox.person.events;

import java.time.Instant;

public interface DomainEvent {
    Instant occuredAt();
}
