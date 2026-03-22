package org.example.recruit.entity;

import lombok.Data;
import java.util.Date;

/**
 * 学生实体类
 */
@Data
public class Student {
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 学号
     */
    private Long studentNum;
    
    /**
     * 年级
     */
    private String grade;
    
    /**
     * 专业
     */
    private String specialty;
    
    /**
     * QQ
     */
    private Long qq;
    
    /**
     * 手机号码
     */
    private Long phone;
    
    /**
     * 提交时间
     */
    private Date submissionTime;
}