package com.janwee.rabbitmqinaction.springintegration.message.consumer;

import com.janwee.rabbitmqinaction.springintegration.entity.Person;
import com.janwee.rabbitmqinaction.springintegration.message.channel.InputChannels;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @StreamListener(InputChannels.INPUT_TEXT)
    public void consumeText(String text) {
        System.out.printf("Text Message Consumer : received '%s'.\n", text);
    }

    @StreamListener(InputChannels.INPUT_PERSON)
    public void consumePerson(Person person) {
        System.out.printf("Person Message Consumer : saving info of person '%s'.\n", person);
    }
}
