package org.example.recruit.dto;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.util.Date;

@Data
public class StudentWithDetailsDTO {
    @ColumnWidth(10)
    private Long id;
    
    @ColumnWidth(15)
    private String name;
    
    @ColumnWidth(15)
    private Long studentNum;
    
    @ColumnWidth(10)
    private String grade;
    
    @ColumnWidth(15)
    private Long specialtyId;
    
    @ColumnWidth(15)
    private String specialtyName;  // 专业名称
    
    @ColumnWidth(15)
    private Long qq;
    
    @ColumnWidth(15)
    private Long phone;
    
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date submissionTime;
}