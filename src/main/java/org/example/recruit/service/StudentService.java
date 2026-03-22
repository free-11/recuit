package org.example.recruit.service;

import org.example.recruit.entity.Student;

import java.util.List;

/**
 * 学生服务接口
 */
public interface StudentService {
    /**
     * 获取所有学生
     * @return 学生列表
     */
    List<Student> getAllStudents();
    
    /**
     * 根据ID获取学生
     * @param id 学生ID
     * @return 学生信息
     */
    Student getStudentById(Long id);
    
    /**
     * 添加学生
     * @param student 学生信息
     * @return 影响行数
     */
    int addStudent(Student student);
    
    /**
     * 更新学生
     * @param student 学生信息
     * @return 影响行数
     */
    int updateStudent(Student student);
    
    /**
     * 删除学生
     * @param id 学生ID
     * @return 影响行数
     */
    int deleteStudent(Long id);
}