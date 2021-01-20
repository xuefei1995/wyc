package com.xuefei.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 
 * 支付表
 */
@Data
public class Payment implements Serializable {
    /**
     * 主键ID
     */
    private Long paymentId;

    /**
     * 流水号
     */
    private String serial;

    private static final long serialVersionUID = 1L;
}