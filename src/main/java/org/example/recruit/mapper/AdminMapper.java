package org.example.recruit.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.recruit.entity.Admin;

/**
 * 管理员Mapper接口
 */
@Mapper
public interface AdminMapper {
    /**
     * 根据用户名查询管理员
     * @param username 用户名
     * @return 管理员信息
     */
    Admin getAdminByUsername(String username);
}
