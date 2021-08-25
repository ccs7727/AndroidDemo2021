package com.example.androiddemo.base;

public class BaseEntity {
    /**
     * 请求数据成功时为0
     */
    public final static int RESULT_SUCCESS = 0;

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
