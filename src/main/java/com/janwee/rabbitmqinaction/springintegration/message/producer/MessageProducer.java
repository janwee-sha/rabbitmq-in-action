package com.janwee.rabbitmqinaction.springintegration.message.producer;

import com.janwee.rabbitmqinaction.springintegration.message.channel.OutputChannels;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    private MessageChannel outputText;

    public MessageProducer(OutputChannels outputChannels) {
        this.outputText = outputChannels.outputText();
    }

    public void publishText() {
        String text = "hello";
        Message<String> msg = MessageBuilder.withPayload(text).build();
        this.outputText.send(msg);
    }
}
