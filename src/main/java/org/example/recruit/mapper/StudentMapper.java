package org.example.recruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.recruit.dto.StudentWithDetailsDTO;
import org.example.recruit.entity.Student;

import java.util.List;

/**
 * 学生Mapper接口
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    /**
     * 查询所有学生信息（包含专业名称）
     */
    @Select("SELECT s.*, sp.specialty_name FROM student s " +
            "LEFT JOIN specialty sp ON s.specialty_id = sp.id " +
            "ORDER BY s.submission_time DESC")
    List<StudentWithDetailsDTO> selectStudentsWithDetails();

    /**
     * 分页查询学生信息（包含专业名称）
     */
    @Select("SELECT s.*, sp.specialty_name FROM student s " +
            "LEFT JOIN specialty sp ON s.specialty_id = sp.id " +
            "ORDER BY s.submission_time DESC " +
            "LIMIT #{offset}, #{pageSize}")
    List<StudentWithDetailsDTO> selectStudentsWithDetailsByPage(int offset, int pageSize);

    /**
     * 根据学号列表查询学生信息（包含专业名称）
     */
    @Select("<script>" +
            "SELECT s.*, sp.specialty_name FROM student s " +
            "LEFT JOIN specialty sp ON s.specialty_id = sp.id " +
            "WHERE s.student_num IN " +
            "<foreach collection='studentNums' item='studentNum' open='(' separator=',' close=')'>" +
            "#{studentNum}" +
            "</foreach>" +
            "ORDER BY s.submission_time DESC" +
            "</script>")
    List<StudentWithDetailsDTO> selectStudentsWithDetailsByStudentNums(List<Long> studentNums);
}

