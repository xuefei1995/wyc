package com.xuefei.controller;

import com.xuefei.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {

    @Autowired
    private IMessageProvider messageProvider;

    @GetMapping("/send")
    public void sendMessage() {
        messageProvider.send();
    }

}
