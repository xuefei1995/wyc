package com.xuefei.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class BaseResp<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 结果编码 */
    private Long code = BaseRespEumn.SUCCESS.getCode();

    /** 信息 */
    private String message = BaseRespEumn.SUCCESS.getName();

    private Boolean success = true;

    /** 数据对象 */
    private T result;
}
