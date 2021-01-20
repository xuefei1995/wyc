package com.xuefei.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xuefei.dao.PaymentDao;
import com.xuefei.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xuefei.pojo.Payment;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public Payment getByPaymentId(long paymentId) {
        return paymentDao.selectByPrimaryKey(paymentId);
    }

    @Override
    public void insertPayment(Payment payment) {
        paymentDao.insert(payment);
    }

    @Override
    public String hystrixOk(int id) {
        return "线程" + Thread.currentThread().getName() + "hystrixOk, id = " + id;
    }

    @HystrixCommand(fallbackMethod = "hystrixTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    @Override
    public String hystrixTimeOut(int id) {
//        int i = 1/0;
        try {
            TimeUnit.SECONDS.sleep(5); //假设目前处理3秒服务提供方认为正常
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程" + Thread.currentThread().getName() + "hystrixTimeOut, id = " + id;
    }

    //服务超时
    private String hystrixTimeOutHandler(int id) {
        return "线程" + Thread.currentThread().getName() + "调用服务超时或者异常, id = " + id;
    }

    //代表10秒内5次请求只要失败率达到百分之60服务就会熔断，过一段时间后才会恢复
    @HystrixCommand(fallbackMethod = "hystrixCircuitBreakerHandler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //开启熔断器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //失败率
    })
    @Override
    public String hystrixCircuitBreaker(int id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数，" + IdUtil.simpleUUID());
        }
        System.out.println("=========================================");
        return "线程" + Thread.currentThread().getName() + "测试熔断服务成功, id = " + id;
    }

    //服务熔断
    private String hystrixCircuitBreakerHandler(int id) {
        return "服务熔断，id : " + id;
    }





}
