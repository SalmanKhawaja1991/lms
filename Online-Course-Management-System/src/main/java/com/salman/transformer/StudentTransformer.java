package com.salman.transformer;

import com.salman.dto.ProfileSummaryDTO;
import com.salman.dto.StudentDTO;
import com.salman.entity.Student;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StudentTransformer {

    public static StudentDTO toDTO(Student student) {
        if (student == null) return null;

        StudentDTO dto = new StudentDTO();
        dto.setStudent_id(String.valueOf(student.getStudent_id()));
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setDateOfBirth(student.getDateOfBirth().toString());

        // Include a summary of Profile
        if (student.getProfile() != null) {
            ProfileSummaryDTO profileSummary = new ProfileSummaryDTO();
            profileSummary.setBiodata(student.getProfile().getBiodata());
            dto.setProfile(profileSummary);
        }

        dto.setEnrollments(EnrollmentTransformer.toDTOList(student.getEnrollments()));
        return dto;
    }

    public static Student toEntity(StudentDTO dto) {
        if (dto == null) return null;

        Student student = new Student();

        //student.setStudent_id(dto.getStudent_id() != null ? Long.parseLong(dto.getStudent_id()) : null);
        if(!dto.getStudent_id().equals("")){
            student.setStudent_id(Long.parseLong(dto.getStudent_id()));
        }
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));

        // Optionally handle profile and enrollments if needed
        // student.setProfile(ProfileTransformer.toEntity(dto.getProfile()));
        student.setEnrollments(EnrollmentTransformer.toEntityList(dto.getEnrollments()));

        return student;
    }

    public static List<StudentDTO> toDTOList(List<Student> students) {
        if (students == null) return null;

        return students.stream()
                .map(StudentTransformer::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Student> toEntityList(List<StudentDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .map(StudentTransformer::toEntity)
                .collect(Collectors.toList());
    }



//    // Convert from Student entity to StudentDTO
//    public StudentDTO toDTOs(Student student) {
//        if (student == null) {
//            return null;
//        }
//
//        return new StudentDTO(
//                student.getStudent_id(),
//                student.getName(),
//                student.getEmail(),
//                student.getDateOfBirth()
//        );
//    }

//    // Convert from StudentDTO to Student entity
//    public Student toEntitys(StudentDTO studentDTO) {
//        if (studentDTO == null) {
//            return null;
//        }
//
//        Student student = new Student();
//        if(studentDTO.getStudent_id()!=null||!studentDTO.getStudent_id().equals(""))
//        student.setStudent_id(Long.parseLong(studentDTO.getStudent_id()));
//        student.setName(studentDTO.getName());
//        student.setEmail(studentDTO.getEmail());
//        //student.setDateOfBirth(Date.parse(studentDTO.getDateOfBirth()));
//
//        return student;
//    }

}
