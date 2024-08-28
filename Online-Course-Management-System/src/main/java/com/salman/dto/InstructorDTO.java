package com.salman.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorDTO {
    private String instructor_id;
    private String name;
    private String expertise;
  //  private List<CourseInstructorDTO> courseInstructors;
    private List<CourseInstructorSummaryDTO> courseInstructors; // Change to Summary DT
}
