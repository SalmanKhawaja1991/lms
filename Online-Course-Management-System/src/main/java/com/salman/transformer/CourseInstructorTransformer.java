package com.salman.transformer;

import com.salman.dto.*;
import com.salman.entity.CourseInstructor;
import com.salman.entity.Course;
import com.salman.entity.Instructor;
import java.util.List;
import java.util.stream.Collectors;

public class CourseInstructorTransformer {

    public static CourseInstructorDTO toDTO(CourseInstructor courseInstructor) {
        if (courseInstructor == null) return null;

        CourseInstructorDTO dto = new CourseInstructorDTO();
        dto.setCourse_instructor_id(String.valueOf(courseInstructor.getCourse_instructor_id()));

        // Use summary or specific fields to avoid recursion
        if (courseInstructor.getCourse() != null) {
            CourseSummaryDTO courseSummary = new CourseSummaryDTO();
            courseSummary.setCourse_id(String.valueOf(courseInstructor.getCourse().getCourse_id()));
            courseSummary.setTitle(courseInstructor.getCourse().getTitle());
            dto.setCourse(courseSummary);
        }

        if (courseInstructor.getInstructor() != null) {
            InstructorSummaryDTO instructorSummary = new InstructorSummaryDTO();
            instructorSummary.setInstructor_id(String.valueOf(courseInstructor.getInstructor().getInstructor_id()));
            instructorSummary.setName(courseInstructor.getInstructor().getName());
            dto.setInstructor(instructorSummary);
        }

        return dto;
    }

    public static CourseInstructorSummaryDTO toSummaryDTO(CourseInstructor courseInstructor) {
        if (courseInstructor == null) return null;

        CourseInstructorSummaryDTO dto = new CourseInstructorSummaryDTO();
        dto.setCourseInstructorId(String.valueOf(courseInstructor.getCourse_instructor_id()));
        dto.setCourseTitle(courseInstructor.getCourse() != null ? courseInstructor.getCourse().getTitle() : null);
        dto.setInstructorName(courseInstructor.getInstructor() != null ? courseInstructor.getInstructor().getName() : null);

        return dto;
    }

    public static CourseInstructor toEntity(CourseInstructorDTO dto) {
        if (dto == null) return null;

        CourseInstructor courseInstructor = new CourseInstructor();

        if(dto.getCourse_instructor_id()!=(null)) {
            courseInstructor.setCourse_instructor_id(Long.parseLong(dto.getCourse_instructor_id()));
        }//courseInstructor.setInstructor();

        // Convert back if needed; be cautious of potential recursion
       //  courseInstructor.setCourse(CourseTransformer.toEntity(dto.getCourse()));
       //  courseInstructor.setInstructor(InstructorTransformer.toEntity(dto.getInstructor()));

        // Use summary or specific fields to avoid recursion
        if (dto.getInstructor() != null) {
            InstructorSummaryDTO Summary = new InstructorSummaryDTO();
            Summary.setInstructor_id(String.valueOf(dto.getInstructor().getInstructor_id()));
            Summary.setName(dto.getInstructor().getName());
            Summary.setExpertise(dto.getInstructor().getExpertise());
            dto.setInstructor(Summary);
        }

        if (dto.getCourse() != null) {
            CourseSummaryDTO courseSummary = new CourseSummaryDTO();
            courseSummary.setCourse_id(String.valueOf(dto.getCourse().getCourse_id()));
            courseSummary.setTitle(dto.getCourse().getTitle());
            dto.setCourse(courseSummary);
        }
        return courseInstructor;
    }

    public static CourseInstructor toEntity(CourseInstructorSummaryDTO dto) {
        if (dto == null) return null;

        CourseInstructor courseInstructor = new CourseInstructor();
        courseInstructor.setCourse_instructor_id(dto.getCourseInstructorId() != null ? Long.parseLong(dto.getCourseInstructorId()) : null);

        // Optionally set course and instructor if needed
        // courseInstructor.setCourse(new Course(dto.getCourseId()));
        // courseInstructor.setInstructor(new Instructor(dto.getInstructorId()));

        return courseInstructor;
    }

    public static List<CourseInstructorDTO> toDTOList(List<CourseInstructor> courseInstructors) {
        if (courseInstructors == null) return null;

        return courseInstructors.stream()
                .map(CourseInstructorTransformer::toDTO)
                .collect(Collectors.toList());
    }

    public static List<CourseInstructorSummaryDTO> toSummaryDTOList(List<CourseInstructor> courseInstructors) {
        if (courseInstructors == null) return null;

        return courseInstructors.stream()
                .map(CourseInstructorTransformer::toSummaryDTO)
                .collect(Collectors.toList());
    }

    public static List<CourseInstructor> toEntityList(List<CourseInstructorDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .map(CourseInstructorTransformer::toEntity)
                .collect(Collectors.toList());
    }
}
