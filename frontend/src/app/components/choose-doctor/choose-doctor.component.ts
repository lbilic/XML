import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { DoctorService } from 'src/app/services/doctor.service';
import { ReturnStatement } from '@angular/compiler';
import { NgxXml2jsonService } from 'ngx-xml2json';

@Component({
  selector: 'app-choose-doctor',
  templateUrl: './choose-doctor.component.html',
  styleUrls: ['./choose-doctor.component.css']
})
export class ChooseDoctorComponent implements OnInit {

  private doctors = null;
  private chosenDoctor = null;
  constructor(private userService: UserService, private doctorService: DoctorService,
    private ngxXml2jsonService: NgxXml2jsonService) { }

  ngOnInit() {
    var parser = require('fast-xml-parser');


    this.doctorService.getDoctors().subscribe(
      data => {
        var doctors = parser.parse(data, [,{}]);
        this.doctors = doctors.doctors['lkr:doctor'];
      }
    );
  }

  onSubmit(){
    if (this.chosenDoctor === null)
      return;

    var Parser = require('fast-xml-parser').j2xParser;
    var parser = new Parser();

    let chosenDoctor = parser.parse({action: {function: 'edit',context: 'edit',content: this.chosenDoctor}});

    this.userService.chooseDoctor(chosenDoctor).subscribe(
      data => {
        console.log(data)
      },
      error => {
        console.log(error)
      } 
    );
  }

}
