package com.salman.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseInstructorSummaryDTO {
    private String courseInstructorId;
    private String courseTitle;
    private String instructorName;
}
