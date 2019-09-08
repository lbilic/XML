import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Accept': 'text/html, application/xhtml+xml, */*',
      'Content-Type': 'application/x-www-form-urlencoded'
    }),
    responseType: 'text' as 'json'
  };
  
  constructor(private http: HttpClient) { }

  public getDoctors(){
    return this.http.get<string>("http://localhost:8080/doctors", this.httpOptions);
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
