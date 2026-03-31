package org.example.recruit.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.recruit.dto.StudentApplyDTO;
import org.example.recruit.dto.StudentWithDetailsDTO;
import org.example.recruit.entity.Student;
import org.example.recruit.exception.BusinessException;
import org.example.recruit.exception.DeleteFailedException;
import org.example.recruit.mapper.StudentMapper;
import org.example.recruit.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public void apply(StudentApplyDTO studentApplyDTO) {
            if (studentApplyDTO == null) {
                throw new BusinessException("报名信息不能为空");
            }
            if (studentApplyDTO.getStudentNum() == null) {
                throw new BusinessException("学号不能为空");
            }
            //检查学号是否存在
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_num",studentApplyDTO.getStudentNum());
            if(studentMapper.selectOne(queryWrapper)!=null){
                throw new BusinessException("该学号已经存在，请勿重复报名！");
            }
            //创建实体对象
            Student student = new Student();
            //设置提交时间
            studentApplyDTO.setSubmissionTime(Timestamp.valueOf(LocalDateTime.now()));


            //将DTO属性拷贝到实体
            BeanUtils.copyProperties(studentApplyDTO,student);

            //插入
            studentMapper.insert(student);


    }
    @Override
    public boolean deleteByStudentNum(Long studentNum) {
        log.info("[StudentServiceImpl] 开始删除学生，学号：{}", studentNum);
        try {
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_num", studentNum);
            int result = studentMapper.delete(queryWrapper);
            boolean success = result > 0;
            if (success) {
                log.info("[StudentServiceImpl] 删除学生成功，学号：{}", studentNum);
            } else {
                log.warn("[StudentServiceImpl] 未找到要删除的学生，学号：{}", studentNum);
                throw new DeleteFailedException("未找到该学号的学生");
            }
            return success;
        } catch (DeleteFailedException e) {
            throw e;
        } catch (Exception e) {
            log.error("[StudentServiceImpl] 删除学生失败，学号：{}，错误：{}", studentNum, e.getMessage());
            throw new DeleteFailedException("删除失败：" + e.getMessage());
        }
    }
    @Override
    public Map<String, Object> getStudentPage(int pageNum, int pageSize) {
        log.info("[StudentServiceImpl] 分页查询学生信息（包含专业名称），页码：{}，每页大小：{}", pageNum, pageSize);
        
        // 先查询总记录数
        QueryWrapper<Student> countWrapper = new QueryWrapper<>();
        Long total = studentMapper.selectCount(countWrapper);
        
        // 计算分页参数
        int offset = (pageNum - 1) * pageSize;
        long pages = (total + pageSize - 1) / pageSize;
        
        // 查询分页数据（包含专业名称）
        List<StudentWithDetailsDTO> records = studentMapper.selectStudentsWithDetailsByPage(offset, pageSize);
        
        // 创建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("records", records);
        result.put("total", total);
        result.put("size", pageSize);
        result.put("current", pageNum);
        result.put("pages", pages);
        
        return result;
    }

    @Override
    public Map<String, Object> getStudentPageWithDetails(int pageNum, int pageSize) {
        log.info("[StudentServiceImpl] 分页查询学生信息（包含专业名称），页码：{}，每页大小：{}", pageNum, pageSize);
        
        // 先查询总记录数
        QueryWrapper<Student> countWrapper = new QueryWrapper<>();
        Long total = studentMapper.selectCount(countWrapper);
        
        // 计算分页参数
        int offset = (pageNum - 1) * pageSize;
        long pages = (total + pageSize - 1) / pageSize;
        
        // 查询分页数据（包含专业名称）
        List<StudentWithDetailsDTO> records = studentMapper.selectStudentsWithDetailsByPage(offset, pageSize);
        
        // 创建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("records", records);
        result.put("total", total);
        result.put("size", pageSize);
        result.put("current", pageNum);
        result.put("pages", pages);
        
        return result;
    }

    @Override
    public void exportAllStudents(HttpServletResponse response) {
        // 查询所有学生数据（包含专业名称）
        List<StudentWithDetailsDTO> students = studentMapper.selectStudentsWithDetails();
        
        // 导出 Excel
        exportExcelWithDetails(students, "table_student", response);
    }

    @Override
    public void exportAllStudentsWithDetails(HttpServletResponse response) {
        // 查询所有学生数据（包含专业名称）
        List<StudentWithDetailsDTO> students = studentMapper.selectStudentsWithDetails();
        
        // 导出 Excel
        exportExcelWithDetails(students, "table_student", response);
    }

    @Override
    public void exportSelectedStudents(List<Long> studentNums, HttpServletResponse response) {
        if (studentNums == null || studentNums.isEmpty()) {
            throw new BusinessException("未选择要导出的学生");
        }
        
        // 根据学号列表查询学生数据（包含专业名称）
        List<StudentWithDetailsDTO> students = studentMapper.selectStudentsWithDetailsByStudentNums(studentNums);
        
        if (students.isEmpty()) {
            throw new BusinessException("未找到选中的学生信息");
        }
        
        // 导出 Excel
        exportExcelWithDetails(students, "table_student_selected", response);
    }

    @Override
    public void exportSelectedStudentsWithDetails(List<Long> studentNums, HttpServletResponse response) {
        if (studentNums == null || studentNums.isEmpty()) {
            throw new BusinessException("未选择要导出的学生");
        }
        
        // 根据学号列表查询学生数据（包含专业名称）
        List<StudentWithDetailsDTO> students = studentMapper.selectStudentsWithDetailsByStudentNums(studentNums);
        
        if (students.isEmpty()) {
            throw new BusinessException("未找到选中的学生信息");
        }
        
        // 导出 Excel
        exportExcelWithDetails(students, "table_student_selected", response);
    }

    /**
     * 通用 Excel 导出方法
     * @param students 学生列表
     * @param fileNamePrefix 文件名前缀
     * @param response HTTP响应对象
     */
    private void exportExcel(List<Student> students, String fileNamePrefix, HttpServletResponse response) {
        try {
            // 响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");

            // 文件名
            String fileName = fileNamePrefix + "_" + LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyMMddHHmmss")) + ".xlsx";
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

            // 写入 excel
            EasyExcel.write(response.getOutputStream(), Student.class)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet("data")
                    .doWrite(students);

            log.info("导出 Excel 成功，记录数：{}", students.size());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("导出 Excel 失败", e);
            throw new BusinessException(500, "导出失败：" + e.getMessage());
        }
    }

    /**
     * 通用 Excel 导出方法（包含专业名称）
     * @param students 学生列表
     * @param fileNamePrefix 文件名前缀
     * @param response HTTP响应对象
     */
    private void exportExcelWithDetails(List<StudentWithDetailsDTO> students, String fileNamePrefix, HttpServletResponse response) {
        try {
            // 响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");

            // 文件名
            String fileName = fileNamePrefix + "_" + LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyMMddHHmmss")) + ".xlsx";
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

            // 写入 excel
            EasyExcel.write(response.getOutputStream(), StudentWithDetailsDTO.class)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet("data")
                    .doWrite(students);

            log.info("导出 Excel 成功，记录数：{}", students.size());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("导出 Excel 失败", e);
            throw new BusinessException(500, "导出失败：" + e.getMessage());
        }
    }
}