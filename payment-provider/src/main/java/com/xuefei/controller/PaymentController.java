package com.xuefei.controller;

import com.xuefei.dto.BaseResp;
import com.xuefei.dto.BaseRespEumn;
import com.xuefei.service.IPaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import com.xuefei.pojo.Payment;

import java.util.List;


//@RestController
@RequestMapping("/payment")
@CrossOrigin
@Slf4j
@RefreshScope //自动刷新
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Value("${conf.info}")
    private String confInfo;

    //配置服务发现
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/confInfo")
    public String confInfo() {
        return confInfo;
    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service:" + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("payment-provider");
        for (ServiceInstance instance : instances) {
            log.info("serviceId:" + instance.getServiceId() + ",host," + instance.getHost() + ",port,"
                    + instance.getPort() + ",uri," + instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/query/{id}")
    public BaseResp<Payment> getById(@PathVariable("id") Long id) {
        log.info("调用port:" + serverPort);
        BaseResp<Payment> baseResp = new BaseResp<>();
        try {
            Payment payment = paymentService.getByPaymentId(id);
            baseResp.setResult(payment);
        } catch (Exception e) {
            baseResp.setSuccess(false);
            baseResp.setCode(BaseRespEumn.UNKNOWN_SYSTEM_ERROR.getCode());
            baseResp.setMessage(BaseRespEumn.UNKNOWN_SYSTEM_ERROR.getName() + e.getMessage());
        }
        return baseResp;
    }

    @PostMapping("/create")
    public BaseResp<Void> createPayment(@RequestBody Payment payment) {
        log.info("调用port:" + serverPort);
        BaseResp<Void> baseResp = new BaseResp<>();
        try {
            paymentService.insertPayment(payment);
        } catch (Exception e) {
            baseResp.setSuccess(false);
            baseResp.setCode(BaseRespEumn.UNKNOWN_SYSTEM_ERROR.getCode());
            baseResp.setMessage(BaseRespEumn.UNKNOWN_SYSTEM_ERROR.getName() + e.getMessage());
        }
        return baseResp;
    }

    @GetMapping("/hystrixOk/{id}")
    public BaseResp<String> hystrixOk(@PathVariable("id") Integer id) {
        log.info("调用port:" + serverPort);
        BaseResp<String> baseResp = new BaseResp<>();
        try {
            String result = paymentService.hystrixOk(id);
            baseResp.setResult(result);
        } catch (Exception e) {
            baseResp.setSuccess(false);
            baseResp.setCode(BaseRespEumn.UNKNOWN_SYSTEM_ERROR.getCode());
            baseResp.setMessage(BaseRespEumn.UNKNOWN_SYSTEM_ERROR.getName() + e.getMessage());
        }
        return baseResp;
    }

    @GetMapping("/hystrixTimeOut/{id}")
    public BaseResp<String> hystrixTimeOut(@PathVariable("id") Integer id) {
        log.info("调用port:" + serverPort);
        BaseResp<String> baseResp = new BaseResp<>();
        try {
            String result = paymentService.hystrixTimeOut(id);
            baseResp.setResult(result);
        } catch (Exception e) {
            baseResp.setSuccess(false);
            baseResp.setCode(BaseRespEumn.UNKNOWN_SYSTEM_ERROR.getCode());
            baseResp.setMessage(BaseRespEumn.UNKNOWN_SYSTEM_ERROR.getName() + e.getMessage());
        }
        return baseResp;
    }

    @GetMapping("/hystrixCircuitBreaker/{id}")
    public BaseResp<String> hystrixCircuitBreaker(@PathVariable("id") Integer id) {
        log.info("调用port:" + serverPort);
        BaseResp<String> baseResp = new BaseResp<>();
        try {
            String result = paymentService.hystrixCircuitBreaker(id);
            baseResp.setResult(result);
        } catch (Exception e) {
            baseResp.setSuccess(false);
            baseResp.setCode(BaseRespEumn.UNKNOWN_SYSTEM_ERROR.getCode());
            baseResp.setMessage(BaseRespEumn.UNKNOWN_SYSTEM_ERROR.getName() + e.getMessage());
        }
        return baseResp;
    }

}
