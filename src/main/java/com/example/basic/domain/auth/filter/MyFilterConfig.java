package com.example.basic.domain.auth.filter;

import com.example.basic.global.reqres.ReqResHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyFilterConfig {

    private final ReqResHandler reqResHandler;

    @Bean
    public FilterRegistrationBean<AdminFilter> AdminFilterRegistrationBean() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminFilter(reqResHandler));
        registrationBean.addUrlPatterns("/admin/*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> LoginFilterRegistrationBean() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter(reqResHandler));
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}