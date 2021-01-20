package com.xuefei.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseRespEumn {

    SUCCESS(0L,"操作成功！"),
    NEED_LOGIN_ERROR(210100004L, "请先登录！"),
    SIGN_ERROR(210100000L, "鉴权失败！"),
    UNKNOWN_SYSTEM_ERROR(210199999L, "未知系统错误！"),
    LOGIN_ERROR(210100001L, "登录失败,用户名密码错误！"),
    POWER_ERROR(210100002L, "当前用户权限不足！"),
    FILE_ERROR(210100003L, "未选择文件！"),
    PARAM_ERROR(210100004L, "参数校验异常！");

    private Long code;
    private String name;

}
