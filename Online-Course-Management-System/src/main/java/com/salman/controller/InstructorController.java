package com.salman.controller;

import com.salman.dto.ApiResponseMessage;
import com.salman.dto.InstructorDTO;
import com.salman.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping
    public ResponseEntity<ApiResponseMessage> createInstructor(@RequestBody InstructorDTO instructorDTO) {
        InstructorDTO createdInstructor = instructorService.createInstructor(instructorDTO);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Instructor created successfully")
                .status(HttpStatus.CREATED)
                .success(true)
                .data(createdInstructor)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> updateInstructor(@PathVariable("id") String id, @RequestBody InstructorDTO instructorDTO) {
        instructorDTO.setInstructor_id(id); // Ensure the ID is set in the DTO
        InstructorDTO updatedInstructor = instructorService.updateInstructor(instructorDTO);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Instructor updated successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(updatedInstructor)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteInstructor(@PathVariable("id") Long id) {
        instructorService.deleteInstructor(id);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Instructor deleted successfully")
                .status(HttpStatus.NO_CONTENT)
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> getInstructorById(@PathVariable("id") Long id) {
        InstructorDTO instructorDTO = instructorService.getInstructorById(id);
        if (instructorDTO != null) {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("Instructor retrieved successfully")
                    .status(HttpStatus.OK)
                    .success(true)
                    .data(instructorDTO)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("Instructor not found")
                    .status(HttpStatus.NOT_FOUND)
                    .success(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseMessage> getAllInstructors() {
        List<InstructorDTO> instructors = instructorService.getAllInstructors();
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Instructors retrieved successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(instructors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/course-count")
    public ResponseEntity<ApiResponseMessage> getNumberOfCoursesPerInstructor() {
        Map<Long, Integer> coursesCount = instructorService.getNumberOfCoursesPerInstructor();
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Number of courses per instructor retrieved successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(coursesCount)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{instructorId}/course-count")
    public ResponseEntity<ApiResponseMessage> getNumberOfCoursesByInstructorId(@PathVariable Long instructorId) {
        long courseCount = instructorService.getNumberOfCoursesByInstructorId(instructorId);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Number of courses for instructor retrieved successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(courseCount)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
