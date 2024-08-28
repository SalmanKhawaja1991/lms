import { Component } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
import { Router } from '@angular/router';
import { CourseDTO } from 'src/app/model/CourseDTO';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent {

  course= new CourseDTO();

  constructor(private apiService:ApiService,private router: Router){

  }


  submitForm(event:SubmitEvent){
    console.log("Recieved");
  event.preventDefault();
  console.log(event);
  console.log(this.course);
  
  this.apiService.addCourse(this.course).subscribe({
    next:(data)=> {
      console.log(data)
      alert('Student Added Sccessfully')
      this.course= new CourseDTO();
      this.router.navigate(['/view-course']); // Redirect to a route
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
