package com.janwee.rabbitmqinaction.nativejavaclient;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Sender {
    public static void main(String[] args) throws Exception {
        //create a channel
        Channel channel = ConnectionFactory.createChannel();

        //declare a direct exchange
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("alternate-exchange", Property.ALTERNATE_EXCHANGE_NAME);
        channel.exchangeDeclare(Property.DIRECT_EXCHANGE_NAME, BuiltinExchangeType.DIRECT, false, false, arguments);
        channel.queueDeclare(Property.QUEUE_NAME, false, false, false, null);
        channel.queueBind(Property.QUEUE_NAME, Property.DIRECT_EXCHANGE_NAME, Property.ROUTABLE_KEY_1);

        //declare an fanout alternate exchange and its queue which stores unroutable messages
        channel.exchangeDeclare(Property.ALTERNATE_EXCHANGE_NAME, BuiltinExchangeType.FANOUT, false);
        channel.queueDeclare(Property.UNROUTABLE_QUEUE_NAME, false, false, false, null);
        channel.queueBind(Property.UNROUTABLE_QUEUE_NAME, Property.ALTERNATE_EXCHANGE_NAME, "");

        //declare a topic exchange
        channel.exchangeDeclare(Property.TOPIC_EXCHANGE_NAME, BuiltinExchangeType.TOPIC, false);
        channel.queueDeclare(Property.QUEUE_TEXT, false, false, false, null);
        channel.queueBind(Property.QUEUE_TEXT, Property.TOPIC_EXCHANGE_NAME, "text.*");

        //purge messages
        channel.queuePurge(Property.QUEUE_NAME);

        //add return listener
        channel.addReturnListener(callback ->
                System.out.printf("message published to exchange : %s with routing key : %s get a reply text : %s\n",
                        callback.getExchange(), callback.getRoutingKey(), callback.getReplyText()));

        Map<String, Object> headers = new HashMap<>();
        headers.put("sender", "rabbitmq-demo");

        //publish an unroutable message to the direct exchange
        channel.basicPublish(Property.DIRECT_EXCHANGE_NAME, Property.UNROUTABLE_KEY, false,
                new AMQP.BasicProperties.Builder()
                        .headers(headers)
                        .timestamp(Date.from(Instant.now()))
                        .messageId("1")
                        .deliveryMode(2)
                        .contentType("text/plain")
                        .userId("janwee")
                        .build(), "Hello,Janwee!".getBytes());

        //publish a routable message to the direct exchange
        channel.basicPublish(Property.DIRECT_EXCHANGE_NAME, Property.ROUTABLE_KEY_1, false,
                new AMQP.BasicProperties.Builder()
                        .headers(headers)
                        .timestamp(Date.from(Instant.now()))
                        .messageId("2")
                        .expiration("60000")
                        .deliveryMode(1)
                        .contentType("text/plain")
                        .userId("janwee")
                        .build(), "How are you,Janwee!".getBytes());

        //publish a message to the topic exchange
        channel.basicPublish(Property.TOPIC_EXCHANGE_NAME, "text.hello", false,
                new AMQP.BasicProperties.Builder()
                        .headers(headers)
                        .timestamp(Date.from(Instant.now()))
                        .messageId("3")
                        .deliveryMode(1)
                        .contentType("text/plain")
                        .userId("janwee")
                        .build(), "This is a greeting message!".getBytes());
        System.out.println("[x] Messages sent.");
    }
}
