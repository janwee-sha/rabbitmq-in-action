package com.janwee.rabbitmqinaction.springintegration.web.rest;

import com.janwee.rabbitmqinaction.springintegration.entity.Person;
import com.janwee.rabbitmqinaction.springintegration.message.publisher.MessagePublisher;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msg")
public class MsgController {
    private MessagePublisher msgProducer;

    @Autowired
    public MsgController(MessagePublisher msgProducer) {
        this.msgProducer = msgProducer;
    }

    @GetMapping("/text/{text}")
    @ApiOperation("send a plain text message to queue text")
    @ResponseBody
    public void sendText(@ApiParam(value = "*plain text message", required = true) @PathVariable("text") String text) {
        msgProducer.publishText(text);
    }

    @PostMapping("/person")
    @ApiOperation("send a object serialization data to queue person")
    @ResponseBody
    public void sendPerson(@ApiParam(value = "*person entity", required = true) @RequestBody Person person) {
        msgProducer.publishPerson(person);
    }
}
