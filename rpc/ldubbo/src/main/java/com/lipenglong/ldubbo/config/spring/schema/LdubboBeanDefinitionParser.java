package com.lipenglong.ldubbo.config.spring.schema;

import com.lipenglong.ldubbo.config.ProtocolConfig;
import com.lipenglong.ldubbo.config.RegistryConfig;
import com.lipenglong.ldubbo.config.spring.ServiceBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import java.lang.reflect.Method;

/**
 * ldubbo bean xml配置文件解析类，将信息放入spring容器
 * <p/>
 * Created by lipenglong on 2017/7/21.
 */
public class LdubboBeanDefinitionParser implements BeanDefinitionParser {
    private final Class<?> beanClass;

    public LdubboBeanDefinitionParser(Class<?> serviceBeanClass) {
        this.beanClass = serviceBeanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        //判断bean的id是否赋值
        String id = element.getAttribute("id");
        if (StringUtils.isEmpty(id)) {
            if (ServiceBean.class.equals(beanClass)) {
                id = element.getAttribute("interface");
            } else if (ProtocolConfig.class.equals(beanClass)) {
                id = element.getAttribute("name");
            } else {
                id = beanClass.getSimpleName();
            }
        }
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        beanDefinition.getPropertyValues().addPropertyValue("id", id);

        //遍历class的set方法，属性赋值
        for (Method method : beanClass.getMethods()) {
            String name = method.getName();
            if (name.length() > 3 && name.startsWith("set")) {
                String property = name.substring(3, 4).toLowerCase() + name.substring(4);
                String value = element.getAttribute(property);
                if (StringUtils.isEmpty(value)) {
                    if ("protocolConfig".equals(property)) {
                        for (String beanName : parserContext.getRegistry().getBeanDefinitionNames()) {
                            BeanDefinition definition = parserContext.getRegistry().getBeanDefinition(beanName);
                            if (ProtocolConfig.class.getName().equals(definition.getBeanClassName())) {
                                beanDefinition.getPropertyValues().addPropertyValue(property, definition);
                            }
                        }
                    } else if ("registryConfig".equals(property)) {
                        for (String beanName : parserContext.getRegistry().getBeanDefinitionNames()) {
                            BeanDefinition definition = parserContext.getRegistry().getBeanDefinition(beanName);
                            if (RegistryConfig.class.getName().equals(definition.getBeanClassName())) {
                                beanDefinition.getPropertyValues().addPropertyValue(property, definition);
                            }
                        }
                    }
                    continue;
                }
                Object reference;
                if ("ref".equals(property) && parserContext.getRegistry().containsBeanDefinition(value)) {
                    reference = new RuntimeBeanReference(value);
                } else {
                    reference = value;
                }
                beanDefinition.getPropertyValues().addPropertyValue(property, reference);
            }
        }
        return beanDefinition;
    }
}
