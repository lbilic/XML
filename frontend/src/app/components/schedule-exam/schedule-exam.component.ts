import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { DoctorService } from 'src/app/services/doctor.service';

@Component({
  selector: 'app-schedule-exam',
  templateUrl: './schedule-exam.component.html',
  styleUrls: ['./schedule-exam.component.css']
})
export class ScheduleExamComponent implements OnInit {

  private exams;
  private scheduledExam = null;

  constructor(private userService: UserService, private doctorService: DoctorService) { }

  ngOnInit() {
    this.doctorService.getExams().subscribe(
      data => this.exams = data
    );
  }

  onSubmit(){
    
    if (this.scheduledExam === null)
      return;
    this.userService.chooseDoctor(this.scheduledExam).subscribe(
      data => {
        console.log(data)
      },
      error => {
        console.log(error)
      } 
    );
  }
}
