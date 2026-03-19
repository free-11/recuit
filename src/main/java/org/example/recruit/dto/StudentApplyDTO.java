package org.example.recruit.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public void setSubmissionTime(LocalDateTime localDateTime) {
        this.submissionTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    }

