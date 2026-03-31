package org.example.recruit.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.recruit.entity.TechDirection;
import org.example.recruit.result.Result;
import org.example.recruit.service.TechDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tech-direction")
@Slf4j
public class TechDirectionController {
    @Autowired
    private TechDirectionService techDirectionService;

    /**
     * 获取所有技术方向列表
     * GET /api/tech-direction/list
     */
    @GetMapping("/list")
    public Result<List<TechDirection>> getAllTechDirections() {
        log.info("[TechDirectionController] 获取所有技术方向列表");
        List<TechDirection> techDirections = techDirectionService.getAllTechDirections();
        log.info("[TechDirectionController] 技术方向列表数量：{}", techDirections.size());
        return Result.success(techDirections);
    }

    /**
     * 根据ID获取技术方向
     * GET /api/tech-direction/{id}
     */
    @GetMapping("/{id}")
    public Result<TechDirection> getTechDirectionById(@PathVariable Integer id) {
        log.info("[TechDirectionController] 根据ID获取技术方向，ID：{}", id);
        TechDirection techDirection = techDirectionService.getTechDirectionById(id);
        if (techDirection == null) {
            log.warn("[TechDirectionController] 技术方向不存在，ID：{}", id);
            return Result.error("技术方向不存在");
        }
        return Result.success(techDirection);
    }

    /**
     * 新增技术方向
     * POST /api/tech-direction
     */
    @PostMapping
    public Result<String> addTechDirection(@RequestBody TechDirection techDirection) {
        log.info("[TechDirectionController] 新增技术方向：{}", techDirection.getTile());
        if (techDirection.getTile() == null || techDirection.getTile().isEmpty()) {
            log.warn("[TechDirectionController] 技术方向标题不能为空");
            return Result.error("技术方向标题不能为空");
        }
        try {
            boolean success = techDirectionService.addTechDirection(techDirection);
            if (success) {
                log.info("[TechDirectionController] 新增技术方向成功，ID：{}", techDirection.getId());
                return Result.success("新增成功");
            } else {
                log.error("[TechDirectionController] 新增技术方向失败");
                return Result.error("新增失败");
            }
        } catch (Exception e) {
            log.error("[TechDirectionController] 新增技术方向异常：{}", e.getMessage(), e);
            return Result.error("新增失败：" + e.getMessage());
        }
    }

    /**
     * 更新技术方向
     * PUT /api/tech-direction
     */
    @PutMapping
    public Result<String> updateTechDirection(@RequestBody TechDirection techDirection) {
        log.info("[TechDirectionController] 更新技术方向，ID：{}", techDirection.getId());
        if (techDirection.getId() == null) {
            return Result.error("技术方向ID不能为空");
        }
        try {
            boolean success = techDirectionService.updateTechDirection(techDirection);
            if (success) {
                log.info("[TechDirectionController] 更新技术方向成功");
                return Result.success("更新成功");
            } else {
                log.error("[TechDirectionController] 更新技术方向失败");
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            log.error("[TechDirectionController] 更新技术方向异常：{}", e.getMessage(), e);
            return Result.error("更新失败：" + e.getMessage());
        }
    }

    /**
     * 删除技术方向
     * DELETE /api/tech-direction/{id}
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteTechDirection(@PathVariable Integer id) {
        log.info("[TechDirectionController] 删除技术方向，ID：{}", id);
        try {
            boolean success = techDirectionService.deleteTechDirection(id);
            if (success) {
                log.info("[TechDirectionController] 删除技术方向成功");
                return Result.success("删除成功");
            } else {
                log.error("[TechDirectionController] 删除技术方向失败");
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            log.error("[TechDirectionController] 删除技术方向异常：{}", e.getMessage(), e);
            return Result.error("删除失败：" + e.getMessage());
        }
    }
}