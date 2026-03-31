package org.example.recruit.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.recruit.entity.College;
import org.example.recruit.result.Result;
import org.example.recruit.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/college")
@Slf4j
public class CollegeController {
    
    @Autowired
    private CollegeService collegeService;
    
    /**
     * 获取所有学院列表
     * GET /api/college/list
     */
    @GetMapping("/list")
    public Result<List<College>> getColleges() {
        log.info("[CollegeController] 获取学院列表");
        List<College> colleges = collegeService.list();
        log.info("[CollegeController] 学院列表数量：{}", colleges.size());
        return Result.success(colleges);
    }
    
    /**
     * 根据ID获取学院
     * GET /api/college/{id}
     */
    @GetMapping("/{id}")
    public Result<College> getCollegeById(@PathVariable Long id) {
        log.info("[CollegeController] 根据ID获取学院，ID：{}", id);
        College college = collegeService.getById(id);
        if (college == null) {
            log.warn("[CollegeController] 学院不存在，ID：{}", id);
            return Result.error("学院不存在");
        }
        return Result.success(college);
    }
    
    /**
     * 新增学院
     * POST /api/college
     */
    @PostMapping
    public Result<String> addCollege(@RequestBody College college) {
        log.info("[CollegeController] 新增学院：{}", college.getCollegeName());
        try {
            boolean success = collegeService.save(college);
            if (success) {
                log.info("[CollegeController] 新增学院成功，ID：{}", college.getId());
                return Result.success("新增成功");
            } else {
                log.error("[CollegeController] 新增学院失败，保存返回false");
                return Result.error("新增失败");
            }
        } catch (Exception e) {
            log.error("[CollegeController] 新增学院异常：{}", e.getMessage(), e);
            return Result.error("新增失败：" + e.getMessage());
        }
    }
    
    /**
     * 更新学院
     * PUT /api/college
     */
    @PutMapping
    public Result<String> updateCollege(@RequestBody College college) {
        log.info("[CollegeController] 更新学院，ID：{}", college.getId());
        if (college.getId() == null) {
            return Result.error("学院ID不能为空");
        }
        try {
            if (collegeService.getById(college.getId()) == null) {
                return Result.error("学院不存在");
            }
            boolean success = collegeService.updateById(college);
            if (success) {
                log.info("[CollegeController] 更新学院成功");
                return Result.success("更新成功");
            } else {
                log.error("[CollegeController] 更新学院失败");
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            log.error("[CollegeController] 更新学院异常：{}", e.getMessage(), e);
            return Result.error("更新失败：" + e.getMessage());
        }
    }
    
    /**
     * 删除学院
     * DELETE /api/college/{id}
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteCollege(@PathVariable Long id) {
        log.info("[CollegeController] 删除学院，ID：{}", id);
        try {
            if (collegeService.getById(id) == null) {
                return Result.error("学院不存在");
            }
            boolean success = collegeService.removeById(id);
            if (success) {
                log.info("[CollegeController] 删除学院成功");
                return Result.success("删除成功");
            } else {
                log.error("[CollegeController] 删除学院失败");
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            log.error("[CollegeController] 删除学院异常：{}", e.getMessage(), e);
            return Result.error("删除失败：" + e.getMessage());
        }
    }
}
