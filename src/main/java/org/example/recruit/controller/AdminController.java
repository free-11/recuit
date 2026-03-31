package org.example.recruit.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.recruit.dto.LoginDTO;
import org.example.recruit.dto.LoginResponseDTO;
import org.example.recruit.entity.Admin;
import org.example.recruit.exception.LoginFailedException;
import org.example.recruit.result.Result;
import org.example.recruit.service.AdminService;
import org.example.recruit.utils.JwtUtils;
import org.example.recruit.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员控制器
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    
    @Autowired
    private AdminService adminService;
    
    /**
     * 管理员登录
     * @param loginDTO 登录信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        log.info("[AdminController] 收到登录请求，用户名：{}", loginDTO.getUsername());
        
        // 根据用户名查询管理员
        Admin admin = adminService.getAdminByUsername(loginDTO.getUsername());
        if (admin == null) {
            throw new LoginFailedException("用户名不存在");
        }
        
        // 验证密码
        if (!PasswordUtils.matches(loginDTO.getPassword(), admin.getPassword())) {
            throw new LoginFailedException("密码错误");
        }
        
        // 生成JWT token
        String token = JwtUtils.generateToken(admin.getUsername());
        
        // 构建响应
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setUsername(admin.getUsername());
        
        log.info("[AdminController] 登录成功，用户名：{}", loginDTO.getUsername());
        return Result.success(response);
    }
    
    /**
     * 管理员退出登录
     * GET /admin/logout
     */
    @GetMapping("/logout")
    public Result<String> logout() {
        log.info("[AdminController] 收到退出登录请求");
        
        // JWT是无状态的，后端不需要特殊处理
        // 前端需要删除本地存储的token
        
        log.info("[AdminController] 退出登录成功");
        return Result.success("退出登录成功");
    }
}
