package com.lipenglong.lspring;

import com.lipenglong.lspring.action.UserAction;
import com.lipenglong.lspring.dao.UserDaoImpl;
import com.lipenglong.lspring.service.UserService;
import org.junit.BeforeClass;
import org.junit.Test;
import com.lipenglong.lspring.context.Context;

import static org.junit.Assert.assertEquals;

public class LSpringTest {
    public static Context context;

    @BeforeClass
    public static void beforeTest() {
//        String contextXml = "bean-context-annotation.xml";
        String contextXml = "bean-context-xml.xml";
        context = new Context(contextXml);
    }

    @Test
    public void initTableAndData() {
        UserDaoImpl userDao = (UserDaoImpl) context.getBean("userDao");
        assertEquals(userDao.initTable(), true);
        userDao.initData();
    }

    @Test
    public void testUserService() {
        UserService service = (UserService) context.getBean("userService");
        assertEquals(service.getUserList().size(), 5);
        assertEquals(1, 1);
        service.print();
    }

    @Test
    public void testUserAction() {
        UserAction userAction = (UserAction) context.getBean("userAction");
        assertEquals(userAction.getUserList().size(), 5);
    }
}
