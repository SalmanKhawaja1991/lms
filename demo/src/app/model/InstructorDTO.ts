
export class InstructorDTO {
    constructor(
        public instructor_id: string = '',
        public name: string = '',
        public expertise: string = '',
        
        public numberOfCourses: string ='', // Add this property
      
    ) {}
}