import { Component, OnInit } from '@angular/core';
import { DoctorService } from 'src/app/services/doctor.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-update-exam',
  templateUrl: './update-exam.component.html',
  styleUrls: ['./update-exam.component.css']
})
export class UpdateExamComponent implements OnInit {

  private chosenDoctor = null;
  private doctors;
  private exams;

  constructor(private doctorService: DoctorService, private userService: UserService) { }

  ngOnInit() {
    this.doctorService.getDoctors().subscribe(
      data => this.doctors = data
    );
  }

  onChange(){
    console.log(this.chosenDoctor);
    this.doctorService.getExamsByDoctor(this.chosenDoctor).subscribe(
      data => {
        this.exams = data;
      },
      error => {
        console.log(error);
      }
    )
  }

  onConfirm(id){
    this.doctorService.confirmExam(id).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log(error);
      }
    )
  }
}
