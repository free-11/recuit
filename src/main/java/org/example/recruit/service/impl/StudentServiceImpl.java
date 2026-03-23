package org.example.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.recruit.dto.StudentApplyDTO;
import org.example.recruit.entity.Student;
import org.example.recruit.exception.DeleteFailedException;
import org.example.recruit.mapper.StudentMapper;
import org.example.recruit.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public void apply(StudentApplyDTO studentApplyDTO) {
            //检查学号是否存在
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_num",studentApplyDTO.getStudentNum());
            if(studentMapper.selectOne(queryWrapper)!=null){
                throw new RuntimeException("该学号已经存在，请勿重复报名！");
            }
            //创建实体对象
            Student student = new Student();
            //设置提交时间
            studentApplyDTO.setSubmissionTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));


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
    public Page<Student> getStudentPage(int pageNum, int pageSize) {
        log.info("[StudentServiceImpl] 分页查询学生信息，页码：{}，每页大小：{}", pageNum, pageSize);
        Page<Student> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        // 可以添加排序条件
        queryWrapper.orderByDesc("submission_time");
        return studentMapper.selectPage(page, queryWrapper);
    }
}