package com.salman.service;

import com.salman.dto.CourseInstructorDTO;
import java.util.List;

public interface CourseInstructorService {

    CourseInstructorDTO createCourseInstructor(CourseInstructorDTO courseInstructorDTO);

    CourseInstructorDTO updateCourseInstructor(CourseInstructorDTO courseInstructorDTO);

    void deleteCourseInstructor(Long courseInstructorId);

    CourseInstructorDTO getCourseInstructorById(Long courseInstructorId);

    List<CourseInstructorDTO> getAllCourseInstructors();
}
