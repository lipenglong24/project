package com.lipenglong.ldubbo.config.spring.schema;

import com.lipenglong.ldubbo.config.ProtocolConfig;
import com.lipenglong.ldubbo.config.RegistryConfig;
import com.lipenglong.ldubbo.config.spring.ReferenceBean;
import com.lipenglong.ldubbo.config.spring.ServiceBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 命名空间Handler类，spring框架的调用入口
 * <p/>
 * Created by lipenglong on 2017/7/21.
 */
public class LdubboNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("protocol", new LdubboBeanDefinitionParser(ProtocolConfig.class));
        registerBeanDefinitionParser("service", new LdubboBeanDefinitionParser(ServiceBean.class));
        registerBeanDefinitionParser("registry", new LdubboBeanDefinitionParser(RegistryConfig.class));
        registerBeanDefinitionParser("reference", new LdubboBeanDefinitionParser(ReferenceBean.class));
    }
}
