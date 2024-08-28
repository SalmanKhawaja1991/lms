package com.salman.controller;

import com.salman.dto.ApiResponseMessage;
import com.salman.dto.EnrollmentDTO;
import com.salman.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<ApiResponseMessage> createEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
        EnrollmentDTO createdEnrollment = enrollmentService.createEnrollment(enrollmentDTO);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Enrollment created successfully")
                .status(HttpStatus.CREATED)
                .success(true)
                .data(createdEnrollment)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> updateEnrollment(@PathVariable("id") String id, @RequestBody EnrollmentDTO enrollmentDTO) {
        enrollmentDTO.setEnrollment_id(id); // Ensure the ID is set in the DTO
        EnrollmentDTO updatedEnrollment = enrollmentService.updateEnrollment(enrollmentDTO);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Enrollment updated successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(updatedEnrollment)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteEnrollment(@PathVariable("id") Long id) {
        enrollmentService.deleteEnrollment(id);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Enrollment deleted successfully")
                .status(HttpStatus.NO_CONTENT)
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> getEnrollmentById(@PathVariable("id") Long id) {
        EnrollmentDTO enrollmentDTO = enrollmentService.getEnrollmentById(id);
        if (enrollmentDTO != null) {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("Enrollment retrieved successfully")
                    .status(HttpStatus.OK)
                    .success(true)
                    .data(enrollmentDTO)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("Enrollment not found")
                    .status(HttpStatus.NOT_FOUND)
                    .success(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseMessage> getAllEnrollments() {
        List<EnrollmentDTO> enrollments = enrollmentService.getAllEnrollments();
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Enrollments retrieved successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(enrollments)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
