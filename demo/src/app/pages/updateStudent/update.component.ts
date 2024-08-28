import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Student } from 'src/app/model/Student';
import { ApiService } from 'src/app/services/api.service';
import { Router } from '@angular/router';
import { ApiResponseMessage } from 'src/app/model/ApiResponseMessage';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  student = new Student();

  constructor(private apiService:ApiService,private activatedRoute:ActivatedRoute, private route: Router){

  }
  ngOnInit(): void {
  const studentId=this.activatedRoute.snapshot.paramMap.get('studentId');

  this.apiService.loadStudent(studentId||'').subscribe((data:ApiResponseMessage)=>{
    this.student = <Student>data.data
    });

  }



  updateStudent(event:SubmitEvent){
    this.apiService.updateStudent(this.student.student_id,this.student).subscribe({
      next:(data)=> {
        console.log(data.data)
        alert('Student Updated Sccessfully')
        this.student= <Student>data.data
        this.route.navigate(['/view']); // Redirect to a route
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
