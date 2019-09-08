import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { DoctorService } from 'src/app/services/doctor.service';
import { ReturnStatement } from '@angular/compiler';

@Component({
  selector: 'app-choose-doctor',
  templateUrl: './choose-doctor.component.html',
  styleUrls: ['./choose-doctor.component.css']
})
export class ChooseDoctorComponent implements OnInit {

  private doctors;
  private chosenDoctor = null;
  constructor(private userService: UserService, private doctorService: DoctorService) { }

  ngOnInit() {
    this.doctorService.getDoctors().subscribe(
      data => this.doctors = data
    );
  }

  onSubmit(){
    if (this.chosenDoctor === null)
      return;
    this.userService.chooseDoctor(this.chosenDoctor).subscribe(
      data => {
        console.log(data)
      },
      error => {
        console.log(error)
      } 
    );
  }
}
