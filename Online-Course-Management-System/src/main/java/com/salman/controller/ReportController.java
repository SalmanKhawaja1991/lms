package com.salman.controller;

import com.salman.dto.ApiResponseMessage;
import com.salman.dto.StudentDTO;
import com.salman.service.StudentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private StudentReportService studentReportService;

    @GetMapping("/student-report")
    public ResponseEntity<ApiResponseMessage> getStudentReport() {
        List<StudentDTO> report = studentReportService.generateStudentReport();
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Student report generated successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(report)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
