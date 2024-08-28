package com.salman.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentDTO {
    private String enrollment_id;
   // private StudentDTO student;
   // private CourseDTO course;
    private String enrollmentDate;
    private StudentSummaryDTO student;  // Ensure this field exists
    private CourseSummaryDTO course;    // Ensure this field exists

}
