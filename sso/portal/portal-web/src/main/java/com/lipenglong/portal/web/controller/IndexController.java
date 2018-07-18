package com.lipenglong.portal.web.controller;

import com.lipenglong.portal.domain.Menu;
import com.lipenglong.portal.service.IndexService;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    public String index(Model model, HttpServletRequest request) {
        List<Menu> menuList = indexService.queryMenuList();
        Principal principal = request.getUserPrincipal();
        AttributePrincipal attributePrincipal = (AttributePrincipal) principal;
        Map<String, Object> attributes = attributePrincipal.getAttributes();
        Iterator<String> keys = attributes.keySet().iterator();
        while (keys.hasNext()) {
            String name = keys.next();
            Object value = attributes.get(name);

            System.out.println("name=" + name + "; value=" + value);
        }
        System.out.println("------------------------");
        System.out.println(principal.getName());

        model.addAttribute("userName", request.getUserPrincipal().getName());
        model.addAttribute("menuList", menuList);
        return "index";
    }

    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }
}
