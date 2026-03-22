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
    
    /**
     * 添加管理员
     * @param admin 管理员信息
     * @return 影响行数
     */
    int addAdmin(Admin admin);
    
    /**
     * 更新管理员
     * @param admin 管理员信息
     * @return 影响行数
     */
    int updateAdmin(Admin admin);
    
    /**
     * 删除管理员
     * @param id 管理员ID
     * @return 影响行数
     */
    int deleteAdmin(Long id);
}