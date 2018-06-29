package com.lipenglong.portal.web;

import com.lipenglong.portal.web.config.CasProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * portal启动类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
@SpringBootApplication
@ComponentScan(value = {"com.lipenglong.portal.web.config", "com.lipenglong.portal.web.controller", "com.lipenglong.portal.service"})
@EnableConfigurationProperties({CasProperties.class})
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}
