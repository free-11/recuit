package org.example.recruit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.recruit.entity.TechDirection;
import org.example.recruit.mapper.TechDirectionMapper;
import org.example.recruit.service.TechDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TechDirectionServiceImpl implements TechDirectionService {
    @Autowired
    private TechDirectionMapper techDirectionMapper;

    @Override
    public List<TechDirection> getAllTechDirections() {
        log.info("[TechDirectionServiceImpl] 获取所有技术方向列表");
        List<TechDirection> techDirections = techDirectionMapper.selectList(null);
        log.info("[TechDirectionServiceImpl] 技术方向列表数量：{}", techDirections.size());
        return techDirections;
    }

    @Override
    public TechDirection getTechDirectionById(Integer id) {
        log.info("[TechDirectionServiceImpl] 根据ID获取技术方向，ID：{}", id);
        TechDirection techDirection = techDirectionMapper.selectById(id);
        if (techDirection == null) {
            log.warn("[TechDirectionServiceImpl] 技术方向不存在，ID：{}", id);
        }
        return techDirection;
    }

    @Override
    public boolean addTechDirection(TechDirection techDirection) {
        log.info("[TechDirectionServiceImpl] 新增技术方向：{}", techDirection.getTile());
        try {
            int result = techDirectionMapper.insert(techDirection);
            boolean success = result > 0;
            if (success) {
                log.info("[TechDirectionServiceImpl] 新增技术方向成功，ID：{}", techDirection.getId());
            } else {
                log.error("[TechDirectionServiceImpl] 新增技术方向失败");
            }
            return success;
        } catch (Exception e) {
            log.error("[TechDirectionServiceImpl] 新增技术方向异常：{}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean updateTechDirection(TechDirection techDirection) {
        log.info("[TechDirectionServiceImpl] 更新技术方向，ID：{}", techDirection.getId());
        try {
            if (techDirectionMapper.selectById(techDirection.getId()) == null) {
                log.warn("[TechDirectionServiceImpl] 技术方向不存在，ID：{}", techDirection.getId());
                return false;
            }
            int result = techDirectionMapper.updateById(techDirection);
            boolean success = result > 0;
            if (success) {
                log.info("[TechDirectionServiceImpl] 更新技术方向成功");
            } else {
                log.error("[TechDirectionServiceImpl] 更新技术方向失败");
            }
            return success;
        } catch (Exception e) {
            log.error("[TechDirectionServiceImpl] 更新技术方向异常：{}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deleteTechDirection(Integer id) {
        log.info("[TechDirectionServiceImpl] 删除技术方向，ID：{}", id);
        try {
            if (techDirectionMapper.selectById(id) == null) {
                log.warn("[TechDirectionServiceImpl] 技术方向不存在，ID：{}", id);
                return false;
            }
            int result = techDirectionMapper.deleteById(id);
            boolean success = result > 0;
            if (success) {
                log.info("[TechDirectionServiceImpl] 删除技术方向成功");
            } else {
                log.error("[TechDirectionServiceImpl] 删除技术方向失败");
            }
            return success;
        } catch (Exception e) {
            log.error("[TechDirectionServiceImpl] 删除技术方向异常：{}", e.getMessage(), e);
            return false;
        }
    }
}