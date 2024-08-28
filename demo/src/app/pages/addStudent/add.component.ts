import { Component } from '@angular/core';
//import { Student } from 'src/app/model/Student';
import { Student } from 'src/app/model/Student';
import { ApiService } from 'src/app/services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent {
  
student = new Student();


constructor(private apiService:ApiService,private router: Router){

}
// submitForm(event:FormDataEvent){

submitForm(event:SubmitEvent){
  console.log("Recieved");
event.preventDefault();
console.log(event);
console.log(this.student);

this.apiService.addStudent(this.student).subscribe({
  next:(data)=> {
    console.log(data)
    alert('Student Added Sccessfully')
    this.student= new Student();
    this.router.navigate(['/view']); // Redirect to a route
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
