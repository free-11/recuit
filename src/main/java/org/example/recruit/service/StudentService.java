package org.example.recruit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.recruit.dto.StudentApplyDTO;
import org.example.recruit.entity.Student;

import java.util.List;

public interface StudentService  {
    void apply(StudentApplyDTO studentApplyDTO);

}