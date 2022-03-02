package cn.ruoshy.platform.util;

import com.alibaba.fastjson.JSON;

public class Result<T> {

    private int code;

    private T data;

    private String msg;

    public Result() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toJSONString() {
        return JSON.toJSONString(this);
    }
}
