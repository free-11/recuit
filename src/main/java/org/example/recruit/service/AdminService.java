package org.example.recruit.service;

import org.example.recruit.entity.Admin;

/**
 * 管理员服务接口
 */
public interface AdminService {
    /**
     * 根据用户名查询管理员
     * @param username 用户名
     * @return 管理员信息
     */
    Admin getAdminByUsername(String username);
}