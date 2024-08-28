import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseDTO } from 'src/app/model/CourseDTO';
import { ApiService } from 'src/app/services/api.service';
import { Router } from '@angular/router';
import { ApiResponseMessage } from 'src/app/model/ApiResponseMessage';

@Component({
  selector: 'app-update-course',
  templateUrl: './update-course.component.html',
  styleUrls: ['./update-course.component.css']
})
export class UpdateCourseComponent implements OnInit {

  course= new CourseDTO();


  constructor(private apiService:ApiService,private activatedRoute:ActivatedRoute, private route: Router){

  }

  ngOnInit(): void {
    const courseId=this.activatedRoute.snapshot.paramMap.get('courseId');
  
    this.apiService.loadCourse(courseId||'').subscribe((data:ApiResponseMessage)=>{
      this.course = <CourseDTO>data.data
      });
  
    }




  updateCourse(event:SubmitEvent){

    this.apiService.updateCourse(this.course.course_id,this.course).subscribe({
      next:(data)=> {
        console.log(data)
        alert('Student Updated Sccessfully')
        this.course= <CourseDTO>data.data
        this.route.navigate(['/view-course']); // Redirect to a route
      },
      error:(error)=> {
        console.log(error)
      },
      complete:()=> {
        console.log("Requst completed")
      }
    })


  }



}
