package com.xuefei.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding({Sink.class, Source.class})
public class MessageOrderController {

    //接受消息
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("收到的消息为：" + message.getPayload());
    }

}
