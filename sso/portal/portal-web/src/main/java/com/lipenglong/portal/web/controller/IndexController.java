package com.lipenglong.portal.web.controller;

import com.lipenglong.portal.domain.Menu;
import com.lipenglong.portal.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * index首页控制器类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;

    @RequestMapping(value = {"/", "index"})
    public String index(Model model) {
        List<Menu> menuList = indexService.queryMenuList();
        model.addAttribute("userName", "admin");
        model.addAttribute("menuList", menuList);
        return "index";
    }

    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }
}
