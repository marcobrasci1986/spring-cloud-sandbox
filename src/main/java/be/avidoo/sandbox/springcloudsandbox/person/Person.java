package be.avidoo.sandbox.springcloudsandbox.person;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class Person {

    private final UUID id;
    private String name;
    private State state = State.INITIALIZED;

    public Person(UUID id) {
        this.id = id;
    }

    Person changeName(String newName) {
        if (isDeactivated()) {
            throw new IllegalStateException("Cannot change name of a deactivated person");
        }
        this.name = newName;
        return this;
    }

    Person activate() {
        if (isActivated()) {
            throw new IllegalStateException("Cannot activate an activated Person");
        }
        this.state = State.ACTIVE;
        return this;
    }

    Person deactivate() {
        if (isDeactivated()) {
            throw new IllegalStateException("Cannot deactivate a deactivated Person");
        }
        this.state = State.DEACTIVATED;
        return this;
    }

    Boolean isActivated() {
        return this.state == State.ACTIVE;
    }

    Boolean isDeactivated() {
        return this.state == State.DEACTIVATED;
    }

    enum State {
        INITIALIZED, ACTIVE, DEACTIVATED
    }
}
