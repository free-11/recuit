package org.example.recruit.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.recruit.entity.Student;
import java.util.List;

/**
 * 学生Mapper接口
 */
@Mapper
public interface StudentMapper {
    /**
     * 查询所有学生
     * @return 学生列表
     */
    List<Student> selectAll();
    
    /**
     * 根据ID查询学生
     * @param id 学生ID
     * @return 学生信息
     */
    Student selectById(Long id);
    
    /**
     * 插入学生
     * @param student 学生信息
     * @return 影响行数
     */
    int insert(Student student);
    
    /**
     * 更新学生
     * @param student 学生信息
     * @return 影响行数
     */
    int update(Student student);
    
    /**
     * 删除学生
     * @param id 学生ID
     * @return 影响行数
     */
    int delete(Long id);
}
