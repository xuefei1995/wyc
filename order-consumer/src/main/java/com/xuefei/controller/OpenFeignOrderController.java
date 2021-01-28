package com.xuefei.controller;

import com.xuefei.dto.BaseResp;
import com.xuefei.feign.OpenFeignOrderClient;
import com.xuefei.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@DefaultProperties(defaultFallback = "") 通用的全局降级方法
//@RestController
@RequestMapping("/openFeignOrder")
@Slf4j
@RefreshScope
public class OpenFeignOrderController {

    @Value("${conf.info}")
    private String confInfo;

    @Autowired
    private OpenFeignOrderClient openFeignOrderClient;

    @GetMapping("/confInfo")
    public String confInfo() {
        return confInfo;
    }

    @GetMapping("/query/{id}")
    public BaseResp<Payment> getById(@PathVariable("id") Long id) {
        return openFeignOrderClient.getById(id);
    }

    @GetMapping("/hystrixOk/{id}")
    public BaseResp<String> hystrixOk(@PathVariable("id") Integer id) {
        return openFeignOrderClient.hystrixOk(id);
    }

    @GetMapping("/hystrixTimeOut/{id}")
    public BaseResp<String> hystrixTimeOut(@PathVariable("id") Integer id) {
        return openFeignOrderClient.hystrixTimeOut(id);
    }
}
