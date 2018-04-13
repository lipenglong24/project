package com.lipenglong.ssoserver.controller;

import com.lipenglong.ssoserver.util.TokenInfo;
import com.lipenglong.ssoserver.util.TokenUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.lipenglong.ssoserver.util.HttpUtil.executeGet;

/**
 * sso-server login控制器类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, String service, HttpSession session) throws IOException {
        if (session.getAttribute("userInfo") != null) {
            if (StringUtils.hasText(service)) {
                String token = TokenUtil.getToken(session.getId());
                TokenUtil.getTokenInfo(token).addClient(service.substring(0, service.indexOf("/", 7)));
                return "redirect:" + service + "?token=" + token;
            }
            return "redirect:/welcome";
        }
        model.addAttribute("service", service);
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, String service, HttpSession session) {
        if ("admin".equals(username) && "123456".equals(password)) {
            String token = UUID.randomUUID().toString();
            TokenInfo tokenInfo = new TokenInfo();
            tokenInfo.setUsername(username);
            tokenInfo.setGlobalId(session.getId());
            TokenUtil.setToken(token, tokenInfo);
            session.setAttribute("userInfo", tokenInfo);
            if (StringUtils.hasText(service)) {
                tokenInfo.addClient(service.substring(0, service.indexOf("/", 7)));
                return "redirect:" + service + "?token=" + token;
            }
            return "redirect:/welcome";
        }
        return "redirect:/login?error";
    }

    @GetMapping("verify")
    public @ResponseBody
    String verify(String token) {
        JSONObject result = new JSONObject();
        TokenInfo tokenInfo = TokenUtil.getTokenInfo(token);
        if (tokenInfo == null) {
            result.put("flag", 1);
            return result.toString();
        }
        result.put("flag", 0);
        result.put("username", tokenInfo.getUsername());
        result.put("globalId", tokenInfo.getGlobalId());
        return result.toString();
    }

    @GetMapping("welcome")
    public String welcome() {
        return "/welcome";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String token = TokenUtil.getToken(session.getId());
        List<String> clientList = TokenUtil.getTokenInfo(token).getSsoClientList();
        for (String client : clientList) {
            executeGet(client + "/logout?globalId=" + session.getId());
        }
        TokenUtil.delTokenInfo(token);
        session.invalidate();
        return "redirect:login";
    }
}
