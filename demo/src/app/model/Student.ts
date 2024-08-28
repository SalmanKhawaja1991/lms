
export class Student {
    constructor(
        public student_id: string = '',
        public name: string = '',
        public email: string = '',
        public dateOfBirth: string = '',
        public profile : any = null,

        public numberOfCourses: string ='', // Add this property
        
        //public profile: string = '',
        // public profile: ProfileDTO 
    ) {}
}
