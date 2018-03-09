package com.lipenglong.ldubbo.consumer;

import com.lipenglong.ldubbo.api.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * com.lipenglong.ldubbo.consumer
 * </p>
 * Created by lipenglong on 2017/8/30.
 */
public class Consumer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-ldubbo.xml");
        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService);
        System.out.println(userService.queryUserList());
        System.out.println(userService.getUserById(23L));

    }
}
