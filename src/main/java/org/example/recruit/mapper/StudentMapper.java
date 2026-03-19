package org.example.recruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.recruit.entity.Student;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
