package com.acupt.domain;

import com.acupt.domain.enums.CodeEnum;
import com.acupt.util.GsonUtil;

/**
 * Created by liujie on 2017/8/15.
 */
public class Result<T> {

    /**
     * @see com.acupt.domain.enums.CodeEnum
     */
    private int code;

    private String msg;

    private T data;

    public Result() {
        this(CodeEnum.OK);
    }

    public Result(CodeEnum codeEnum) {
        this(codeEnum.getCode(), codeEnum.getMsg());
    }

    public Result(Throwable e) {
        this(CodeEnum.SYS_ERR.getCode(), CodeEnum.SYS_ERR.getMsg());
    }

    public Result(CodeEnum codeEnum, String msg) {
        this(codeEnum.getCode(), msg);
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return code == 0;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }
}
