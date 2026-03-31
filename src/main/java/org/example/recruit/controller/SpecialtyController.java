package org.example.recruit.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.recruit.dto.SpecialtyWithCollegeDTO;
import org.example.recruit.entity.Specialty;
import org.example.recruit.result.Result;
import org.example.recruit.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialty")
@Slf4j
public class SpecialtyController {
    
    @Autowired
    private SpecialtyService specialtyService;
    
    /**
     * 根据学院ID获取专业列表
     * GET /api/specialty/list/{collegeId}
     */
    @GetMapping("/list/{collegeId}")
    public Result<List<Specialty>> getSpecialtiesByCollegeId(@PathVariable Long collegeId) {
        log.info("[SpecialtyController] 根据学院ID获取专业列表，学院ID：{}", collegeId);
        List<Specialty> specialties = specialtyService.getByCollegeId(collegeId);
        log.info("[SpecialtyController] 专业列表数量：{}", specialties.size());
        return Result.success(specialties);
    }
    
    /**
     * 获取所有专业列表（带学院名称）
     * GET /api/specialty/list
     */
    @GetMapping("/list")
    public Result<List<SpecialtyWithCollegeDTO>> getSpecialties() {
        log.info("[SpecialtyController] 获取所有专业列表（带学院名称）");
        List<SpecialtyWithCollegeDTO> specialties = specialtyService.getAllSpecialtiesWithCollege();
        log.info("[SpecialtyController] 专业列表数量：{}", specialties.size());
        return Result.success(specialties);
    }
    
    /**
     * 根据ID获取专业
     * GET /api/specialty/{id}
     */
    @GetMapping("/{id}")
    public Result<Specialty> getSpecialtyById(@PathVariable Long id) {
        log.info("[SpecialtyController] 根据ID获取专业，ID：{}", id);
        Specialty specialty = specialtyService.getById(id);
        if (specialty == null) {
            log.warn("[SpecialtyController] 专业不存在，ID：{}", id);
            return Result.error("专业不存在");
        }
        return Result.success(specialty);
    }
    
    /**
     * 新增专业
     * POST /api/specialty
     */
    @PostMapping
    public Result<String> addSpecialty(@RequestBody Specialty specialty) {
        log.info("[SpecialtyController] 新增专业：{}，学院ID：{}", specialty.getSpecialtyName(), specialty.getCollegeId());
        if (specialty.getCollegeId() == null) {
            log.warn("[SpecialtyController] 学院ID不能为空");
            return Result.error("学院ID不能为空");
        }
        try {
            boolean success = specialtyService.save(specialty);
            if (success) {
                log.info("[SpecialtyController] 新增专业成功，ID：{}", specialty.getId());
                return Result.success("新增成功");
            } else {
                log.error("[SpecialtyController] 新增专业失败，保存返回false");
                return Result.error("新增失败");
            }
        } catch (Exception e) {
            log.error("[SpecialtyController] 新增专业异常：{}", e.getMessage(), e);
            return Result.error("新增失败：" + e.getMessage());
        }
    }
    
    /**
     * 更新专业
     * PUT /api/specialty
     */
    @PutMapping
    public Result<String> updateSpecialty(@RequestBody Specialty specialty) {
        log.info("[SpecialtyController] 更新专业，ID：{}", specialty.getId());
        if (specialty.getId() == null) {
            return Result.error("专业ID不能为空");
        }
        try {
            if (specialtyService.getById(specialty.getId()) == null) {
                return Result.error("专业不存在");
            }
            boolean success = specialtyService.updateById(specialty);
            if (success) {
                log.info("[SpecialtyController] 更新专业成功");
                return Result.success("更新成功");
            } else {
                log.error("[SpecialtyController] 更新专业失败");
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            log.error("[SpecialtyController] 更新专业异常：{}", e.getMessage(), e);
            return Result.error("更新失败：" + e.getMessage());
        }
    }
    
    /**
     * 删除专业
     * DELETE /api/specialty/{id}
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteSpecialty(@PathVariable Long id) {
        log.info("[SpecialtyController] 删除专业，ID：{}", id);
        try {
            if (specialtyService.getById(id) == null) {
                return Result.error("专业不存在");
            }
            boolean success = specialtyService.removeById(id);
            if (success) {
                log.info("[SpecialtyController] 删除专业成功");
                return Result.success("删除成功");
            } else {
                log.error("[SpecialtyController] 删除专业失败");
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            log.error("[SpecialtyController] 删除专业异常：{}", e.getMessage(), e);
            return Result.error("删除失败：" + e.getMessage());
        }
    }
}
