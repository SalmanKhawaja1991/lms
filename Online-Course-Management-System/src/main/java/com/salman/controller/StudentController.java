package com.salman.controller;

import com.salman.dto.ApiResponseMessage;
import com.salman.dto.CourseDTO;
import com.salman.dto.StudentDTO;
import com.salman.entity.Student;
import com.salman.repository.StudentRepo;
import com.salman.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<ApiResponseMessage> createStudent(@RequestBody StudentDTO studentDTO) {
        System.out.println("Before");
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        System.out.println("After");
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Student created successfully")
                .status(HttpStatus.CREATED)
                .success(true)
                .data(createdStudent)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> updateStudent(@PathVariable("id") String id, @RequestBody StudentDTO studentDTO) {
        studentDTO.setStudent_id(id); // Ensure the ID is set in the DTO
        StudentDTO updatedStudent = studentService.updateStudent(studentDTO);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Student updated successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(updatedStudent)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Student deleted successfully")
                .status(HttpStatus.NO_CONTENT)
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> getStudentById(@PathVariable("id") Long id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        if (studentDTO != null) {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("Student retrieved successfully")
                    .status(HttpStatus.OK)
                    .success(true)
                    .data(studentDTO)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("Student not found")
                    .status(HttpStatus.NOT_FOUND)
                    .success(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    //@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<ApiResponseMessage> getAllStudents() {

        System.out.println("called the Get All Student");

        List<StudentDTO> students = studentService.getAllStudents();
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Students retrieved successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(students)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponseMessage> getStudentsByCourse(@PathVariable("courseId") Long courseId) {
        List<StudentDTO> students = studentService.getStudentsByCourse(courseId);
        if (!students.isEmpty()) {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("Students retrieved successfully")
                    .status(HttpStatus.OK)
                    .success(true)
                    .data(students)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("No students found for the given course")
                    .status(HttpStatus.NOT_FOUND)
                    .success(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<ApiResponseMessage> getCoursesByStudentId(@PathVariable("id") Long studentId) {
        List<CourseDTO> courses = studentService.getCoursesByStudentId(studentId);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Courses retrieved successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(courses)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/total-courses")
    public ResponseEntity<ApiResponseMessage> getTotalCoursesEnrolled(@PathVariable("id") Long studentId) {
        int totalCourses = studentService.getTotalCoursesEnrolled(studentId);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Total courses enrolled: " + totalCourses)
                .status(HttpStatus.OK)
                .success(true)
                .data(totalCourses)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
