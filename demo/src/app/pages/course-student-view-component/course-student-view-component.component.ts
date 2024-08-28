import { Component } from '@angular/core';
import { ApiResponseMessage } from 'src/app/model/ApiResponseMessage';
import { CourseDTO } from 'src/app/model/CourseDTO';
import { Student } from 'src/app/model/Student';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-course-student-view-component',
  templateUrl: './course-student-view-component.component.html',
  styleUrls: ['./course-student-view-component.component.css']
})
export class CourseStudentViewComponentComponent {

 
  courses: CourseDTO[] = [];
  students: Student[] = [];
  selectedCourseId: string | null = null;
  selectedStudentId: string | null = null;
  courseStudents: Student[] = [];
  studentCourses: CourseDTO[] = [];

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.loadCourses();
    this.loadStudents();
  }

  loadCourses(): void {
    this.apiService.getAllCourses().subscribe((response: ApiResponseMessage) => {
      this.courses = response.data ? <CourseDTO[]>response.data : [];
    });
  }

  loadStudents(): void {
    this.apiService.getAllStudents().subscribe((response: ApiResponseMessage) => {
      this.students = response.data ? <Student[]>response.data : [];
    });
  }

  onCourseChange(event: Event): void {
    const target = event.target as HTMLSelectElement;
    const courseId = target.value;

    if (courseId) {
      this.selectedCourseId = courseId;
      this.apiService.getStudentsByCourse(courseId).subscribe((response: ApiResponseMessage) => {
        this.courseStudents = response.data ? <Student[]>response.data : [];
      });
    }
  }

  onStudentChange(event: Event): void {
    const target = event.target as HTMLSelectElement;
    const studentId = target.value;

    if (studentId) {
      this.selectedStudentId = studentId;
      this.apiService.getCoursesByStudent(studentId).subscribe((response: ApiResponseMessage) => {
        this.studentCourses = response.data ? <CourseDTO[]>response.data : [];
      });
    }
  }
}
