package com.salman.serviceImpl;


import com.salman.dto.CourseDTO;
import com.salman.dto.StudentDTO;
import com.salman.entity.Course;
import com.salman.entity.Profile;
import com.salman.entity.Student;
import com.salman.repository.EnrollmentRepo;
import com.salman.repository.ProfileRepo;
import com.salman.repository.StudentRepo;
import com.salman.service.StudentService;
import com.salman.transformer.CourseTransformer;
import com.salman.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Autowired
    private StudentTransformer studentTransformer;


    @Override
    public int getTotalCoursesEnrolled(Long studentId) {
        if (!studentRepo.existsById(studentId)) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }
        return enrollmentRepo.countByStudentId(studentId);
    }

    @Override
    public List<CourseDTO> getCoursesByStudentId(Long studentId) {
        List<Course> courses = enrollmentRepo.findCoursesByStudentId(studentId);
        return CourseTransformer.toDTOList(courses);
    }


    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = StudentTransformer.toEntity(studentDTO);
        student = studentRepo.save(student);

        String str= "";
        str+=student.getName()+" ";
        str+=student.getEmail()+" ";
        str+=student.getAge();

        Profile profile = new Profile();
        profile.setBiodata(str);
        profile.setStudent(student);
        profileRepo.save(profile);
        student.setProfile(profile);
        return StudentTransformer.toDTO(student);
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Student student = StudentTransformer.toEntity(studentDTO);
        student = studentRepo.save(student);


        String str= "";
        str+=student.getName()+" ";
        str+=student.getEmail()+" ";

        LocalDate birthDate =student.getDateOfBirth();
        // Step 1: Get the current date
        LocalDate currentDate = LocalDate.now();

        // Step 2: Calculate the age
        int age = Period.between(birthDate, currentDate).getYears();



        str+=" "+age;

        Profile profile = profileRepo.getReferenceById(student.getStudent_id());
        profile.setBiodata(str);

     //   profile.setStudent(student);

        profileRepo.save(profile);
        student.setProfile(profile);




        return StudentTransformer.toDTO(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepo.deleteById(studentId);
    }

    @Override
    public StudentDTO getStudentById(Long studentId) {
        return studentRepo.findById(studentId)
                .map(StudentTransformer::toDTO)
                .orElse(null);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return StudentTransformer.toDTOList(studentRepo.findAll());
    }


    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if (birthDate != null && currentDate != null) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            throw new IllegalArgumentException("The birthDate and currentDate cannot be null");
        }
    }

    @Override
    public List<StudentDTO> getStudentsByCourse(Long courseId) {
        List<Student> students = enrollmentRepo.findStudentsByCourseId(courseId);
        return students.stream()
                .map(StudentTransformer::toDTO)
                .collect(Collectors.toList());
    }

//
//    public List<StudentDTO> getStudentsByCourse(Long courseId) {
//        List<Student> students = enrollmentRepo.findStudentsByCourseId(courseId);
//        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
//    }

//    public List<StudentDTO> getStudentsByCourse(Long courseId) {
//        List<Student> students = enrollmentRepo.findStudentsByCourseId(courseId);
//        return students.stream()
//                .map(student -> new StudentDTO(
//                        student.getStudent_id(),
//                        student.getName(),
//                        student.getEmail(),
//                        student.getDateOfBirth()
//                ))
//                .collect(Collectors.toList());
//    }

//    public List<StudentDTO> getStudentsByCourse(Long courseId) {
//        List<Student> students = enrollmentRepo.findStudentsByCourseId(courseId);
//        return students.stream()
//                .map(studentTransformer::toDTO)
//                .collect(Collectors.toList());
//    }
}
