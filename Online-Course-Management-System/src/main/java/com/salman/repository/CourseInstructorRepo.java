package com.salman.repository;

import com.salman.entity.CourseInstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseInstructorRepo extends JpaRepository<CourseInstructor, Long> {
    // Add custom query methods if needed
    @Query("SELECT COUNT(e) > 0 FROM CourseInstructor e WHERE e.instructor.instructor_id = :instructorId AND e.course.course_id = :courseId")
    boolean existsByInstructorAndCourse(@Param("instructorId") Long instructorId, @Param("courseId") Long courseId);


    // count the noumber of cources of the instructors
    @Query("SELECT ci.instructor.instructor_id, COUNT(ci.course) FROM CourseInstructor ci GROUP BY ci.instructor.instructor_id")
    List<Object[]> countCoursesPerInstructor();


    // count the noumber of cources of the instructors By Id
    @Query("SELECT COUNT(ci) FROM CourseInstructor ci WHERE ci.instructor.instructor_id = :instructorId")
    long countCoursesByInstructorId(@Param("instructorId") Long instructorId);
}
