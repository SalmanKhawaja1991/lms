package com.salman.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorSummaryDTO {
    private String instructor_id;
    private String name;
    private String expertise; // Ensure this field exists


}
