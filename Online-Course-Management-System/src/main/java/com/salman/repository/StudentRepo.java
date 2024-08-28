package com.salman.repository;

import com.salman.entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {
    // Add custom query methods if needed
    @EntityGraph(attributePaths = {"profile", "enrollments"})

    boolean existsById(Long id);
    List<Student> findAll(); // This will fetch students with their profiles and enrollments

//    @Query("SELECT s FROM Student s JOIN Enrollment e ON s.student_id = e.student_id WHERE e.course_id = :courseId")
//    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);



}
