package org.example.recruit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("student")
public class Student {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    @TableField("student_num")
    private Long studentNum;
    private String grade;
    private String specialty;
    private Long qq;
    private Long phone;
    @TableField("submission_time")
    private Date submissionTime;
}