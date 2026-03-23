package org.example.recruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.example.recruit.entity.Student;

/**
 * 学生Mapper接口
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Delete("delete from student where student_num=#{id}")
    void deleteStuByNum(String id);
}

