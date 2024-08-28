package com.salman.transformer;

import com.salman.dto.InstructorDTO;
import com.salman.dto.InstructorSummaryDTO; // Ensure this DTO is correctly defined
import com.salman.dto.CourseInstructorSummaryDTO; // Ensure this DTO is correctly defined
import com.salman.entity.Instructor;
import com.salman.entity.CourseInstructor;
import java.util.List;
import java.util.stream.Collectors;

public class InstructorTransformer {

    public static InstructorDTO toDTO(Instructor instructor) {
        if (instructor == null) return null;


        InstructorDTO dto = new InstructorDTO();

        dto.setInstructor_id(String.valueOf(instructor.getInstructor_id()));

        dto.setName(instructor.getName());
        dto.setExpertise(instructor.getExpertise());

        // Log or debug this value
        System.out.println("Expertise: " + instructor.getExpertise());

        // Include CourseInstructors if needed
        if (instructor.getCourseInstructors() != null) {
            List<CourseInstructorSummaryDTO> courseInstructorSummaries = instructor.getCourseInstructors().stream()
                    .map(CourseInstructorTransformer::toSummaryDTO) // Convert to CourseInstructorSummaryDTO
                    .collect(Collectors.toList());
            dto.setCourseInstructors(courseInstructorSummaries);
        }

        return dto;
    }

    public static Instructor toEntity(InstructorDTO dto) {
        if (dto == null) return null;

        Instructor instructor = new Instructor();
        //instructor.setInstructor_id(dto.getInstructor_id() != null ? Long.parseLong(dto.getInstructor_id()) : null);
        if(!dto.getInstructor_id().equals("")){
            instructor.setInstructor_id(Long.parseLong(dto.getInstructor_id()));
        }
        instructor.setName(dto.getName());
        instructor.setExpertise(dto.getExpertise());

        // Optionally handle CourseInstructors if needed
        if (dto.getCourseInstructors() != null) {
            List<CourseInstructor> courseInstructors = dto.getCourseInstructors().stream()
                    .map(CourseInstructorTransformer::toEntity) // Convert to CourseInstructor entity
                    .collect(Collectors.toList());
            instructor.setCourseInstructors(courseInstructors);
        }

        return instructor;
    }

    public static List<InstructorDTO> toDTOList(List<Instructor> instructors) {
        if (instructors == null) return null;

        return instructors.stream()
                .map(InstructorTransformer::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Instructor> toEntityList(List<InstructorDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .map(InstructorTransformer::toEntity)
                .collect(Collectors.toList());
    }
}
