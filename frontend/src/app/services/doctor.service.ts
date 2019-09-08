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
}
