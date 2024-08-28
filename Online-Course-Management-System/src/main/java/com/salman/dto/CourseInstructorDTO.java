package com.salman.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseInstructorDTO {
    private String course_instructor_id;
    //private CourseDTO course;
    //private InstructorDTO instructor;

    private CourseSummaryDTO course; // Change to CourseSummaryDTO
    private InstructorSummaryDTO instructor; // Change to InstructorSummaryDTO
}
