package com.janwee.rabbitmqinaction.springintegration.message.producer;

import com.janwee.rabbitmqinaction.springintegration.entity.Person;
import com.janwee.rabbitmqinaction.springintegration.message.channel.OutputChannels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    private MessageChannel outputText;
    private MessageChannel outputPerson;

    @Autowired
    public MessageProducer(OutputChannels outputChannels) {
        this.outputText = outputChannels.outputText();
        this.outputPerson=outputChannels.outputPerson();
    }

    public void publishText(String text) {
        Message<String> msg = MessageBuilder.withPayload(text).build();
        this.outputText.send(msg);
    }
    public void publishPerson(Person person){
        Message<Person> msg=MessageBuilder.withPayload(person).build();
        this.outputPerson.send(msg);
    }
}
