package com.janwee.rabbitmqinaction.nativejavaclient;

import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

public class Receiver {
    public static void main(String[] args) throws Exception {
        Channel channel = ConnectionFactory.createChannel();
        channel.queueDeclare(Property.QUEUE_NAME, false, false, false, null);

        System.out.println("[*] Waiting for messages...");
        channel.basicConsume(Property.QUEUE_NAME, false, (consumerTag, delivery) -> {
            String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.printf("[x] Message '%s' received.\n", msg);
        }, consumerTag -> {
        });
    }
}
