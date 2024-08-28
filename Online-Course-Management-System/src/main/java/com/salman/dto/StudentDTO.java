package com.salman.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class StudentDTO {
    private String student_id;
    private String name;
    private String email;
    private String dateOfBirth;
    private String profileBiodata; // Add this field to hold profile data
    private ProfileSummaryDTO profile; // Include a profile summary
   // @JsonIgnore
   // private ProfileDTO profile;
    private List<EnrollmentDTO> enrollments;


}
