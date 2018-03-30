package com.lipenglong.passport.web.config;

import com.lipenglong.passport.web.util.RSAUtil;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * passport项目自定义filter，继承UsernamePasswordAuthenticationFilter，可以增加用户名、密码额外的字段，
 * 如验证码的校验处理；也可以在在用户名密码校验前，对密码做rsa解密处理
 * <p>
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
public class PPUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private String privateKey;

    public PPUsernamePasswordAuthenticationFilter(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password = super.obtainPassword(request);
        return RSAUtil.decrypt(password, RSAUtil.getPrivateKey(privateKey));
    }
}
