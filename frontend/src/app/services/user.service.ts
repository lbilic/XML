import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

import { map } from 'rxjs/operators';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  private API_URL = 'localhost:9003';

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User{
    return this.currentUserSubject.value;
  }

  public register(name: string){
    return this.http.post<any>('http://localhost:9003/user/register', {name});
  }


  public login(username: string, password: string){
    console.log("Login" + username + password);
    return this.http.post<any>('http://localhost:9003/user/login', {username, password})
      .pipe(map(user => {
        if(user && user.token){
          localStorage.setItem('currentUser', JSON.stringify(user))
          this.currentUserSubject.next(user);
        }

        return user;
      }));
  }

  public logout(){
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  public chooseDoctor(doctor){
    return this.http.post<any>("http://localhost:9003/user/chooseDoctor", doctor);
  }

  public getMedicalRecord(){
    return this.http.get<any>("http://localhost:9003/user/medicalRecord");
  }

  public getNotifications(){
    return this.http.get<any>("http://localhost:9003/user/notifications");
  }
}
