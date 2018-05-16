package com.lipenglong.passport.web.config;

import com.lipenglong.passport.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.*;

/**
 * spring security相关配置类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${privateKey}")
    private String privateKey;
    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/images/**", "/js/**", "/favicon.ico")
                .permitAll()
                .anyRequest().authenticated().and()
                .addFilterAt(ppUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll().logoutSuccessUrl("/login")
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        通过自定义的DaoAuthenticationProvider类，并重写additionalAuthenticationChecks方法实现校验前把rsa加密的密码解密，
//        这样的方式可以用PPUsernamePasswordAuthenticationFilter来实现
//        PPDaoAuthenticationProvider authenticationProvider = new PPDaoAuthenticationProvider(privateKey);
//        authenticationProvider.setUserDetailsService(userService);
//        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        auth.authenticationProvider(authenticationProvider);

        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PPUsernamePasswordAuthenticationFilter ppUsernamePasswordAuthenticationFilter() throws Exception {
        PPUsernamePasswordAuthenticationFilter filter = new PPUsernamePasswordAuthenticationFilter(privateKey);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return filter;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/welcome");
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login?error");
    }
}
