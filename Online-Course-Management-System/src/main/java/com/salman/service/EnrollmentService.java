package com.salman.service;

import com.salman.dto.EnrollmentDTO;
import java.util.List;

public interface EnrollmentService {

    EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO);

    EnrollmentDTO updateEnrollment(EnrollmentDTO enrollmentDTO);

    void deleteEnrollment(Long enrollmentId);

    EnrollmentDTO getEnrollmentById(Long enrollmentId);

    List<EnrollmentDTO> getAllEnrollments();

    boolean isStudentEnrolledInCourse(Long studentId, Long courseId);
}
