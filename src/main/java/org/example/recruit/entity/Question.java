package org.example.recruit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("questions")
public class Question {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
}