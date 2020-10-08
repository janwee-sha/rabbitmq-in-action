package com.janwee.rabbitmqinaction.springintegration.configurer;


import com.janwee.rabbitmqinaction.springintegration.message.channel.InputChannels;
import com.janwee.rabbitmqinaction.springintegration.message.channel.OutputChannels;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding({InputChannels.class, OutputChannels.class})
public class StreamBindingConfigurer {
}
