package com.salman.service;

import com.salman.dto.CourseDTO;
import java.util.List;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO updateCourse(CourseDTO courseDTO);

    void deleteCourse(Long courseId);

    CourseDTO getCourseById(Long courseId);

    List<CourseDTO> getAllCourses();

   // List<CourseDTO> getCoursesByStudent(Long studentId);

}
