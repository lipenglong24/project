package com.lipenglong.passport.web.config;

import com.lipenglong.passport.web.util.RSAUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * passport项目AuthenticationProvider类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
public class PPDaoAuthenticationProvider extends DaoAuthenticationProvider {
    private String privateKey;

    public PPDaoAuthenticationProvider(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String presentedPassword = authentication.getCredentials().toString();
        String credentials = RSAUtil.decrypt(presentedPassword, RSAUtil.getPrivateKey(privateKey));
        UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(), credentials);
        super.additionalAuthenticationChecks(userDetails, newAuthentication);
    }

    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    @Override
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        super.setPasswordEncoder(passwordEncoder);
    }
}
