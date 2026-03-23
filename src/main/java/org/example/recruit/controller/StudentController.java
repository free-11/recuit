package org.example.recruit.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.recruit.dto.StudentApplyDTO;
import org.example.recruit.entity.Student;
import org.example.recruit.result.Result;
import org.example.recruit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result deleteStu(@PathVariable String studentNum){
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
    public Result<Page<Student>> getStudentPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        log.info("[StudentController] 接收分页查询请求，页码：{}，每页大小：{}", pageNum, pageSize);
        Page<Student> page = studentService.getStudentPage(pageNum, pageSize);
        log.info("[StudentController] 分页查询成功，总记录数：{}，总页数：{}", page.getTotal(), page.getPages());
        return Result.success(page);
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
     * 导出选中的学生信息为 Excel
     * Post /api/student/export
     */
    @PostMapping("/export")
    public void exportSelectedStudents(@RequestBody List<Long> ids, HttpServletResponse response) {
        studentService.exportSelectedStudents(ids, response);
    }
}