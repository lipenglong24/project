package com.lipenglong.ldubbo.config.spring.schema;

import com.lipenglong.ldubbo.config.spring.ServiceBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * ldubbo bean xml配置文件解析demo类
 * <p/>
 * Created by lipenglong on 2017/7/21.
 */
public class BeanDefinitionParserDemo extends AbstractSimpleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return ServiceBean.class;
    }
}
