import { Component } from '@angular/core';
import { InstructorDTO } from 'src/app/model/InstructorDTO';
import { ApiService } from 'src/app/services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-instructor',
  templateUrl: './add-instructor.component.html',
  styleUrls: ['./add-instructor.component.css']
})
export class AddInstructorComponent {

instructor= new InstructorDTO();


constructor(private apiService:ApiService,private router: Router){

}


  submitForm(event:SubmitEvent){
    console.log("Recieved");
  event.preventDefault();
  console.log(event);
 console.log(this.instructor);
  
  this.apiService.addInstructor(this.instructor).subscribe({
    next:(data)=> {
      console.log(data)
      alert('Instructor Added Sccessfully')
      this.instructor= new InstructorDTO();
      this.router.navigate(['/view-instructor']); // Redirect to a route
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
