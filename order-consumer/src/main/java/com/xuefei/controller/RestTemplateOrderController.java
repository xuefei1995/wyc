package com.xuefei.controller;

import com.xuefei.dto.BaseResp;
import com.xuefei.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/restTemplateOrder")
@Slf4j
public class RestTemplateOrderController {

    public static final String PAYMENT_URL = "http://payment-provider/";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public BaseResp<Void> createPayment(@RequestBody Payment payment) {
        log.info("消费者入参, payment = " + payment.toString());
        return restTemplate.postForObject(PAYMENT_URL + "payment/create", payment, BaseResp.class);
    }

    @GetMapping("/query/{id}")
    public BaseResp<Payment> getById(@PathVariable("id") Long id) {
        log.info("消费者入参, id = " + id);
        return restTemplate.getForObject(PAYMENT_URL + "payment/query/" + id, BaseResp.class);
    }

}
