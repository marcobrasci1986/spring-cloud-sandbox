package be.avidoo.sandbox.springcloudsandbox.person;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonTest {

    private Person person;

    @Before
    public void setUp() {
        person = new Person(UUID.randomUUID());
    }

    @Test
    public void newPersonIsInitialized() {
        assertThat(person.getState(), is(Person.State.INITIALIZED));
    }

    @Test(expected = IllegalStateException.class)
    public void deactivatedPersonCannotChangeName() {
        person.deactivate();

        person.changeName("John");
    }

    @Test
    public void activatedPersonCanChangeName() {
        person.activate();

        person.changeName("John");

        assertThat(person.getName(), is("John"));
    }

    @Test
    public void newPersonCanBeActivated() {
        person.activate();

        assertThat(person.isActivated(), is(Boolean.TRUE));
    }

    @Test
    public void activatedUserCanBeDeactivated() {
        person.activate();

        person.deactivate();

        assertThat(person.isDeactivated(), is(Boolean.TRUE));
    }

    @Test(expected = IllegalStateException.class)
    public void activatedPersonCannotBeActivated() {
        person.activate();

        person.activate();
    }

    @Test(expected = IllegalStateException.class)
    public void deactivatedPersonCannotBeDeactivated() {
        person.deactivate();

        person.deactivate();
    }
}
