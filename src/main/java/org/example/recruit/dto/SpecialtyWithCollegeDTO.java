package org.example.recruit.dto;

import lombok.Data;

@Data
public class SpecialtyWithCollegeDTO {
    private Long id;
    private String specialtyName;
    private Long collegeId;
    private String collegeName;
}
