package com.xuefei.service;


import com.xuefei.pojo.Payment;

public interface IPaymentService {

    Payment getByPaymentId(long paymentId);

    void insertPayment(Payment payment);

    String hystrixOk(int id);

    String hystrixTimeOut(int id);

    String hystrixCircuitBreaker(int id);

}
