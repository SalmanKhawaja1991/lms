import { Component, Input } from '@angular/core';
import { Student } from 'src/app/model/Student';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})

export class StudentListComponent {

  @Input() student:Student=new Student

}
