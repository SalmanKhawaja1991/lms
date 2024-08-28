package com.salman.repository;

import com.salman.entity.Course;
import com.salman.entity.Enrollment;
import com.salman.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {

    @Query("SELECT COUNT(e) > 0 FROM Enrollment e WHERE e.student.student_id = :studentId AND e.course.course_id = :courseId")
    boolean existsByStudentAndCourse(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

    // get students on course In
    @Query("SELECT e.student FROM Enrollment e WHERE e.course.course_id = :courseId")
    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);

    // get students on course Id
    @Query("SELECT e.course FROM Enrollment e WHERE e.student.student_id = :studentId")
    List<Course> findCoursesByStudentId(@Param("studentId") Long studentId);

    // query written for prevent course deletetion i which courses are written
    @Query("SELECT COUNT(e) > 0 FROM Enrollment e WHERE e.course.course_id = :courseId")
    boolean existsByCourseId(@Param("courseId") Long courseId);

    //Implement a method to calculate the total number of courses a student is enrolled in.
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.student.student_id = :studentId")
    int countByStudentId(@Param("studentId") Long studentId);

}
