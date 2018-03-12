package com.lipenglong.lspring.context;

import org.apache.log4j.Logger;
import com.lipenglong.lspring.annotation.*;
import com.lipenglong.lspring.context.entity.BeanEntity;
import com.lipenglong.lspring.context.entity.ParamEntity;
import com.lipenglong.lspring.exception.BeanNameAlreadyExistsExcption;
import com.lipenglong.lspring.util.XMLParser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lspring配置构建类
 * User: lipl
 * Date: 12-7-2
 * Time: 下午10:10
 * To change this template use File | Settings | File Templates.
 */
class ContextBuilder {
    private static Logger logger = Logger.getLogger(ContextBuilder.class);

    BeanContext beanContext = new BeanContext();
    String aopPackage = null;

    /**
     * 初始化applicationContext方法，实现bean的初始化、依赖注入、aop等
     *
     * @param configLocation 配置文件
     */
    public void initApplicationContext(String configLocation) {
        Map<String, Integer> methodNameMap = new HashMap<String, Integer>();
        List<BeanEntity> beanList = null;
        List<String> annotationPackage = null;
        try {
            XMLParser xmlParser = new XMLParser();
            beanList = xmlParser.getAllBeansConfigure(configLocation);
            aopPackage = xmlParser.getAopPackageConfigure(configLocation);
            annotationPackage = xmlParser.getAnnotationPackageConfigure(configLocation);
            beanContext.setBeanConfigureList(beanList);
            beanContext.setAopPackage(aopPackage);
            beanContext.setAnnotationPackage(annotationPackage);
            if (beanList != null) {
                for (BeanEntity bean : beanList) {
                    if (beanContext.getBeanForInit(bean.getName()) != null) {
                        logger.error("repeating bean error! this is also a bean named '"
                                + bean.getName() + "', please check your configuration file :"
                                + configLocation);
                        return;
                    }

                    String beanClass = bean.getClassName();
                    Class clazz = Class.forName(beanClass);
                    Object beanObject = clazz.newInstance();

                    if (bean.getParamList() != null) {
                        List<ParamEntity> params = bean.getParamList();
                        Map<String, ParamEntity> paramMap = new HashMap<String, ParamEntity>();
                        for (ParamEntity param : params) {
                            paramMap.put(param.getParamName(), param);
                        }

                        Method[] methods = clazz.getMethods();
                        for (int i = 0; i < methods.length; i++) {
                            methodNameMap.put(methods[i].getName(), i);
                        }

                        Field[] fields = clazz.getDeclaredFields();
                        for (Field field : fields) {
                            String fieldName = field.getName();
                            if (paramMap.keySet().contains(fieldName)) {
                                String setMethodName = "set" + (fieldName.length() > 1 ?
                                        fieldName.substring(0, 1).toUpperCase() +
                                                fieldName.substring(1) : fieldName.toUpperCase());
                                if (methodNameMap.keySet().contains(setMethodName)) {
                                    Method setMethod = methods[methodNameMap.get(setMethodName)];
                                    ParamEntity paramTemp = paramMap.get(fieldName);
                                    if (setMethod != null) {
                                        Object arg = null;
                                        if (paramTemp.getParamRef() != null) {
                                            arg = beanContext.getBeanForInit(paramTemp.getParamRef());
                                            if (arg.getClass().getPackage().getName()
                                                    .contains(aopPackage)) {
                                                arg = beanContext.getBean(
                                                        paramTemp.getParamRef());
                                            }
                                        } else if (paramTemp.getParamValue() != null) {
                                            arg = paramTemp.getParamValue();
                                        }
                                        setMethod.invoke(beanObject, arg);
                                    }
                                }
                            }
                        }
                    }
                    beanContext.putBean(bean.getName(), beanObject);
                }
            }

            initAnnotationBeanDI(annotationPackage);

            logger.info("init application context success. defining beans:"
                    + beanContext.getInitBeans());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (BeanNameAlreadyExistsExcption e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * 注解bean的依赖注入初始化
     *
     * @param annotationPackage 注解扫描包路径列表
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws BeanNameAlreadyExistsExcption
     */
    private void initAnnotationBeanDI(List<String> annotationPackage) throws ClassNotFoundException, IllegalAccessException, InstantiationException, BeanNameAlreadyExistsExcption {
        if (annotationPackage != null && annotationPackage.size() > 0) {
            ComponentScanner scanner = new ComponentScanner();

            for (String packageStr : annotationPackage) {
                List<String> classList = scanner.scan(packageStr);
                for (String classPath : classList) {
                    Class clazz = Thread.currentThread().getContextClassLoader()
                            .loadClass(classPath);
                    Annotation annotation = clazz.getAnnotation(Persistance.class);
                    if (annotation == null) {
                        continue;
                    }
                    Persistance persistance = (Persistance) annotation;
                    String beanName = persistance.name();
                    if ("".equals(beanName)) {
                        String className = clazz.getName();
                        beanName = className.substring(0, 1).toLowerCase()
                                + className.substring(1, className.length());
                    }

                    if (beanContext.getBeanForInit(beanName) != null) {
                        logger.error("repeating bean error! this is also a bean named '"
                                + beanName + "', please specify another name for "
                                + clazz);
                        throw new BeanNameAlreadyExistsExcption();
                    }

                    Object beanObject = clazz.newInstance();
                    injectProcess(clazz, beanObject);
                    beanContext.putBean(beanName, beanObject);
                }
            }

            for (String packageStr : annotationPackage) {
                List<String> classList = scanner.scan(packageStr);
                for (String classPath : classList) {
                    Class clazz = Thread.currentThread().getContextClassLoader()
                            .loadClass(classPath);
                    Annotation annotation = clazz.getAnnotation(Service.class);
                    if (annotation == null) {
                        continue;
                    }
                    Service service = (Service) annotation;
                    String beanName = service.name();
                    if ("".equals(beanName)) {
                        String className = clazz.getName();
                        beanName = className.substring(0, 1).toLowerCase()
                                + className.substring(1, className.length());
                    }

                    if (beanContext.getBeanForInit(beanName) != null) {
                        logger.error("repeating bean error! this is also a bean named '"
                                + beanName + "', please specify another name for "
                                + clazz);
                        throw new BeanNameAlreadyExistsExcption();
                    }

                    Object beanObject = clazz.newInstance();
                    injectProcess(clazz, beanObject);
                    beanContext.putBean(beanName, beanObject);
                }
            }

            for (String packageStr : annotationPackage) {
                List<String> classList = scanner.scan(packageStr);
                for (String classPath : classList) {
                    Class clazz = Thread.currentThread().getContextClassLoader()
                            .loadClass(classPath);
                    Annotation annotation = clazz.getAnnotation(Action.class);
                    if (annotation == null) {
                        continue;
                    }
                    Action action = (Action) annotation;
                    String beanName = action.name();
                    if ("".equals(beanName)) {
                        String className = clazz.getName();
                        beanName = className.substring(0, 1).toLowerCase()
                                + className.substring(1, className.length());
                    }

                    if (beanContext.getBeanForInit(beanName) != null) {
                        logger.error("repeating bean error! this is also a bean named '"
                                + beanName + "', please specify another name for "
                                + clazz);
                        throw new BeanNameAlreadyExistsExcption();
                    }

                    Object beanObject = clazz.newInstance();
                    injectProcess(clazz, beanObject);
                    beanContext.putBean(beanName, beanObject);
                }
            }
        }
    }

    /**
     * 注解方式的依赖注入实现
     *
     * @param clazz
     * @param beanObject
     * @throws IllegalAccessException
     */
    private void injectProcess(Class clazz, Object beanObject) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Annotation fieldAnnotation = field.getAnnotation(Inject.class);
            if (fieldAnnotation != null) {
                Inject inject = (Inject) fieldAnnotation;
                String paramName = inject.name();
                Object arg = null;
                if (!"".equals(paramName)) {
                    arg = beanContext.getBeanForInit(paramName);
                } else {
                    if (field.getType().isInterface()) {
                        arg = beanContext.getBeanByInterface(field.getType());
                    } else {
                        arg = beanContext.getBeanByClass(field.getType());
                    }
                }
                if (arg.getClass().getPackage().getName()
                        .contains(aopPackage)) {
                    if ("".equals(paramName)) {
                        paramName = beanContext.getBeanNameByClass(arg.getClass());
                    }
                    arg = beanContext.getBean(paramName);
                }
                field.setAccessible(true);
                field.set(beanObject, arg);
            }
        }
    }

}
