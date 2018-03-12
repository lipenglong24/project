package com.lipenglong.lspring.context.entity;

/**
 * 参数对象类
 * User: lipl
 * Date: 12-8-6
 * Time: 下午 4:14
 * To change this template use File | Settings | File Templates.
 */
public class ParamEntity {
    private String paramName;
    private String paramRef;
    private String paramValue;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamRef() {
        return paramRef;
    }

    public void setParamRef(String paramRef) {
        this.paramRef = paramRef;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}
