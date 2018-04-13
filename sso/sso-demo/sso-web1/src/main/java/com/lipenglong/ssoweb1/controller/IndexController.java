package com.lipenglong.ssoweb1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * sso-web1
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
@Controller
public class IndexController {
    @GetMapping("page")
    public String page() {
        return "page";
    }
}
