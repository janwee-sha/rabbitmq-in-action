package com.janwee.rabbitmqinaction.springintegration.message.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputChannels {
    String OUTPUT_TEXT = "outputText";

    @Output(OUTPUT_TEXT)
    MessageChannel outputText();
}
