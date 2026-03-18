package org.example.recruit.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Student {
    private Long id;
    private String name;
    private Long studentNum;
    private String grade;
    private String specialty;
    private Long qq;
    private Long phone;
    private Date submissionTime;
}