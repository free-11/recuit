package org.example.recruit.controller;


import org.example.recruit.dto.StudentApplyDTO;
import org.example.recruit.entity.Student;
import org.example.recruit.mapper.StudentMapper;
import org.example.recruit.result.Result;
import org.example.recruit.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /*学生提交报名信息
    * post /api/student/apply
    * */
    @PostMapping("/apply")
    public Result<String> apply(@RequestBody StudentApplyDTO studentApplyDTO){
        studentService.apply(studentApplyDTO);
        return Result.success("报名成功");

    }



}
