package com.example.basic.domain.auth.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterConfig {

    @Bean
    public FilterRegistrationBean<TestFilter> testFilterRegistrationBean() {
        FilterRegistrationBean<TestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TestFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> AdminFilterRegistrationBean() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminFilter());
        registrationBean.addUrlPatterns("/admin/*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> LoginFilterRegistrationBean() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter());
        registrationBean.addUrlPatterns("/article/write", "/article/delete/*", "/article/modify/*");

        return registrationBean;
    }
}
