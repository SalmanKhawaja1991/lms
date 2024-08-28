package com.salman.service;

import com.salman.dto.CourseDTO;
import com.salman.dto.StudentDTO;
import java.util.List;

public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(StudentDTO studentDTO);

    void deleteStudent(Long studentId);

    StudentDTO getStudentById(Long studentId);

    List<StudentDTO> getAllStudents();


    List<StudentDTO> getStudentsByCourse(Long courseId);
    List<CourseDTO> getCoursesByStudentId(Long studentId);

    int getTotalCoursesEnrolled(Long studentId);

  //  List<StudentDTO> getStudentsByCourse(Long courseId);
}
