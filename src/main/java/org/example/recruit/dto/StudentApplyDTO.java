package org.example.recruit.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StudentApplyDTO {
    private Long id;
    private String name;
    private Long studentNum;
    private String grade;
    private String specialty;
    private Long qq;
    private Long phone;
    private Date submissionTime;
}
