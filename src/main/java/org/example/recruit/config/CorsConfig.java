package org.example.recruit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许所有接口跨域
        registry.addMapping("/**")
                // 允许前端所有域名（开发环境用*，生产环境指定具体域名）
                .allowedOriginPatterns("*")
                // 允许的请求方式（GET/POST/PUT/DELETE等）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许所有请求头
                .allowedHeaders("*")
                // 允许携带Cookie
                .allowCredentials(true)
                // 预检请求有效期（秒）
                .maxAge(3600);
    }
}
