import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AddComponent } from './pages/addStudent/add.component';
import { ViewComponent } from './pages/viewStudent/view.component';
import { UpdateComponent } from './pages/updateStudent/update.component';
import { ViewInstructorComponent } from './pages/view-instructor/view-instructor.component';
import { ViewCourseComponent } from './pages/view-course/view-course.component';
import { AddCourseComponent } from './pages/add-course/add-course.component';
import { UpdateCourseComponent } from './pages/update-course/update-course.component';
import { AddInstructorComponent } from './pages/add-instructor/add-instructor.component';
import { UpdateInstructorComponent } from './pages/update-instructor/update-instructor.component';
import { EnrollStudentComponent } from './pages/enroll-student/enroll-student.component';
import { AssignInstructorComponent } from './pages/assign-instructor/assign-instructor.component';
import { CourseStudentViewComponentComponent } from './pages/course-student-view-component/course-student-view-component.component';
const routes: Routes = [
  {
    path:'',
    redirectTo:'home',
    pathMatch:'full',
     title:'Home page'
  },
{
  path:'home',
  component:HomeComponent,
  title:'Home page'
},
{
  path:'add',
  component:AddComponent,
   title:'Add page'
},
{
  path:'view',
  component:ViewComponent,
   title:'View page'
},
{
  path:'update/:studentId',
  component:UpdateComponent,
   title:'Update page'
},
{
  path:'view-instructor',
  component:ViewInstructorComponent,
   title:'Instructor page'
},
{
  path:'view-course',
  component:ViewCourseComponent,
   title:'Course page'
},
{
  path:'add-course',
  component:AddCourseComponent,
   title:'Add Course'
},
{
  path:'update-course/:courseId',
  component:UpdateCourseComponent,
   title:'Update Course'
},
{
  path:'add-instructor',
  component:AddInstructorComponent,
   title:'Add Instructor'
},
{
  path:'update-instructor/:instructorId',
  component:UpdateInstructorComponent,
   title:'Update Instructor'
},
{
  path:'enroll-student/:studentId',
  component:EnrollStudentComponent,
   title:'Enroll student'
},
{
  path:'assign-instructor/:instructorId',
  component:AssignInstructorComponent,
   title:'Assign Instructor'
},
{
  path:'course-student-view-component',
  component:CourseStudentViewComponentComponent,
   title:'Course Student View'
}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
