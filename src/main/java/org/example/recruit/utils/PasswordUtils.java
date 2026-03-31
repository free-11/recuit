package org.example.recruit.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码工具类
 */
public class PasswordUtils {
    
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    /**
     * 加密密码（使用BCrypt）
     * @param password 原始密码
     * @return 加密后的密码
     */
    public static String encryptPassword(String password) {
        return encoder.encode(password);
    }
    
    /**
     * 验证密码（支持明文和MD5）
     * @param rawPassword 原始密码
     * @param encodedPassword 存储的密码
     * @return 是否匹配
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        // 首先尝试明文验证
        if (rawPassword.equals(encodedPassword)) {
            return true;
        }
        
        // 尝试MD5验证
        try {
            String md5Password = getMD5(rawPassword);
            return md5Password.equals(encodedPassword);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 获取MD5加密值
     */
    private static String getMD5(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}