package com.example.basic.domain.auth.filter;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.global.reqres.ReqResHandler;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginFilter implements Filter {

    private ReqResHandler reqResHandler;

    public LoginFilter(ReqResHandler reqResHandler) {
        this.reqResHandler = reqResHandler;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Member loginMember = reqResHandler.getLoginMember();

        if (loginMember == null) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("/login");
            return;
        }

        chain.doFilter(request, response);
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