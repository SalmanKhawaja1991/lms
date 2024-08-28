import { Component, OnInit } from '@angular/core';
import { InstructorDTO } from 'src/app/model/InstructorDTO';
import { ApiService } from 'src/app/services/api.service';
import { ApiResponseMessage } from 'src/app/model/ApiResponseMessage';
import { forkJoin, map } from 'rxjs';

@Component({
  selector: 'app-view-instructor',
  templateUrl: './view-instructor.component.html',
  styleUrls: ['./view-instructor.component.css']
})

export class ViewInstructorComponent implements OnInit{

  instructors:InstructorDTO[]=[]
  constructor(private apiService:ApiService){

  }


  ngOnInit(): void {
    this.loadInstructor();

  }

  
  // loadInstructor(): void {
  //   this.apiService.getAllInstructor().subscribe((data:ApiResponseMessage)=>{
  //     this.instructors = <InstructorDTO[]>data.data
  //     }
  //   );
  // }

  loadInstructor(): void {
    this.apiService.getAllInstructor().subscribe((data: ApiResponseMessage) => {
      this.instructors = <InstructorDTO[]>data.data;
  
      // Create an observable for fetching course counts for all instructors
      const courseCounts$ = this.instructors.map(instructor =>
        this.apiService.getInstructorNumberOfCourses(instructor.instructor_id).pipe(
          map((response: ApiResponseMessage) => ({
            instructorId: instructor.instructor_id,
            count: <string>(<unknown>response.data)
          }))
        )
      );
  
      // Combine all course count observables and update the instructors array
      forkJoin(courseCounts$).subscribe(results => {
        results.forEach(result => {
          const instructor = this.instructors.find(i => i.instructor_id === result.instructorId);
          if (instructor) {
            instructor.numberOfCourses = result.count;
          }
        });
      });
    });
  }




  deleteInstructor(instructorID : string){
    this.apiService.deleteInstructor(instructorID).subscribe({

      next:(data)=> {
        //console.log(data.data)

       this.instructors = this.instructors.filter(instructor => instructor.instructor_id !== instructorID);
        
        alert('Instructor Deleted Sccessfully')
       
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


  // courseCounts: { [instructor_id: string]: string } = {};

  // instructorCourses(instructor_id: string): string {
  //   if (!this.courseCounts[instructor_id]) {
  //     this.apiService.getInstructorNumberOfCourses(instructor_id).subscribe((response: ApiResponseMessage) => {
  //       this.courseCounts[instructor_id] = <string>(<unknown>response.data);
  //     });
  //   }
  //   return this.courseCounts[instructor_id] || '0';
  // }


      courseCounts: { [instructor_id: string]: string } = {};

    instructorCourses(instructor_id: string): string {
      if (this.courseCounts[instructor_id] === undefined) {
        this.apiService.getInstructorNumberOfCourses(instructor_id).subscribe((response: ApiResponseMessage) => {
          this.courseCounts[instructor_id] = <string>(<unknown>response.data);
        });
      }
      return this.courseCounts[instructor_id] || '0';
    }


}
