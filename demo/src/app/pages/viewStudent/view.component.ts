import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/model/Student';
import { ApiResponseMessage } from 'src/app/model/ApiResponseMessage';
import { ApiService } from 'src/app/services/api.service';
import { forkJoin, map, timer } from 'rxjs';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})

export class ViewComponent implements OnInit{

  students: Student[]=[];
   apiResponse= new ApiResponseMessage();
  numberOCourses :string ='';

  constructor(private apiService:ApiService){

  }

  ngOnInit(): void {
    this.loadStudents();
   }



   loadStudents(): void {
    this.apiService.getAllStudents().subscribe((data: ApiResponseMessage) => {
      this.students = <Student[]>data.data;
  
      // Create an observable for fetching course counts for all students
      const courseCounts$ = this.students.map(student =>
        this.apiService.getstudentNumberOfCourses(student.student_id).pipe(
          map((response: ApiResponseMessage) => ({
            studentId: student.student_id,
            count: <string>(<unknown>response.data)
          }))
        )
      );
  
      // Combine all course count observables and update the students array
      forkJoin(courseCounts$).subscribe(results => {
        results.forEach(result => {
          const student = this.students.find(s => s.student_id === result.studentId);
          if (student) {
            student.numberOfCourses = result.count;
          }
        });
      });
    });
  }

  // loadStudents(): void {

  //   this.apiService.getAllStudents().subscribe((data:ApiResponseMessage)=>{
  //     this.students = <Student[]>data.data;


  //   //   this.students.forEach( (value: any) => {
  //   //    // console.log(value);
   
      
  //   // });
    
           
  //     }
  //   );


  // }


  // courseCounts: { [studentId: string]: string } = {};

  // studentCourses(student_id: string): string {
  //   if (!this.courseCounts[student_id]) {
  //     this.apiService.getstudentNumberOfCourses(student_id).subscribe((response: ApiResponseMessage) => {
  //       this.courseCounts[student_id] = <string>(<unknown>response.data);
  //     });
  //   }
  //   return this.courseCounts[student_id] || '0';
  // }
  
  courseCounts: { [studentId: string]: string } = {};

studentCourses(student_id: string): string {
  if (this.courseCounts[student_id] === undefined) {
    this.apiService.getstudentNumberOfCourses(student_id).subscribe((response: ApiResponseMessage) => {
      this.courseCounts[student_id] = <string>(<unknown>response.data);
    });
  }
  return this.courseCounts[student_id] || 'Loading...';
}



  deleteStudent(studentID : string){
    this.apiService.deleteStudent(studentID).subscribe({

      next:(data)=> {
        //console.log(data.data)

       this.students = this.students.filter(student => student.student_id !== studentID);
        
        alert('Student Deleted Sccessfully')
       
        //window.location.reload(); 

       
      },
      error:(error)=> {
        console.log(error)
      },
      complete:()=> {
        console.log("Deleted completed")
      }

    })

  }

  detailsOfStudent(studentID : string){}


}
