package be.avidoo.sandbox.springcloudsandbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@EnableBinding(Source.class)
public class PublishingController {

    @Autowired
    private Source source;


    @GetMapping
    public String sendMessage() {
        System.out.println("send message");

        PersonCreatedEvent personCreatedEvent = PersonCreatedEvent.builder().id(UUID.randomUUID()).firstname("John").lastname("Doe").build();

        this.source.output().send(MessageBuilder.withPayload(personCreatedEvent).setHeader("myHeader", "headervalue").build());

        return "success";
    }
}
