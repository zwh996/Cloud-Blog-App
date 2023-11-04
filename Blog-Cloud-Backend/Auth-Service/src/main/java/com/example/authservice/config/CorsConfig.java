package com.example.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName CorsConfig
 * @Date 2023/10/26 23:45
 * @Author weihuazhang
 * @Version 1.0.0
 **/
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置允许跨域访问的路径、允许的域名、HTTP方法等
        registry.addMapping("/api/**")
                .allowedOrigins("*") // 允许的前端域名
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的HTTP方法
                .allowCredentials(false); // 允许携带认证信息（如cookies）
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(false);
            }
        };
    }
}
