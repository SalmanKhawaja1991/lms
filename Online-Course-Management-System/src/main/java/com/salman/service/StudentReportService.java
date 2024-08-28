package com.salman.service;

import com.salman.dto.StudentDTO;

import java.util.List;

public interface StudentReportService {
    List<StudentDTO> generateStudentReport();
}