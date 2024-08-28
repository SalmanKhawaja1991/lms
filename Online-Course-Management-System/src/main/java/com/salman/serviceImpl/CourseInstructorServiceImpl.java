package com.salman.serviceImpl;

import com.salman.dto.CourseInstructorDTO;
import com.salman.entity.Course;
import com.salman.entity.CourseInstructor;
import com.salman.entity.Instructor;
import com.salman.entity.Student;
import com.salman.repository.CourseInstructorRepo;
import com.salman.repository.CourseRepo;
import com.salman.repository.InstructorRepo;
import com.salman.repository.StudentRepo;
import com.salman.service.CourseInstructorService;
import com.salman.transformer.CourseInstructorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseInstructorServiceImpl implements CourseInstructorService {

    @Autowired
    private CourseInstructorRepo courseInstructorRepo;

    @Autowired
    private InstructorRepo instructorRepo;
    @Autowired
    private CourseRepo courseRepo;


    @Override
    public CourseInstructorDTO createCourseInstructor(CourseInstructorDTO courseInstructorDTO) {

        Long instructorId = Long.parseLong(courseInstructorDTO.getInstructor().getInstructor_id());
        Long courseId = Long.parseLong(courseInstructorDTO.getCourse().getCourse_id());

        // Validate student and course existence
        if (!instructorRepo.existsById(instructorId)) {
            throw new IllegalArgumentException("Instructor  does not exist.");
        }

        if (!courseRepo.existsById(courseId)) {
            throw new IllegalArgumentException("Course does not exist.");
        }

        if (isStudentEnrolledInCourse(instructorId, courseId)) {
            throw new IllegalArgumentException("Student is already enrolled in this course.");
        }

        CourseInstructor courseInstructor = CourseInstructorTransformer.toEntity(courseInstructorDTO);

        Instructor instructor=instructorRepo.getReferenceById(instructorId);
        Course course=courseRepo.getReferenceById(courseId);

//        if(student==null){
//            throw new IllegalArgumentException("Student Does not exist With Id ."+studentId);
//        }
//        if(course==null){
//            throw new IllegalArgumentException("Course Does not exist With Id ."+courseId);
//        }

        courseInstructor.setCourse(course);
        courseInstructor.setInstructor(instructor);

        courseInstructor = courseInstructorRepo.save(courseInstructor);
        return CourseInstructorTransformer.toDTO(courseInstructor);
    }

   // @Override
    public boolean isStudentEnrolledInCourse(Long instructorId, Long courseId) {
        return courseInstructorRepo.existsByInstructorAndCourse(instructorId, courseId);
    }


    @Override
    public CourseInstructorDTO updateCourseInstructor(CourseInstructorDTO courseInstructorDTO) {
        CourseInstructor courseInstructor = CourseInstructorTransformer.toEntity(courseInstructorDTO);
        courseInstructor = courseInstructorRepo.save(courseInstructor);
        return CourseInstructorTransformer.toDTO(courseInstructor);
    }

    @Override
    public void deleteCourseInstructor(Long courseInstructorId) {
        courseInstructorRepo.deleteById(courseInstructorId);
    }

    @Override
    public CourseInstructorDTO getCourseInstructorById(Long courseInstructorId) {
        return courseInstructorRepo.findById(courseInstructorId)
                .map(CourseInstructorTransformer::toDTO)
                .orElse(null);
    }

    @Override
    public List<CourseInstructorDTO> getAllCourseInstructors() {
        return CourseInstructorTransformer.toDTOList(courseInstructorRepo.findAll());
    }

}

