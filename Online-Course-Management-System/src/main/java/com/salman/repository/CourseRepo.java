package com.salman.repository;

import com.salman.entity.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {
    // Add custom query methods if needed
//    @EntityGraph(attributePaths = {"enrollments", "courseInstructors"})
//    List<Course> findAll();

//    @EntityGraph(attributePaths = {"enrollments", "courseInstructors"})
//    List<Course> findAll();

    boolean existsById(Long id);

//    @Query("SELECT c FROM Course c JOIN Enrollment e ON c.course_id = e.course_id WHERE e.student_id = :studentId")
//    List<Course> findCoursesByStudentId(@Param("studentId") Long studentId);

//    @Query("SELECT c FROM Course c JOIN Enrollment e ON c.id = e.course.id WHERE e.studentId = :studentId")
//    List<Course> findCoursesByStudentId(@Param("studentId") Long studentId);

}
