package com.lipenglong.lspring.context;

/**
 * lspring 核心项目配置类，提供getBean接口
 * User: lipl
 * Date: 12-7-2
 * Time: 下午10:07
 * To change this template use File | Settings | File Templates.
 */
public class Context {

    private static BeanContext beanContext = new BeanContext();

    /**
     * 创建Context对象，加载配置文件
     *
     * @param xmlFile xml配置文件
     */
    public Context(String xmlFile) {
        String configLocation = Thread.currentThread().getContextClassLoader().getResource(xmlFile).getFile();
        new ContextBuilder().initApplicationContext(configLocation);
    }

    /**
     * 获取bean对象
     *
     * @param beanName bean名称
     * @return bean对象
     */
    public Object getBean(String beanName) {
        return beanContext.getBean(beanName);
    }
}
