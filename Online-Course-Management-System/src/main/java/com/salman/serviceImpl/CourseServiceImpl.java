package com.salman.serviceImpl;


import com.salman.dto.CourseDTO;
import com.salman.entity.Course;
import com.salman.exception.CourseDeletionException;
import com.salman.repository.CourseRepo;
import com.salman.repository.EnrollmentRepo;
import com.salman.service.CourseService;
import com.salman.transformer.CourseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private EnrollmentRepo enrollmentRepo;

//    @Override
//    public void deleteCourse(Long courseId) {
//        // Check if there are any enrollments associated with the course
//        boolean hasEnrollments = enrollmentRepo.existsByCourseId(courseId);
//
//        if (hasEnrollments) {
//            throw new CourseDeletionException("Cannot delete course with enrolled students.");
//        }
//
//        // Proceed with deletion if no enrollments are found
//        courseRepo.deleteById(courseId);
//    }


    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = CourseTransformer.toEntity(courseDTO);
        course = courseRepo.save(course);
        return CourseTransformer.toDTO(course);
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO) {
        Course course = CourseTransformer.toEntity(courseDTO);
        course = courseRepo.save(course);
        return CourseTransformer.toDTO(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        // Check if there are any enrollments associated with the course
        boolean hasEnrollments = enrollmentRepo.existsByCourseId(courseId);

        if (hasEnrollments) {
            throw new CourseDeletionException("Cannot delete course with enrolled students.");
        }


        courseRepo.deleteById(courseId);
    }

    @Override
    public CourseDTO getCourseById(Long courseId) {
        return courseRepo.findById(courseId)
                .map(CourseTransformer::toDTO)
                .orElse(null);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return CourseTransformer.toDTOList(courseRepo.findAll());
    }
}
