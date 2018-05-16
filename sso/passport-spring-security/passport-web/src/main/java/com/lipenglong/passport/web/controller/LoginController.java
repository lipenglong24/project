package com.lipenglong.passport.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录控制类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
@Controller
public class LoginController {
    @Value("${publicKey}")
    private String publicKey;

    @RequestMapping(value = {"/", "login"})
    public String login(Model model) {
        model.addAttribute("publicKey", publicKey);
        return "login";
    }

    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }
}
