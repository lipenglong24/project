package com.lipenglong.lspring.context;

import org.apache.log4j.Logger;
import com.lipenglong.lspring.context.entity.BeanEntity;
import com.lipenglong.lspring.proxy.BeanProxyCreator;

import java.util.*;

/**
 * bean容器类
 * User: lipl
 * Date: 12-7-2
 * Time: 下午10:20
 * To change this template use File | Settings | File Templates.
 */
class BeanContext {
    private static Map<String, Object> beanMap = new HashMap<String, Object>();
    private static List<BeanEntity> beanConfigureList = null;
    private static String aopPackage = null;
    private static List<String> annotationPackage = null;

    private Object beanObject = null;
    private BeanEntity beanVO = null;

    private static Logger logger = Logger.getLogger(BeanContext.class);

    void putBean(String beanName, Object beanObject) {
        this.beanMap.put(beanName, beanObject);
    }

    Object getBean(String beanName) {
        beanObject = beanMap.get(beanName);
        if (beanObject != null && aopPackage != null &&
                beanObject.getClass().getPackage().getName().contains(aopPackage)) {
            for (BeanEntity vo : beanConfigureList) {
                if (vo.getName().equals(beanName)) {
                    beanVO = vo;
                    break;
                }
            }
            return new BeanProxyCreator().proxy(beanObject, beanVO, beanMap);
        }
        return beanObject;
    }

    Object getBeanForInit(String beanName) {
        return beanMap.get(beanName);
    }

    void setBeanConfigureList(List<BeanEntity> list) {
        beanConfigureList = list;
    }

    void setAopPackage(String aopPackage) {
        BeanContext.aopPackage = aopPackage;
    }

    String getInitBeans() {
        StringBuffer initBeans = new StringBuffer("[");
        Set<String> beanNames = beanMap.keySet();
        for (String name : beanNames) {
            initBeans.append(name + ",");
        }
        if (initBeans.length() > 1) {
            initBeans.deleteCharAt(initBeans.length() - 1);
        }
        initBeans.append("]");
        return initBeans.toString();
    }

    void setAnnotationPackage(List<String> annotationPackage) {
        BeanContext.annotationPackage = annotationPackage;
    }

    public Object getBeanByInterface(Class<?> type) {
        for (Iterator iterator = beanMap.keySet().iterator();
             iterator.hasNext(); ) {
            Object beanObj = beanMap.get(iterator.next());
            Class[] implInterfaces = beanObj.getClass().getInterfaces();
            for (Class temp : implInterfaces) {
                if (type.equals(temp)) {
                    return beanObj;
                }
            }
        }
        return null;
    }

    public Object getBeanByClass(Class<?> type) {
        for (Iterator iterator = beanMap.keySet().iterator();
             iterator.hasNext(); ) {
            Object beanObj = beanMap.get(iterator.next());
            if (beanObj.getClass().equals(type)) {
                return beanObj;
            }
        }
        return null;
    }

    public String getBeanNameByClass(Class<?> aClass) {
        for (Iterator<String> iterator = beanMap.keySet().iterator();
             iterator.hasNext(); ) {
            String beanName = iterator.next();
            Object beanObj = beanMap.get(beanName);
            if (beanObj.getClass().equals(aClass)) {
                return beanName;
            }
        }
        return null;
    }
}
