package com.salman.serviceImpl;


import com.salman.dto.EnrollmentDTO;
import com.salman.entity.Course;
import com.salman.entity.Enrollment;
import com.salman.entity.Student;
import com.salman.repository.CourseRepo;
import com.salman.repository.EnrollmentRepo;
import com.salman.repository.StudentRepo;
import com.salman.service.EnrollmentService;
import com.salman.transformer.EnrollmentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepo enrollmentRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private CourseRepo courseRepo;



    @Override
    public boolean isStudentEnrolledInCourse(Long studentId, Long courseId) {
        return enrollmentRepo.existsByStudentAndCourse(studentId, courseId);
    }

    @Override
    public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) {

        Long studentId = Long.parseLong(enrollmentDTO.getStudent().getStudent_id());
        Long courseId = Long.parseLong(enrollmentDTO.getCourse().getCourse_id());

        // Validate student and course existence
        if (!studentRepo.existsById(studentId)) {
            throw new IllegalArgumentException("Student does not exist.");
        }

        if (!courseRepo.existsById(courseId)) {
            throw new IllegalArgumentException("Course does not exist.");
        }

        if (isStudentEnrolledInCourse(studentId, courseId)) {
            throw new IllegalArgumentException("Student is already enrolled in this course.");
        }

        Enrollment enrollment = EnrollmentTransformer.toEntity(enrollmentDTO);

        Student student=studentRepo.getReferenceById(studentId);
        Course course=courseRepo.getReferenceById(courseId);

//        if(student==null){
//            throw new IllegalArgumentException("Student Does not exist With Id ."+studentId);
//        }
//        if(course==null){
//            throw new IllegalArgumentException("Course Does not exist With Id ."+courseId);
//        }

        enrollment.setCourse(course);
        enrollment.setStudent(student);

        enrollment = enrollmentRepo.save(enrollment);
        return EnrollmentTransformer.toDTO(enrollment);
    }

    @Override
    public EnrollmentDTO updateEnrollment(EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = EnrollmentTransformer.toEntity(enrollmentDTO);
        enrollment = enrollmentRepo.save(enrollment);
        return EnrollmentTransformer.toDTO(enrollment);
    }

    @Override
    public void deleteEnrollment(Long enrollmentId) {
        enrollmentRepo.deleteById(enrollmentId);
    }

    @Override
    public EnrollmentDTO getEnrollmentById(Long enrollmentId) {
        return enrollmentRepo.findById(enrollmentId)
                .map(EnrollmentTransformer::toDTO)
                .orElse(null);
    }

    @Override
    public List<EnrollmentDTO> getAllEnrollments() {
        return EnrollmentTransformer.toDTOList(enrollmentRepo.findAll());
    }
}
