import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseStudentViewComponentComponent } from './course-student-view-component.component';

describe('CourseStudentViewComponentComponent', () => {
  let component: CourseStudentViewComponentComponent;
  let fixture: ComponentFixture<CourseStudentViewComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseStudentViewComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourseStudentViewComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
