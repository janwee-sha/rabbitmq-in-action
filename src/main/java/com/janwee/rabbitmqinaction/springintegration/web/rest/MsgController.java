package com.janwee.rabbitmqinaction.springintegration.web.rest;

import com.janwee.rabbitmqinaction.springintegration.message.producer.MessageProducer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class MsgController {
    private MessageProducer msgProducer;

    @Autowired
    public MsgController(MessageProducer msgProducer) {
        this.msgProducer = msgProducer;
    }

    @GetMapping("/greeting")
    @ApiOperation("send a greeting message to queue text")
    @ResponseBody
    public void greeting() {
        msgProducer.publishText();
    }
}
