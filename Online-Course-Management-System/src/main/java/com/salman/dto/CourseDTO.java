package com.salman.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
    private String course_id;
    private String title;
    private String description;
    private List<EnrollmentDTO> enrollments;
    private List<CourseInstructorDTO> courseInstructors;
}
