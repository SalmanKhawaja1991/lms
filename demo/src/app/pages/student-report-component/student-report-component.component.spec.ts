import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentReportComponentComponent } from './student-report-component.component';

describe('StudentReportComponentComponent', () => {
  let component: StudentReportComponentComponent;
  let fixture: ComponentFixture<StudentReportComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentReportComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudentReportComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
