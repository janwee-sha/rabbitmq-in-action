package com.janwee.rabbitmqinaction.springintegration.message.consumer;

import com.janwee.rabbitmqinaction.springintegration.entity.Person;
import com.janwee.rabbitmqinaction.springintegration.message.channel.InputChannels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    @StreamListener(InputChannels.INPUT_TEXT)
    public void consumeText(String text) {
        log.info("[Message Consumer] : received '{}'.", text);
    }

    @StreamListener(InputChannels.INPUT_PERSON)
    public void consumePerson(Person person) {
        log.info("[Message Consumer] : received serialization data of person '{}'.", person);
    }
}
