package org.example.recruit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.recruit.entity.Honor;
import org.example.recruit.entity.HonorSortDTO;
import org.example.recruit.mapper.HonorMapper;
import org.example.recruit.service.HonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HonorServiceImpl implements HonorService {
    @Autowired
    private HonorMapper honorMapper;

    @Override
    public List<Honor> getAllHonors() {
        log.info("[HonorServiceImpl] 获取所有荣誉列表");
        // 使用QueryWrapper按sort字段升序排序
        com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Honor> queryWrapper = new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        List<Honor> honors = honorMapper.selectList(queryWrapper);
        log.info("[HonorServiceImpl] 荣誉列表数量：{}", honors.size());
        return honors;
    }

    @Override
    public Honor getHonorById(Integer id) {
        log.info("[HonorServiceImpl] 根据ID获取荣誉，ID：{}", id);
        Honor honor = honorMapper.selectById(id);
        if (honor == null) {
            log.warn("[HonorServiceImpl] 荣誉不存在，ID：{}", id);
        }
        return honor;
    }

    @Override
    public boolean addHonor(Honor honor) {
        log.info("[HonorServiceImpl] 新增荣誉：{}", honor.getTile());
        try {
            // 为新荣誉设置默认排序值（当前最大排序值 + 1）
            com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Honor> queryWrapper = new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<>();
            queryWrapper.orderByDesc("sort");
            List<Honor> honors = honorMapper.selectList(queryWrapper);
            int maxSort = 0;
            if (!honors.isEmpty() && honors.get(0).getSort() != null) {
                maxSort = honors.get(0).getSort();
            }
            honor.setSort(maxSort + 1);
            
            int result = honorMapper.insert(honor);
            boolean success = result > 0;
            if (success) {
                log.info("[HonorServiceImpl] 新增荣誉成功，ID：{}", honor.getId());
            } else {
                log.error("[HonorServiceImpl] 新增荣誉失败");
            }
            return success;
        } catch (Exception e) {
            log.error("[HonorServiceImpl] 新增荣誉异常：{}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean updateSort(List<HonorSortDTO> sortList) {
        log.info("[HonorServiceImpl] 更新荣誉排序，数量：{}", sortList.size());
        try {
            // 批量更新排序
            for (HonorSortDTO sortDTO : sortList) {
                Honor honor = new Honor();
                honor.setId(sortDTO.getId());
                honor.setSort(sortDTO.getSort());
                honorMapper.updateById(honor);
            }
            log.info("[HonorServiceImpl] 更新荣誉排序成功");
            return true;
        } catch (Exception e) {
            log.error("[HonorServiceImpl] 更新荣誉排序异常：{}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean updateHonor(Honor honor) {
        log.info("[HonorServiceImpl] 更新荣誉，ID：{}", honor.getId());
        try {
            if (honorMapper.selectById(honor.getId()) == null) {
                log.warn("[HonorServiceImpl] 荣誉不存在，ID：{}", honor.getId());
                return false;
            }
            int result = honorMapper.updateById(honor);
            boolean success = result > 0;
            if (success) {
                log.info("[HonorServiceImpl] 更新荣誉成功");
            } else {
                log.error("[HonorServiceImpl] 更新荣誉失败");
            }
            return success;
        } catch (Exception e) {
            log.error("[HonorServiceImpl] 更新荣誉异常：{}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deleteHonor(Integer id) {
        log.info("[HonorServiceImpl] 删除荣誉，ID：{}", id);
        try {
            if (honorMapper.selectById(id) == null) {
                log.warn("[HonorServiceImpl] 荣誉不存在，ID：{}", id);
                return false;
            }
            int result = honorMapper.deleteById(id);
            boolean success = result > 0;
            if (success) {
                log.info("[HonorServiceImpl] 删除荣誉成功");
            } else {
                log.error("[HonorServiceImpl] 删除荣誉失败");
            }
            return success;
        } catch (Exception e) {
            log.error("[HonorServiceImpl] 删除荣誉异常：{}", e.getMessage(), e);
            return false;
        }
    }
}