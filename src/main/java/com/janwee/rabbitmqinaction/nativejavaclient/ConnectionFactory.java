package com.janwee.rabbitmqinaction.nativejavaclient;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class ConnectionFactory {
    public static Channel createChannel() throws Exception {
        com.rabbitmq.client.ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
        factory.setHost(Property.HOST_NAME);
        factory.setUsername(Property.USER_NAME);
        factory.setPassword(Property.PASSWORD);
        Connection connection = factory.newConnection();
        return connection.createChannel();
    }
}
