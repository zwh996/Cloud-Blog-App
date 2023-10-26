package com.example.blogservices.filter;

import com.example.blogservices.service.CallAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class AuthFilter implements Filter {

    @Resource
    CallAuthService callAuthService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String token = httpServletRequest.getHeader("token");
            log.info("token:" + token);
            if(Objects.isNull(callAuthService))
                throw new NullPointerException("null callAuthService");
            if(callAuthService.callAuth(token)){
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            throw new ServletException("not a http request");
        }
    }

    @Override
    public void destroy() {
    }
}
