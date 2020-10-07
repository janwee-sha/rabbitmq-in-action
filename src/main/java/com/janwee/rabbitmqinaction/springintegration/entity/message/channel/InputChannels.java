package com.janwee.rabbitmqinaction.springintegration.entity.message.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputChannels {
    String INPUT_TEXT = "inputText";

    @Input(INPUT_TEXT)
    SubscribableChannel inputText();
}
