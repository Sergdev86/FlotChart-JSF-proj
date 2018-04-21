package com.sample.beans.filters;

import com.sample.beans.service.LoginManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        LoginManager loginManager = (LoginManager) request.getSession().getAttribute("loginManager");
        String uri = request.getRequestURI();

        if(loginManager == null || !loginManager.isLogged()){
            response.sendRedirect(request.getSession().getServletContext().getContextPath() + "/login.xhtml");
        }else if(uri.indexOf("logout.xhtml") >= 0) {
            request.getSession().removeAttribute("loginManager");
            response.sendRedirect(request.getSession().getServletContext().getContextPath() + "/login.xhtml");
        }else
            filterChain.doFilter(request, response);
    }

    public void destroy() {

    }

}
