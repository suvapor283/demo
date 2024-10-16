package com.example.basic.domain.auth.filter;

import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class TestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("저는 필터입니다.");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
