import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { InstructorDTO } from 'src/app/model/InstructorDTO';
import { ApiService } from 'src/app/services/api.service';
import { Router } from '@angular/router';
import { ApiResponseMessage } from 'src/app/model/ApiResponseMessage';


@Component({
  selector: 'app-update-instructor',
  templateUrl: './update-instructor.component.html',
  styleUrls: ['./update-instructor.component.css']
})
export class UpdateInstructorComponent implements OnInit {


  instructor= new InstructorDTO();

  constructor(private apiService:ApiService,private activatedRoute:ActivatedRoute, private route: Router){

  }


  ngOnInit(): void {
    const instructorId=this.activatedRoute.snapshot.paramMap.get('instructorId');
  
    this.apiService.loadInstructor(instructorId||'').subscribe((data:ApiResponseMessage)=>{
      this.instructor = <InstructorDTO>data.data
      });
  
    }


    updateInstructor(event:SubmitEvent){

      this.apiService.updateInstructor(this.instructor.instructor_id,this.instructor).subscribe({
        next:(data)=> {
          console.log(data)
          alert('Instructor Updated Sccessfully')
          this.instructor= <InstructorDTO>data.data
          this.route.navigate(['/view-instructor']); // Redirect to a route
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
