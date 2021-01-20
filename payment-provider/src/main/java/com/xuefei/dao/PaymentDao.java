package com.xuefei.dao;

import com.xuefei.pojo.Payment;
import com.xuefei.pojo.PaymentExample;
import org.apache.ibatis.annotations.Mapper;

/**
 * PaymentDao继承基类
 */
@Mapper
public interface PaymentDao extends MyBatisBaseDao<Payment, Long, PaymentExample> {
}