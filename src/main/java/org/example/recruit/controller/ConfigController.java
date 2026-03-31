package org.example.recruit.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.recruit.entity.Config;
import org.example.recruit.result.Result;
import org.example.recruit.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/config")
@Slf4j
public class ConfigController {
    @Autowired
    private ConfigService configService;

    /**
     * 获取所有配置
     * GET /api/config/list
     */
    @GetMapping("/list")
    public Result<List<Config>> getAllConfigs() {
        log.info("[ConfigController] 获取所有配置");
        List<Config> configs = configService.getAllConfigs();
        return Result.success(configs);
    }

    /**
     * 根据键获取配置
     * GET /api/config/{key}
     */
    @GetMapping("/{key}")
    public Result<Config> getConfigByKey(@PathVariable String key) {
        log.info("[ConfigController] 根据键获取配置：{}", key);
        Config config = configService.getConfigByKey(key);
        if (config == null) {
            return Result.error("配置不存在");
        }
        return Result.success(config);
    }

    /**
     * 更新配置
     * PUT /api/config
     */
    @PutMapping
    public Result<String> updateConfig(@RequestBody Config config) {
        log.info("[ConfigController] 更新配置：{}", config.getConfigKey());
        boolean success = configService.updateConfig(config);
        if (success) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    /**
     * 新增配置
     * POST /api/config
     */
    @PostMapping
    public Result<String> addConfig(@RequestBody Config config) {
        log.info("[ConfigController] 新增配置：{}", config.getConfigKey());
        boolean success = configService.addConfig(config);
        if (success) {
            return Result.success("新增成功");
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 删除配置
     * DELETE /api/config/{id}
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteConfig(@PathVariable Integer id) {
        log.info("[ConfigController] 删除配置，ID：{}", id);
        boolean success = configService.deleteConfig(id);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}