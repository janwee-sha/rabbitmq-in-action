package com.janwee.rabbitmqinaction;

import com.janwee.rabbitmqinaction.springintegration.entity.Person;
import com.janwee.rabbitmqinaction.springintegration.entity.message.channel.InputChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
@EnableBinding({InputChannels.class})
public class RabbitmqInActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqInActionApplication.class, args);
    }

    @Bean
    public Consumer<Person> logPerson() {
        return p -> System.out.printf("Received : %s\n", p);
    }

    @Bean
    public Function<String, String> handleText() {
        System.out.println("Received message.");
        return String::toUpperCase;
    }
}
