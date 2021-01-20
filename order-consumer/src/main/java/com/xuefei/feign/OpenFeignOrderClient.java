package com.xuefei.feign;

import com.xuefei.dto.BaseResp;
import com.xuefei.feign.fallback.OpenFeignOrderClientFactory;
import com.xuefei.pojo.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "${feign.client.paymentname}", fallbackFactory = OpenFeignOrderClientFactory.class)
public interface OpenFeignOrderClient {

    @GetMapping("/payment/query/{id}")
    BaseResp<Payment> getById(@PathVariable("id") Long id);

    @GetMapping("/payment/hystrixOk/{id}")
    BaseResp<String> hystrixOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrixTimeOut/{id}")
    BaseResp<String> hystrixTimeOut(@PathVariable("id") Integer id);
}
