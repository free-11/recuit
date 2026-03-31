package org.example.recruit.controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.recruit.dto.StudentApplyDTO;
import org.example.recruit.result.Result;
import org.example.recruit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    /*学生提交报名信息
    * post /api/student/apply
    * */
    @PostMapping("/apply")
    public Result<String> apply(@RequestBody StudentApplyDTO studentApplyDTO){
        log.info("收到报名请求：学号={}, 姓名={}", studentApplyDTO.getStudentNum(), studentApplyDTO.getName());
        
        studentService.apply(studentApplyDTO);
        
        log.info("报名成功：学号={}, 姓名={}", studentApplyDTO.getStudentNum(), studentApplyDTO.getName());
        return Result.success("报名成功");

    }
    @DeleteMapping("/{studentNum}")
    public Result<String> deleteStu(@PathVariable String studentNum){
        log.info("[StudentController] 开始删除学生，学号：{}", studentNum);
        if (!studentNum.matches("\\d+")) {
            log.error("[StudentController] 学号格式错误，学号：{}", studentNum);
            return Result.error("学号格式错误");
        }
        studentService.deleteByStudentNum(Long.parseLong(studentNum));
        log.info("[StudentController] 删除学生成功，学号：{}", studentNum);
        return Result.success("删除成功");
    }
    /**
     * 分页查询学生信息
     * GET /api/student/page
     */
    @GetMapping("/page")
    public Result<?> getStudentPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        log.info("[StudentController] 接收分页查询请求，页码：{}，每页大小：{}", pageNum, pageSize);
        Map<String, Object> result = studentService.getStudentPage(pageNum, pageSize);
        log.info("[StudentController] 分页查询成功，总记录数：{}", result.get("total"));
        return Result.success(result);
    }

    /**
     * 分页查询学生信息（包含专业名称）
     * GET /api/student/page-with-details
     */
    @GetMapping("/page-with-details")
    public Result<?> getStudentPageWithDetails(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        log.info("[StudentController] 接收分页查询请求（包含专业名称），页码：{}，每页大小：{}", pageNum, pageSize);
        Map<String, Object> result = studentService.getStudentPageWithDetails(pageNum, pageSize);
        log.info("[StudentController] 分页查询成功，总记录数：{}", result.get("total"));
        return Result.success(result);
    }

    /**
     * 导出所有学生信息为 Excel
     * Get /api/student/exportall
     */
    @GetMapping("/exportall")
    public void exportAllStudents(HttpServletResponse response) {
        studentService.exportAllStudents(response);
    }

    /**
     * 导出所有学生信息为 Excel（包含专业名称）
     * Get /api/student/exportall-with-details
     */
    @GetMapping("/exportall-with-details")
    public void exportAllStudentsWithDetails(HttpServletResponse response) {
        studentService.exportAllStudentsWithDetails(response);
    }

    /**
     * 导出选中的学生信息为 Excel
     * POST /api/student/export
     * 请求体 JSON：[2021001, 2021002]
     */
    @PostMapping("/export")
    public void exportSelectedStudents(@RequestBody List<Long> studentNums, HttpServletResponse response) {
        studentService.exportSelectedStudents(studentNums, response);
    }

    /**
     * 导出选中的学生信息为 Excel（包含专业名称）
     * POST /api/student/export-with-details
     * 请求体 JSON：[2021001, 2021002]
     */
    @PostMapping("/export-with-details")
    public void exportSelectedStudentsWithDetails(@RequestBody List<Long> studentNums, HttpServletResponse response) {
        studentService.exportSelectedStudentsWithDetails(studentNums, response);
    }
}