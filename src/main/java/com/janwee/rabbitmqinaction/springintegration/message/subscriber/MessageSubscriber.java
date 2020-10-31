package com.janwee.rabbitmqinaction.springintegration.message.subscriber;

import com.janwee.rabbitmqinaction.springintegration.entity.Person;
import com.janwee.rabbitmqinaction.springintegration.message.channel.InputChannels;
import com.janwee.rabbitmqinaction.springintegration.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class MessageSubscriber {

    @StreamListener(InputChannels.INPUT_TEXT)
    public void consumeText(String text) {
        log.info("[Message Consumer] : received '{}'.", text);
    }

    @StreamListener(InputChannels.INPUT_PERSON)
    public void consumePerson(@Headers Map<String, Object> header, Person person) {
        log.info("[Message Consumer] : received serialization data of person '{}' with headers : {}.",
                JsonUtil.toJson(person), header);
    }
}
