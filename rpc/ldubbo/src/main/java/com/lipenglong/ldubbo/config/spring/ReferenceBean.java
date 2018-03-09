package com.lipenglong.ldubbo.config.spring;

import com.lipenglong.ldubbo.config.ReferenceConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * com.lipenglong.ldubbo.config.spring.ReferenceBean
 * </p>
 * Created by lipenglong on 2017/8/30.
 */
public class ReferenceBean extends ReferenceConfig implements FactoryBean {

    private static final long serialVersionUID = -2278602690695158523L;

    @Override
    public Object getObject() throws Exception {
        return get();
    }

    @Override
    public Class<?> getObjectType() {
        return getInterfaceClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
