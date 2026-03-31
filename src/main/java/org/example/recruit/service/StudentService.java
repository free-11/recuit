package org.example.recruit.service;

import jakarta.servlet.http.HttpServletResponse;
import org.example.recruit.dto.StudentApplyDTO;

import java.util.List;
import java.util.Map;

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
    Map<String, Object> getStudentPage(int pageNum, int pageSize);

    /**
     * 分页查询学生信息（包含专业名称）
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    Map<String, Object> getStudentPageWithDetails(int pageNum, int pageSize);

    /**
     * 导出所有学生信息为 Excel
     * @param response HTTP 响应对象
     */
    void exportAllStudents(HttpServletResponse response);

    /**
     * 导出所有学生信息为 Excel（包含专业名称）
     * @param response HTTP 响应对象
     */
    void exportAllStudentsWithDetails(HttpServletResponse response);

    /**
     * 导出选中的学生信息为 Excel
     * @param studentNums 学生学号列表
     * @param response HTTP 响应对象
     */
    void exportSelectedStudents(List<Long> studentNums, HttpServletResponse response);

    /**
     * 导出选中的学生信息为 Excel（包含专业名称）
     * @param studentNums 学生学号列表
     * @param response HTTP 响应对象
     */
    void exportSelectedStudentsWithDetails(List<Long> studentNums, HttpServletResponse response);

}