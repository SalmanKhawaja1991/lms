import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Student } from '../model/Student';
import { Observable } from 'rxjs';
import { ApiResponseMessage } from 'src/app/model/ApiResponseMessage';
import { InstructorDTO } from '../model/InstructorDTO';
import { CourseDTO } from '../model/CourseDTO';
import { EnrollmentDTO } from '../model/EnrollmentDTO';
import { AsignCourseDTO } from 'src/app/model/AsignCourseDTO';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

// Example of defining baseurl in your service
private baseurl = 'http://localhost:8080'; // Replace with your actual backend URL

  constructor(private httpService:HttpClient) { 

  }
  //Student Services
   getAllStudents(): Observable<ApiResponseMessage>{
    return this.httpService.get<ApiResponseMessage>(`${this.baseurl}/students`);
  }
  loadStudent(studentId :string) : Observable<ApiResponseMessage>{
    return this.httpService.get<ApiResponseMessage>(`${this.baseurl}/students/${studentId}`);
   }
  addStudent(student: Student): Observable<ApiResponseMessage>{
    return this.httpService.post<ApiResponseMessage>(`${this.baseurl}/students`, student);
  }
  updateStudent(studentId :string,student: Student): Observable<ApiResponseMessage>{
    return this.httpService.put<ApiResponseMessage>(`${this.baseurl}/students/${studentId}`,student);
  }  
  deleteStudent(studentId : string): Observable<ApiResponseMessage>{
    return this.httpService.delete<ApiResponseMessage>(`${this.baseurl}/students/${studentId}`);
  }


// Instructor Services
  getAllInstructor(): Observable<ApiResponseMessage>{
     return this.httpService.get<ApiResponseMessage>(`${this.baseurl}/instructors`);
  }
  loadInstructor(instructorId :string) : Observable<ApiResponseMessage>{
    return this.httpService.get<ApiResponseMessage>(`${this.baseurl}/instructors/${instructorId}`);
   }
  addInstructor(instructor: InstructorDTO): Observable<ApiResponseMessage>{
    return this.httpService.post<ApiResponseMessage>(`${this.baseurl}/instructors`, instructor);
  }
  updateInstructor(instructorId :string,instructor: InstructorDTO): Observable<ApiResponseMessage>{
    return this.httpService.put<ApiResponseMessage>(`${this.baseurl}/instructors/${instructorId}`,instructor);
  }  
  deleteInstructor(instructorId : string): Observable<ApiResponseMessage>{
    return this.httpService.delete<ApiResponseMessage>(`${this.baseurl}/instructors/${instructorId}`);
  }


 
// Course Services  
  getAllCourses(): Observable<ApiResponseMessage>{
     return this.httpService.get<ApiResponseMessage>(`${this.baseurl}/courses`);
  }
  loadCourse(courseId :string) : Observable<ApiResponseMessage>{
    return this.httpService.get<ApiResponseMessage>(`${this.baseurl}/courses/${courseId}`);
   }
  addCourse(course: CourseDTO): Observable<ApiResponseMessage>{
    return this.httpService.post<ApiResponseMessage>(`${this.baseurl}/courses`, course);
  }
  updateCourse(courseId :string,course: CourseDTO): Observable<ApiResponseMessage>{
    return this.httpService.put<ApiResponseMessage>(`${this.baseurl}/courses/${courseId}`,course);
  }  
  deleteCourse(courseId : string): Observable<ApiResponseMessage>{
    return this.httpService.delete<ApiResponseMessage>(`${this.baseurl}/courses/${courseId}`);
  }


  // enrollment 
  enrollStudent(enrollment: EnrollmentDTO): Observable<ApiResponseMessage>{
    return this.httpService.post<ApiResponseMessage>(`${this.baseurl}/enrollments`, enrollment);
  }

    // asign course 
    assignCourse(assign: AsignCourseDTO): Observable<ApiResponseMessage>{
      return this.httpService.post<ApiResponseMessage>(`${this.baseurl}/course-instructors`, assign);
    }



    // Course Services  
  getstudentNumberOfCourses(studentId : string): Observable<ApiResponseMessage>{
    return this.httpService.get<ApiResponseMessage>(`${this.baseurl}/students/${studentId}/total-courses`);
 }


     // Course Services  
     getInstructorNumberOfCourses(InstructorId : string): Observable<ApiResponseMessage>{
      return this.httpService.get<ApiResponseMessage>(`${this.baseurl}/instructors/${InstructorId}/course-count`);
   }


// just add these Two Services
   getStudentsByCourse(courseId: string): Observable<ApiResponseMessage> {
    return this.httpService.get<ApiResponseMessage>(`${this.baseurl}/students/course/${courseId}`);
  }

  getCoursesByStudent(studentId: string): Observable<ApiResponseMessage> {
    return this.httpService.get<ApiResponseMessage>(`${this.baseurl}/students/${studentId}/courses`);
  }

//   // In ApiService
// getCoursesByStudent(studentId: string): Observable<ApiResponseMessage> {
//   const url = `https://api.example.com/courses?studentId=${studentId}`;
//   return this.http.get<ApiResponseMessage>(url);
// }

}
