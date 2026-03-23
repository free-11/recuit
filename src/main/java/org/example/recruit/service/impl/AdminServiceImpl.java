package org.example.recruit.service.impl;

import org.example.recruit.entity.Admin;
import org.example.recruit.mapper.AdminMapper;
import org.example.recruit.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员服务实现类
 */
@Service
public class AdminServiceImpl implements AdminService {
    
    @Autowired
    private AdminMapper adminMapper;
    
    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.getAdminByUsername(username);
    }
}