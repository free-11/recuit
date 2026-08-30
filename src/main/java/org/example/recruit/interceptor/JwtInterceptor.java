package org.example.recruit.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.recruit.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[JwtInterceptor] 拦截到请求：{} {}", request.getMethod(), request.getRequestURI());

        // 从请求头中获取token
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            log.warn("[JwtInterceptor] 请求头中未找到token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"未登录或token已过期\",\"data\":null}");
            return false;
        }

        // 去除Bearer前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 验证token
        try {
            if (!jwtUtils.validateToken(token)) {
                log.warn("[JwtInterceptor] token验证失败");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"code\":401,\"message\":\"token无效或已过期\",\"data\":null}");
                return false;
            }

            // 获取用户名并设置到request中，方便后续使用
            String username = jwtUtils.getUsernameFromToken(token);
            request.setAttribute("username", username);
            log.info("[JwtInterceptor] token验证成功，用户名：{}", username);
            return true;

        } catch (Exception e) {
            log.error("[JwtInterceptor] token验证异常：{}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"token验证失败\",\"data\":null}");
            return false;
        }
    }
}
