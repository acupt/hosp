package com.acupt.domain.enums;

/**
 * Created by liujie on 2017/9/15.
 */
public enum CodeEnum {

    OK(0, "ok"),
    NO_LOGIN(1001, "未登录"),
    NO_AUTH(1002, "无权限"),
    USER_INFO_ILLEGAL(1003, "用户信息错误"),
    USER_NOT_EXIST(1004, "用户不存在"),
    PWD_ERR(1005, "密码错误"),
    SYS_ERR(2001, "系统异常"),
    PARAM_ERR(4001, "参数错误"),
    PARAM_MISS(4002, "缺少参数"),
    RECORD_DUPLICATE(5001, "记录已存在"),
    RECORD_NOT_EXIST(5002, "记录不存在");

    private int code;

    private String msg;//给前端的默认提示

    CodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
