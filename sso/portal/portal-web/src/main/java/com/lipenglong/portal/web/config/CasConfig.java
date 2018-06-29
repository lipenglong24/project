package com.lipenglong.portal.web.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * portal
 * <p/>
 *
 * @author lipenglong
 * @since 1.0-SNAPSHOT
 */
@Configuration
public class CasConfig {
    @Autowired
    CasProperties casProperties;

    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listener = new ServletListenerRegistrationBean<>();
        listener.setListener(new SingleSignOutHttpSessionListener());
        listener.setOrder(1);
        return listener;
    }

    @Bean
    public FilterRegistrationBean<SingleSignOutFilter> singleSignOutFilter() {
        FilterRegistrationBean<SingleSignOutFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new SingleSignOutFilter());
        if (!casProperties.getSignOutFilters().isEmpty()) {
            filterRegistration.setUrlPatterns(casProperties.getSignOutFilters());
        } else {
            filterRegistration.addUrlPatterns("/*");
        }
        filterRegistration.addInitParameter("casServerUrlPrefix", casProperties.getCasServerUrlPrefix());
        filterRegistration.addInitParameter("serverName", casProperties.getServerName());
        filterRegistration.setOrder(2);
        return filterRegistration;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new AuthenticationFilter());
        if (!casProperties.getAuthFilters().isEmpty()) {
            filterRegistration.setUrlPatterns(casProperties.getAuthFilters());
        } else {
            filterRegistration.addUrlPatterns("/*");
        }
        filterRegistration.addInitParameter("ignorePattern", casProperties.getIgnorePattern());
        filterRegistration.addInitParameter("casServerLoginUrl", casProperties.getCasServerLoginUrl());
        filterRegistration.addInitParameter("serverName", casProperties.getServerName());
        filterRegistration.addInitParameter("useSession", casProperties.isUseSession() ? "true" : "flase");
        filterRegistration.addInitParameter("redirectAfterValidation", casProperties.isRedirectAfterValidation() ? "true" : "false");
        filterRegistration.setOrder(3);
        return filterRegistration;
    }

    @Bean
    public FilterRegistrationBean<Cas20ProxyReceivingTicketValidationFilter> cas20ProxyReceivingTicketValidationFilter() {
        FilterRegistrationBean<Cas20ProxyReceivingTicketValidationFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        if (!casProperties.getValidateFilters().isEmpty()) {
            filterRegistration.setUrlPatterns(casProperties.getValidateFilters());
        } else {
            filterRegistration.addUrlPatterns("/*");
        }
        filterRegistration.addInitParameter("casServerUrlPrefix", casProperties.getCasServerUrlPrefix());
        filterRegistration.addInitParameter("serverName", casProperties.getServerName());
        filterRegistration.setOrder(4);
        return filterRegistration;
    }

    @Bean
    public FilterRegistrationBean<HttpServletRequestWrapperFilter> httpServletRequestWrapperFilter() {
        FilterRegistrationBean<HttpServletRequestWrapperFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new HttpServletRequestWrapperFilter());
        if (!casProperties.getRequestWrapperFilters().isEmpty()) {
            filterRegistration.setUrlPatterns(casProperties.getRequestWrapperFilters());
        } else {
            filterRegistration.addUrlPatterns("/*");
        }
        filterRegistration.setOrder(5);
        return filterRegistration;
    }

    @Bean
    public FilterRegistrationBean<AssertionThreadLocalFilter> assertionThreadLocalFilter() {
        FilterRegistrationBean<AssertionThreadLocalFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new AssertionThreadLocalFilter());
        if (!casProperties.getAssertionFilters().isEmpty()) {
            filterRegistration.setUrlPatterns(casProperties.getAssertionFilters());
        } else {
            filterRegistration.addUrlPatterns("/*");
        }
        filterRegistration.setOrder(6);
        return filterRegistration;
    }
}
