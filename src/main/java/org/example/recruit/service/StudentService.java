package org.example.recruit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.recruit.dto.StudentApplyDTO;
import org.example.recruit.entity.Student;

public interface StudentService  {
    /**
     * 根据学号删除学生
     * @param studentNum 学号
     * @return 是否删除成功
     */
    boolean deleteByStudentNum(Long studentNum);
    /**
     *学生提交报名信息
     **/
    void apply(StudentApplyDTO studentApplyDTO);
    /**
     * 分页查询学生信息
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    Page<Student> getStudentPage(int pageNum, int pageSize);


}