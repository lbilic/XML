import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-medical-record',
  templateUrl: './medical-record.component.html',
  styleUrls: ['./medical-record.component.css']
})
export class MedicalRecordComponent implements OnInit {

  private items;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getMedicalRecord();
  }

  private getMedicalRecord(){
    this.userService.getMedicalRecord().subscribe(
      data => {
        this.items = data;
      },
      error => {
        console.log(error);
      }
    )
  }
}
