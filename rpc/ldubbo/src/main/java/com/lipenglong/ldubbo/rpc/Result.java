package com.lipenglong.ldubbo.rpc;

import java.io.Serializable;

/**
 * server端返回的结果对象
 * </p>
 * Created by lipenglong on 2017/8/29.
 */
public class Result implements Serializable {
    //返回值
    private Object result;
    //序号
    private String serialNo;

    public Result(Object result, String serialNo) {
        this.result = result;
        this.serialNo = serialNo;
    }

    public Object getValue() {
        return result;
    }

    public String serialNo() {
        return serialNo;
    }

    @Override
    public String toString() {
        return "RpcResult{" +
                "result=" + result +
                ", serialNo='" + serialNo + '\'' +
                '}';
    }
}
