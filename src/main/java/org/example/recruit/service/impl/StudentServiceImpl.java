package org.example.recruit.service.impl;

import org.example.recruit.entity.Student;
import org.example.recruit.mapper.StudentMapper;
import org.example.recruit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生服务实现类
 */
@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentMapper studentMapper;
    
    @Override
    public List<Student> getAllStudents() {
        return studentMapper.selectAll();
    }
    
    @Override
    public Student getStudentById(Long id) {
        return studentMapper.selectById(id);
    }
    
    @Override
    public int addStudent(Student student) {
        return studentMapper.insert(student);
    }
    
    @Override
    public int updateStudent(Student student) {
        return studentMapper.update(student);
    }
    
    @Override
    public int deleteStudent(Long id) {
        return studentMapper.delete(id);
    }
}