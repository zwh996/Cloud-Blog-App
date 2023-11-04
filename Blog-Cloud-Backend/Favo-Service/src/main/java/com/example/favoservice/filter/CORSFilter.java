package com.example.favoservice.filter;

/**
 * @ClassName Filter0CORSFilter
 * @Date 2023/10/31 13:19
 * @Author weihuazhang
 * @Version 1.0.0
 **/

import com.example.favoservice.service.CallAuthService;
import com.thetransactioncompany.cors.CORSConfiguration;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/*"}, asyncSupported = true,filterName = "Filter1",
        initParams = {
                @WebInitParam(name = "cors.allowOrigin", value = "*"),
                @WebInitParam(name = "cors.supportedMethods", value = "CONNECT, DELETE, GET, HEAD, OPTIONS, POST, PUT, TRACE"),
                @WebInitParam(name = "cors.supportedHeaders", value = "Token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified"),//注意，如果token字段放在请求头传到后端，这里需要配置
                @WebInitParam(name = "cors.exposedHeaders", value = "Set-Cookie"),
                @WebInitParam(name = "cors.supportsCredentials", value = "true")
        })
@Slf4j
public class CORSFilter extends com.thetransactioncompany.cors.CORSFilter implements javax.servlet.Filter {


    @Resource
    CallAuthService callAuthService;

    public void init(FilterConfig config) throws ServletException {
        log.info("cors filter init");
        super.init(config);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("filter wokring");
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String token = httpServletRequest.getHeader("token");
            log.info("token:" + token);
            if (Objects.isNull(callAuthService))
                throw new NullPointerException("null callAuthService");
            if (callAuthService.callAuth(token)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Unauthorized Access");
                log.error("httpResponse" + httpResponse);
            }
        } else {
            throw new ServletException("not a http request");
        }
    }


    public void setConfiguration(CORSConfiguration config) {
        super.setConfiguration(config);
    }

}

