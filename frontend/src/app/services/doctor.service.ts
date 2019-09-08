import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private http: HttpClient) { }

  public getDoctors(){
    return this.http.get<any>("http://localhost:9003/doctor/all");
  }

  public getExams(){
    return this.http.get<any>("http://localhost:9003/doctor/exams");
  }

  public getExamsByDoctor(doctor){
    return this.http.get<any>("http://localhost:9003/doctor/exams/"+doctor);
  }

  public confirmExam(id){
    return this.http.post<any>("http://localhost:9003/exam/confirm", id);
  }
}
