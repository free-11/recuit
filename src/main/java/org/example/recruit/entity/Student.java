package org.example.recruit.entity;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
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
    @ColumnWidth(10)
    private Long id;
    
    @ColumnWidth(15)
    private String name;
    
    @TableField("student_num")
    @ColumnWidth(15)
    private Long studentNum;
    
    @ColumnWidth(10)
    private String grade;
    
    @ColumnWidth(20)
    private String specialty;
    
    @ColumnWidth(15)
    private Long qq;
    
    @ColumnWidth(15)
    private Long phone;
    
    @TableField("submission_time")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date submissionTime;
}