package com.salman.transformer;

import com.salman.dto.EnrollmentDTO;
import com.salman.dto.StudentSummaryDTO; // Use a summary DTO
import com.salman.dto.CourseSummaryDTO; // Use a summary DTO
import com.salman.entity.Enrollment;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentTransformer {

    public static EnrollmentDTO toDTO(Enrollment enrollment) {
        if (enrollment == null) return null;

        EnrollmentDTO dto = new EnrollmentDTO();
        if(enrollment.getEnrollment_id()!= null)
        dto.setEnrollment_id(String.valueOf(enrollment.getEnrollment_id()));
        dto.setEnrollmentDate(enrollment.getEnrollmentDate().toString());

        // Use summary or specific fields to avoid recursion
        if (enrollment.getStudent() != null) {
            StudentSummaryDTO studentSummary = new StudentSummaryDTO();
            studentSummary.setStudent_id(String.valueOf(enrollment.getStudent().getStudent_id()));
            studentSummary.setName(enrollment.getStudent().getName());
            dto.setStudent(studentSummary);
        }

        if (enrollment.getCourse() != null) {
            CourseSummaryDTO courseSummary = new CourseSummaryDTO();
            courseSummary.setCourse_id(String.valueOf(enrollment.getCourse().getCourse_id()));
            courseSummary.setTitle(enrollment.getCourse().getTitle());
            dto.setCourse(courseSummary);
        }

        return dto;
    }

    public static Enrollment toEntity(EnrollmentDTO dto) {
        if (dto == null) return null;

        Enrollment enrollment = new Enrollment();
        if(dto.getEnrollment_id()!= null)
        enrollment.setEnrollment_id(Long.parseLong(dto.getEnrollment_id()));
        enrollment.setEnrollmentDate(LocalDate.parse(dto.getEnrollmentDate()));

        // Convert back if needed; be cautious of potential recursion
        // enrollment.setStudent(StudentTransformer.toEntity(dto.getStudent()));
        // enrollment.setCourse(CourseTransformer.toEntity(dto.getCourse()));

        return enrollment;
    }

    public static List<EnrollmentDTO> toDTOList(List<Enrollment> enrollments) {
        if (enrollments == null) return null;

        return enrollments.stream()
                .map(EnrollmentTransformer::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Enrollment> toEntityList(List<EnrollmentDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .map(EnrollmentTransformer::toEntity)
                .collect(Collectors.toList());
    }
}
