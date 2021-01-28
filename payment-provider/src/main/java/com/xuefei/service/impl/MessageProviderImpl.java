package com.xuefei.service.impl;

import com.xuefei.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class) //定义消息推送管道
public class MessageProviderImpl implements IMessageProvider {


    @Qualifier("output")
    @Autowired
    private MessageChannel messageChannel; //消息发送管道

    @Override
    public void send() {
        String seq = "hello world";
        messageChannel.send(MessageBuilder.withPayload(seq).build());
        System.out.println("message ok");
    }
}
