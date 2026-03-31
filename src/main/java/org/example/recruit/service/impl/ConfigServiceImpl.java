package org.example.recruit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.recruit.entity.Config;
import org.example.recruit.mapper.ConfigMapper;
import org.example.recruit.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigMapper configMapper;

    @Override
    public List<Config> getAllConfigs() {
        log.info("[ConfigServiceImpl] 获取所有配置");
        return configMapper.selectList(null);
    }

    @Override
    public Config getConfigByKey(String configKey) {
        log.info("[ConfigServiceImpl] 根据键获取配置：{}", configKey);
        return configMapper.selectOne(new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Config>()
                .eq("config_key", configKey));
    }

    @Override
    public boolean updateConfig(Config config) {
        log.info("[ConfigServiceImpl] 更新配置：{}", config.getConfigKey());
        try {
            int result = configMapper.update(config, new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Config>()
                    .eq("config_key", config.getConfigKey()));
            return result > 0;
        } catch (Exception e) {
            log.error("[ConfigServiceImpl] 更新配置失败：{}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean addConfig(Config config) {
        log.info("[ConfigServiceImpl] 新增配置：{}", config.getConfigKey());
        try {
            int result = configMapper.insert(config);
            return result > 0;
        } catch (Exception e) {
            log.error("[ConfigServiceImpl] 新增配置失败：{}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deleteConfig(Integer id) {
        log.info("[ConfigServiceImpl] 删除配置，ID：{}", id);
        try {
            int result = configMapper.deleteById(id);
            return result > 0;
        } catch (Exception e) {
            log.error("[ConfigServiceImpl] 删除配置失败：{}", e.getMessage(), e);
            return false;
        }
    }
}