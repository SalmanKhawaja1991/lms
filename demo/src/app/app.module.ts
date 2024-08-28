import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './pages/home/home.component';
import { AddComponent } from './pages/addStudent/add.component';
import { ViewComponent } from './pages/viewStudent/view.component';
import { UpdateComponent } from './pages/updateStudent/update.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { StudentListComponent } from './components/student-list/student-list.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http'
import { CommonModule } from '@angular/common';
import { ViewInstructorComponent } from './pages/view-instructor/view-instructor.component';
import { ViewCourseComponent } from './pages/view-course/view-course.component';
import { AddCourseComponent } from './pages/add-course/add-course.component';
import { UpdateCourseComponent } from './pages/update-course/update-course.component';
import { AddInstructorComponent } from './pages/add-instructor/add-instructor.component';
import { UpdateInstructorComponent } from './pages/update-instructor/update-instructor.component';
import { EnrollStudentComponent } from './pages/enroll-student/enroll-student.component';
import { AssignInstructorComponent } from './pages/assign-instructor/assign-instructor.component';
import { CourseStudentViewComponentComponent } from './pages/course-student-view-component/course-student-view-component.component';
import { StudentReportComponentComponent } from './pages/student-report-component/student-report-component.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AddComponent,
    ViewComponent,
    UpdateComponent,
    NavbarComponent,
    FooterComponent,
    StudentListComponent,
    ViewInstructorComponent,
    ViewCourseComponent,
    AddCourseComponent,
    UpdateCourseComponent,
    AddInstructorComponent,
    UpdateInstructorComponent,
    EnrollStudentComponent,
    AssignInstructorComponent,
    CourseStudentViewComponentComponent,
    StudentReportComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,HttpClientModule,CommonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
