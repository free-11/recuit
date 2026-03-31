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
                // 拦截所有请求
                .addPathPatterns("/**")
                // 排除不需要认证的路径
                .excludePathPatterns(
                        "/admin/login",              // 管理员登录
                        "/api/student/apply",        // 学生报名
                        "/api/college/list",         // 报名页：学院列表（只读）
                        "/api/specialty/list",       // 报名页：全部专业列表（只读）
                        "/api/specialty/list/*",     // 报名页：按学院查专业（只读）
                        "/api/specialty/*",          // 按ID获取专业（只读）
                        "/api/question/list",        // 问题列表（只读）
                        "/api/honor/list",           // 荣誉列表（只读）
                        "/api/honor/updateSort",     // 荣誉排序更新（不需要拦截）
                        "/api/tech-direction/list",  // 技术方向列表（只读）
                        "/api/tech_direction/list",  // 技术方向列表（只读）- 取消拦截
                        "/api/config/list",          // 配置列表（只读）
                        "/api/config/*",             // 根据键获取配置（只读）
                        "/api/qrcode/*",             // QR码上传和删除（需要权限控制）
                        "/admin/api/admin/login",     // 管理员登录接口
                        "/admin_dist/**",            // 管理员前端静态资源
                        "/h5_dist/**",               // 移动端前端静态资源
                        "/uploads/**",               // 上传的图片资源
                        "/error"                     // Spring Boot 错误页面
                );
    }
}
