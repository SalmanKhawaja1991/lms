package com.salman.controller;

import com.salman.dto.ApiResponseMessage;
import com.salman.dto.CourseDTO;
import com.salman.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponseMessage> createCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO createdCourse = courseService.createCourse(courseDTO);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Course created successfully")
                .status(HttpStatus.CREATED)
                .success(true)
                .data(createdCourse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> updateCourse(@PathVariable("id") String id, @RequestBody CourseDTO courseDTO) {
        courseDTO.setCourse_id(id); // Ensure the ID is set in the DTO
        CourseDTO updatedCourse = courseService.updateCourse(courseDTO);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Course updated successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(updatedCourse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Course deleted successfully")
                .status(HttpStatus.NO_CONTENT)
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> getCourseById(@PathVariable("id") Long id) {
        CourseDTO courseDTO = courseService.getCourseById(id);
        if (courseDTO != null) {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("Course retrieved successfully")
                    .status(HttpStatus.OK)
                    .success(true)
                    .data(courseDTO)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("Course not found")
                    .status(HttpStatus.NOT_FOUND)
                    .success(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseMessage> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Courses retrieved successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(courses)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @GetMapping("/student/{studentId}")
//    public List<CourseDTO> getCoursesByStudent(@PathVariable Long studentId) {
//        return courseService.getCoursesByStudent(studentId);
//    }
}
