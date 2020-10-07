package com.janwee.rabbitmqinaction.springintegration.message.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputChannels {
    String INPUT_TEXT = "inputText";
    String INPUT_PERSON = "inputPerson";

    @Input(INPUT_TEXT)
    SubscribableChannel inputText();

    @Input(INPUT_PERSON)
    SubscribableChannel inputPerson();
}
