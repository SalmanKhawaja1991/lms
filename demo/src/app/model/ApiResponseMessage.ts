import { Student } from 'src/app/model/Student';

export class  ApiResponseMessage {
//   message: string;
//   code: string;
//   data: Student[];
   // add other fields that your DTO has

   message : string = '';
    //HttpStatus status;
    code: string ='';
   // boolean success;
   //data: Student[];
   data: object | undefined;
   //private Object data;

}