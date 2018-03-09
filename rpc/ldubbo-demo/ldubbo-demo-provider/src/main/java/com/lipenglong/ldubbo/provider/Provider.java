package com.lipenglong.ldubbo.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ldubbo服务提供者启动类
 * <p/>
 * Created by lipenglong on 2017/7/25.
 */
public class Provider {
    private static Log logger = LogFactory.getLog(Provider.class);

    public static void main(String[] args) throws Exception {
        new ClassPathXmlApplicationContext("applicationContext.xml");
        logger.info(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " ldubbo service server started!");
    }
}
