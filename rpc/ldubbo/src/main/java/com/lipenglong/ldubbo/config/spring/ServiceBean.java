package com.lipenglong.ldubbo.config.spring;

import com.lipenglong.ldubbo.config.ServiceConfig;
import org.springframework.beans.factory.InitializingBean;

/**
 * ldubbo service对象类，是暴露服务的入口
 * <p/>
 * Created by lipenglong on 2017/7/21.
 */
public class ServiceBean extends ServiceConfig implements InitializingBean {

    private static final long serialVersionUID = 3083255015367616542L;

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        export();
    }
}
