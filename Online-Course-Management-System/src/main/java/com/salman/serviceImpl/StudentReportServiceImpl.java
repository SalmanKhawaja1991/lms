package com.salman.serviceImpl;

import com.salman.dto.StudentDTO;
import com.salman.entity.Student;
import com.salman.transformer.StudentTransformer;
import com.salman.repository.StudentRepo;
import com.salman.repository.CourseRepo;
import com.salman.repository.EnrollmentRepo;
import com.salman.service.StudentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentReportServiceImpl implements StudentReportService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Override
    public List<StudentDTO> generateStudentReport() {
        List<Student> students = studentRepo.findAll();
        return students.stream().map(StudentTransformer::toDTO).collect(Collectors.toList());
    }
}
