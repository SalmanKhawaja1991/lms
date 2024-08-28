import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
import { CourseDTO } from 'src/app/model/CourseDTO';
import { ApiResponseMessage } from 'src/app/model/ApiResponseMessage';
import { Router, ActivatedRoute } from '@angular/router';
import { EnrollmentDTO } from 'src/app/model/EnrollmentDTO';

@Component({
  selector: 'app-enroll-student',
  templateUrl: './enroll-student.component.html',
  styleUrls: ['./enroll-student.component.css']
})
export class EnrollStudentComponent implements OnInit {

  courses: CourseDTO[] = [];
  enrollment = new EnrollmentDTO();

  constructor(
    private apiService: ApiService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCourses();
  }

  loadCourses(): void {
    this.apiService.getAllCourses().subscribe((data: ApiResponseMessage) => {
      this.courses = <CourseDTO[]>data.data;
    });
  }

  submitForm(event: Event): void {
    event.preventDefault();

    const studentId = this.activatedRoute.snapshot.paramMap.get('studentId');

    if (studentId) {
      this.enrollment.student = { student_id: studentId };
    }

    console.log(this.enrollment); // Debugging line to check the populated object

    this.apiService.enrollStudent(this.enrollment).subscribe({
      next: (data) => {
        console.log(data);
        alert('Student Enrolled Successfully');
        this.enrollment = new EnrollmentDTO();
        this.router.navigate(['/view']); // Redirect to a route after successful enrollment
      },
      error: (error) => {
        console.log(error);
        alert("Student already Enrolled");
      },
      complete: () => {
        console.log("Request completed");
      }
    });
  }
}
