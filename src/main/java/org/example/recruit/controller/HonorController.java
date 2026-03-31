package org.example.recruit.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.recruit.entity.Honor;
import org.example.recruit.entity.HonorSortDTO;
import org.example.recruit.result.Result;
import org.example.recruit.service.HonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/honor")
@Slf4j
public class HonorController {
    @Autowired
    private HonorService honorService;

    /**
     * 获取所有荣誉列表
     * GET /api/honor/list
     */
    @GetMapping("/list")
    public Result<List<Honor>> getAllHonors() {
        log.info("[HonorController] 获取所有荣誉列表");
        List<Honor> honors = honorService.getAllHonors();
        log.info("[HonorController] 荣誉列表数量：{}", honors.size());
        return Result.success(honors);
    }

    /**
     * 根据ID获取荣誉
     * GET /api/honor/{id}
     */
    @GetMapping("/{id}")
    public Result<Honor> getHonorById(@PathVariable Integer id) {
        log.info("[HonorController] 根据ID获取荣誉，ID：{}", id);
        Honor honor = honorService.getHonorById(id);
        if (honor == null) {
            log.warn("[HonorController] 荣誉不存在，ID：{}", id);
            return Result.error("荣誉不存在");
        }
        return Result.success(honor);
    }

    /**
     * 新增荣誉
     * POST /api/honor
     */
    @PostMapping
    public Result<String> addHonor(@RequestBody Honor honor) {
        log.info("[HonorController] 新增荣誉：{}", honor.getTile());
        if (honor.getTile() == null || honor.getTile().isEmpty()) {
            log.warn("[HonorController] 荣誉标题不能为空");
            return Result.error("荣誉标题不能为空");
        }
        if (honor.getWard() == null || honor.getWard().isEmpty()) {
            log.warn("[HonorController] 荣誉奖项不能为空");
            return Result.error("荣誉奖项不能为空");
        }
        try {
            boolean success = honorService.addHonor(honor);
            if (success) {
                log.info("[HonorController] 新增荣誉成功，ID：{}", honor.getId());
                return Result.success("新增成功");
            } else {
                log.error("[HonorController] 新增荣誉失败");
                return Result.error("新增失败");
            }
        } catch (Exception e) {
            log.error("[HonorController] 新增荣誉异常：{}", e.getMessage(), e);
            return Result.error("新增失败：" + e.getMessage());
        }
    }

    /**
     * 更新荣誉
     * PUT /api/honor
     */
    @PutMapping
    public Result<String> updateHonor(@RequestBody Honor honor) {
        log.info("[HonorController] 更新荣誉，ID：{}", honor.getId());
        if (honor.getId() == null) {
            return Result.error("荣誉ID不能为空");
        }
        try {
            boolean success = honorService.updateHonor(honor);
            if (success) {
                log.info("[HonorController] 更新荣誉成功");
                return Result.success("更新成功");
            } else {
                log.error("[HonorController] 更新荣誉失败");
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            log.error("[HonorController] 更新荣誉异常：{}", e.getMessage(), e);
            return Result.error("更新失败：" + e.getMessage());
        }
    }

    /**
     * 删除荣誉
     * DELETE /api/honor/{id}
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteHonor(@PathVariable Integer id) {
        log.info("[HonorController] 删除荣誉，ID：{}", id);
        try {
            boolean success = honorService.deleteHonor(id);
            if (success) {
                log.info("[HonorController] 删除荣誉成功");
                return Result.success("删除成功");
            } else {
                log.error("[HonorController] 删除荣誉失败");
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            log.error("[HonorController] 删除荣誉异常：{}", e.getMessage(), e);
            return Result.error("删除失败：" + e.getMessage());
        }
    }

    /**
     * 更新荣誉排序
     * POST /api/honor/updateSort
     */
    @PostMapping("/updateSort")
    public Result<Boolean> updateSort(@RequestBody Map<String, List<Integer>> request) {
        log.info("[HonorController] 更新荣誉排序");
        try {
            List<Integer> idList = request.get("idList");
            if (idList == null || idList.isEmpty()) {
                return Result.error("排序列表不能为空");
            }
            
            // 构建排序列表
            List<HonorSortDTO> sortList = new ArrayList<>();
            for (int i = 0; i < idList.size(); i++) {
                HonorSortDTO sortDTO = new HonorSortDTO();
                sortDTO.setId(idList.get(i));
                sortDTO.setSort(i + 1); // 排序值从1开始
                sortList.add(sortDTO);
            }
            
            boolean success = honorService.updateSort(sortList);
            if (success) {
                log.info("[HonorController] 更新荣誉排序成功");
                return Result.success(true);
            } else {
                log.error("[HonorController] 更新荣誉排序失败");
                return Result.error("更新排序失败");
            }
        } catch (Exception e) {
            log.error("[HonorController] 更新荣誉排序异常：{}", e.getMessage(), e);
            return Result.error("更新排序失败：" + e.getMessage());
        }
    }
}