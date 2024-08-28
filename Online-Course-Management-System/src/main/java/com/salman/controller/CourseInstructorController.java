package com.salman.controller;

import com.salman.dto.ApiResponseMessage;
import com.salman.dto.CourseInstructorDTO;
import com.salman.service.CourseInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/course-instructors")
public class CourseInstructorController {

    @Autowired
    private CourseInstructorService courseInstructorService;

    @PostMapping
    public ResponseEntity<ApiResponseMessage> createCourseInstructor(@RequestBody CourseInstructorDTO courseInstructorDTO) {
        CourseInstructorDTO createdCourseInstructor = courseInstructorService.createCourseInstructor(courseInstructorDTO);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("CourseInstructor created successfully")
                .status(HttpStatus.CREATED)
                .success(true)
                .data(createdCourseInstructor)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> updateCourseInstructor(@PathVariable("id") String id, @RequestBody CourseInstructorDTO courseInstructorDTO) {
        courseInstructorDTO.setCourse_instructor_id(id); // Ensure the ID is set in the DTO
        CourseInstructorDTO updatedCourseInstructor = courseInstructorService.updateCourseInstructor(courseInstructorDTO);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("CourseInstructor updated successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(updatedCourseInstructor)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteCourseInstructor(@PathVariable("id") Long id) {
        courseInstructorService.deleteCourseInstructor(id);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("CourseInstructor deleted successfully")
                .status(HttpStatus.NO_CONTENT)
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> getCourseInstructorById(@PathVariable("id") Long id) {
        CourseInstructorDTO courseInstructorDTO = courseInstructorService.getCourseInstructorById(id);
        if (courseInstructorDTO != null) {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("CourseInstructor retrieved successfully")
                    .status(HttpStatus.OK)
                    .success(true)
                    .data(courseInstructorDTO)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("CourseInstructor not found")
                    .status(HttpStatus.NOT_FOUND)
                    .success(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseMessage> getAllCourseInstructors() {
        List<CourseInstructorDTO> courseInstructors = courseInstructorService.getAllCourseInstructors();
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("CourseInstructors retrieved successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(courseInstructors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
