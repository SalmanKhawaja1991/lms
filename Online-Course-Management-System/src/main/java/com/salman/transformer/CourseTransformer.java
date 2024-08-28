package com.salman.transformer;

import com.salman.dto.CourseDTO;
import com.salman.entity.Course;
import java.util.List;
import java.util.stream.Collectors;
public class CourseTransformer {

    public static CourseDTO toDTO(Course course) {
        if (course == null) return null;

        CourseDTO dto = new CourseDTO();

        if(course.getCourse_id()!=null)
        dto.setCourse_id(String.valueOf(course.getCourse_id()));
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());

        dto.setEnrollments(EnrollmentTransformer.toDTOList(course.getEnrollments()));
        dto.setCourseInstructors(CourseInstructorTransformer.toDTOList(course.getCourseInstructors()));

        return dto;
    }

    public static Course toEntity(CourseDTO dto) {
        if (dto == null) return null;

        Course course = new Course();

        //if(dto.getCourse_id()!=null)
        if(!dto.getCourse_id().equals("")){
            course.setCourse_id(Long.parseLong(dto.getCourse_id()));
        }


        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setEnrollments(EnrollmentTransformer.toEntityList(dto.getEnrollments()));
        course.setCourseInstructors(CourseInstructorTransformer.toEntityList(dto.getCourseInstructors()));
        return course;
    }

    public static List<CourseDTO> toDTOList(List<Course> courses) {
        if (courses == null) return null;

        return courses.stream()
                .map(CourseTransformer::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Course> toEntityList(List<CourseDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .map(CourseTransformer::toEntity)
                .collect(Collectors.toList());
    }
}


