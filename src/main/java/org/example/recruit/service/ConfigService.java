package org.example.recruit.service;

import org.example.recruit.entity.Config;

import java.util.List;

public interface ConfigService {
    /**
     * 获取所有配置
     */
    List<Config> getAllConfigs();
    
    /**
     * 根据键获取配置
     */
    Config getConfigByKey(String configKey);
    
    /**
     * 更新配置
     */
    boolean updateConfig(Config config);
    
    /**
     * 新增配置
     */
    boolean addConfig(Config config);
    
    /**
     * 删除配置
     */
    boolean deleteConfig(Integer id);
}