import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
import { CourseDTO } from 'src/app/model/CourseDTO';
import { ApiResponseMessage } from 'src/app/model/ApiResponseMessage';
import { Router, ActivatedRoute } from '@angular/router';
import { AsignCourseDTO } from 'src/app/model/AsignCourseDTO';

@Component({
  selector: 'app-assign-instructor',
  templateUrl: './assign-instructor.component.html',
  styleUrls: ['./assign-instructor.component.css']
})
export class AssignInstructorComponent implements OnInit {

  courses: CourseDTO[] = [];
  //enrollment = new EnrollmentDTO();
  courseAsign= new AsignCourseDTO();


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

    const instructorId = this.activatedRoute.snapshot.paramMap.get('instructorId');

    if (instructorId) {
      this.courseAsign.instructor = { instructor_id: instructorId };
    }

    console.log(this.courseAsign); // Debugging line to check the populated object

    this.apiService.assignCourse(this.courseAsign).subscribe({
      next: (data) => {
        console.log(data);
        alert('Successfully Course  Assign To Teacher');
        this.courseAsign = new AsignCourseDTO();
        this.router.navigate(['/view-instructor']); // Redirect to a route after successful enrollment
      },
      error: (error) => {
        console.log(error);
        alert("This course is Already Asigned To Teacher");
      },
      complete: () => {
        console.log("Request completed");
      }
    });

  }
}
