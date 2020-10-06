package com.janwee.rabbitmqinaction;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class RabbitmqInActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqInActionApplication.class, args);
    }

    @Bean
    public Consumer<Person> log() {
        return p -> System.out.printf("Received : %s\n", p);
    }

    @Getter
    @Setter
    public static class Person {
        private String name;

        @Override
        public String toString() {
            return this.name;
        }
    }

}
