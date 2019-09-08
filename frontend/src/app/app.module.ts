import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './components/header/header.component';
import { ChooseDoctorComponent } from './components/choose-doctor/choose-doctor.component';
import { ScheduleExamComponent } from './components/schedule-exam/schedule-exam.component';
import { MedicalRecordComponent } from './components/medical-record/medical-record.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { NurseHomeComponent } from './components/nurse-home/nurse-home.component';
import { DoctorHomeComponent } from './components/doctor-home/doctor-home.component';
import { PatientHomeComponent } from './components/patient-home/patient-home.component';
import { UpdateExamComponent } from './components/update-exam/update-exam.component';

@NgModule({
  exports:[
    HeaderComponent
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    HeaderComponent,
    ChooseDoctorComponent,
    ScheduleExamComponent,
    MedicalRecordComponent,
    NotificationsComponent,
    NurseHomeComponent,
    DoctorHomeComponent,
    PatientHomeComponent,
    UpdateExamComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
