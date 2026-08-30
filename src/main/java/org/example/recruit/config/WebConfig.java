package org.example.recruit.config;

import org.example.recruit.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/api/admin/login",
                        "/api/student/apply",
                        "/api/college/list",
                        "/api/specialty/list",
                        "/api/specialty/list/*",
                        "/api/question/list",
                        "/api/honor/list",
                        "/api/tech-direction/list",
                        "/api/config/list",
                        "/admin_dist/**",
                        "/h5_dist/**",
                        "/uploads/**",
                        "/error"
                );
    }
}
