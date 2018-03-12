package com.lipenglong.lspring.context.entity;

import java.util.List;

/**
 * xml配置bean对象类
 * User: lipl
 * Date: 12-7-3
 * Time: 下午1:31
 * To change this template use File | Settings | File Templates.
 */
public class BeanEntity {
    private String name;
    private String className;
    private List<ParamEntity> paramList;
    private List<String> methodList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ParamEntity> getParamList() {
        return paramList;
    }

    public void setParamList(List<ParamEntity> paramList) {
        this.paramList = paramList;
    }

    public List<String> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<String> methodList) {
        this.methodList = methodList;
    }
}
