package com.lipenglong.ldubbo.rpc;

import java.io.Serializable;
import java.util.Arrays;

/**
 * client端发送的请求对象
 * </p>
 * Created by lipenglong on 2017/9/1.
 */
public class Invocation implements Serializable {
    //请求的方法名
    private String methodName;
    //方法所有参数类型
    private Class<?>[] parameterTypes;
    //方法所有参数值
    private Object[] arguments;
    //请求的服务key
    private String serviceKey;
    //请求的序号
    private String serialNo;

    public Invocation(String methodName, Class<?>[] parameterTypes, Object[] arguments) {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.arguments = arguments;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public String toString() {
        return "RpcInvocation{" +
                "methodName='" + methodName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", arguments=" + Arrays.toString(arguments) +
                ", serviceKey='" + serviceKey + '\'' +
                ", serialNo='" + serialNo + '\'' +
                '}';
    }
}
