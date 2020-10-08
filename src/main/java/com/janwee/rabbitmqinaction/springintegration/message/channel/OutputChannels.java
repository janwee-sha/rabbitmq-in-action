package com.janwee.rabbitmqinaction.springintegration.message.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputChannels {
    String OUTPUT_TEXT = "outputText";
    String OUTPUT_PERSON = "outputPerson";

    @Output(OUTPUT_TEXT)
    MessageChannel outputText();

    @Output(OUTPUT_PERSON)
    MessageChannel outputPerson();
}
