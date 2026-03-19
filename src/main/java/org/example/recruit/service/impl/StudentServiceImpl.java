package org.example.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.recruit.dto.StudentApplyDTO;
import org.example.recruit.entity.Student;
import org.example.recruit.mapper.StudentMapper;
import org.example.recruit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public boolean apply(StudentApplyDTO studentApplyDTO) {
        //检查学号是否存在
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("studenNum",studentApplyDTO.getStudentNum());
        if(studentMapper.selectOne(queryWrapper)!=null){
            throw new RuntimeException("该学号已经报名，请勿重复报名!");
        }
        studentMapper.insert((Collection<Student>) studentApplyDTO);
        return true;
    }
}