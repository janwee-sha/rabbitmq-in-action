package com.janwee.rabbitmqinaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.Assert;

import java.util.Arrays;

@SpringBootTest(classes = RabbitmqInActionApplication.class)
@Import(TestChannelBinderConfiguration.class)
class RabbitmqInActionApplicationTests {
    @Autowired
    private InputDestination input;
    @Autowired
    private OutputDestination output;

    //    @Test
    void contextLoads() {
        input.send(new GenericMessage<>("hello".getBytes()));
        Assert.isTrue(Arrays.equals(output.receive().getPayload(), "HELLO".getBytes()), "[x] Stream Binder " +
                "Tester : message converting error.");
    }
}
