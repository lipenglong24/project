package com.lipenglong.lspring.proxy;

import org.apache.log4j.Logger;
import com.lipenglong.lspring.annotation.Transaction;
import com.lipenglong.lspring.context.entity.BeanEntity;
import com.lipenglong.lspring.jdbc.TransactionHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 动态代理handler类
 * User: lipl
 * Date: 12-8-7
 * Time: 下午1:44
 * To change this template use File | Settings | File Templates.
 */
public class BeanProxyCreator implements InvocationHandler {

    private static Logger logger = Logger.getLogger(BeanProxyCreator.class);

    private Object beanObject = null;
    private BeanEntity beanVO = null;
    private Map<String, Object> beanMap = null;

    public Object proxy(Object beanObject, BeanEntity beanVO, Map<String, Object> beanMap) {
        this.beanObject = beanObject;
        this.beanVO = beanVO;
        this.beanMap = beanMap;
        return Proxy.newProxyInstance(beanObject.getClass().getClassLoader(),
                beanObject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        boolean success = true;
        Annotation annotation = beanObject.getClass().getDeclaredMethod(
                method.getName()).getAnnotation(Transaction.class);
        if ((beanVO == null && annotation != null) || (beanVO != null &&
                beanVO.getMethodList() != null && beanVO.getMethodList()
                        .contains(method.getName()))) {
            TransactionHandler transactionHandler =
                    (TransactionHandler) beanMap.get("transactionManager");
            logger.info("--------------------------before call method-----------------------");
            transactionHandler.beginTransaction();
            try{
                result = method.invoke(beanObject, args);
            } catch (Exception e) {
                result = null;
                success = false;
            }
            logger.info("--------------------------after call method-----------------------");
            if (!success) {
                transactionHandler.rollback();
            } else {
                transactionHandler.commit();
            }
        } else {
            result = method.invoke(beanObject, args);
        }
        return result;
    }
}
