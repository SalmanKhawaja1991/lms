import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
import { CourseDTO } from 'src/app/model/CourseDTO';
import { ApiResponseMessage } from 'src/app/model/ApiResponseMessage';

@Component({
  selector: 'app-view-course',
  templateUrl: './view-course.component.html',
  styleUrls: ['./view-course.component.css']
})
export class ViewCourseComponent implements OnInit{


  courses:CourseDTO[]=[]

  constructor(private apiService:ApiService){

  }




  ngOnInit(): void {
   // throw new Error('Method not implemented.');
   this.loadCourses();
  }


  
  loadCourses(): void {
    this.apiService.getAllCourses().subscribe((data:ApiResponseMessage)=>{
      this.courses = <CourseDTO[]>data.data
      }
    );
  }


  


  deleteCourse(courseID : string){
    this.apiService.deleteCourse(courseID).subscribe({

      next:(data)=> {
       // console.log(data.data)

       this.courses = this.courses.filter(course => course.course_id !== courseID);
        
        alert('Course Deleted Sccessfully')
       
        //window.location.reload(); 

       
      },
      error:(error)=> {
        console.log(error)
        alert('Cannot delete course with enrolled students')
      },
      complete:()=> {
        console.log("Deleted completed")
      }

    })

  }

}
