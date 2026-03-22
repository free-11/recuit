package org.example.recruit.dto;

import lombok.Data;

/**
 * 登录响应DTO
 */
@Data
public class LoginResponseDTO {
    /**
     * JWT token
     */
    private String token;
    
    /**
     * 用户名
     */
    private String username;
}