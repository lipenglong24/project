package com.lipenglong.passport.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录控制类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
@Controller
public class LoginController {

    @RequestMapping(value = {"/", "login"})
    public String login() {
        return "login";
    }
}
