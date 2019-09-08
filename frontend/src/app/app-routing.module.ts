import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { MedicalRecordComponent } from './components/medical-record/medical-record.component';
import { ScheduleExamComponent } from './components/schedule-exam/schedule-exam.component';
import { ChooseDoctorComponent } from './components/choose-doctor/choose-doctor.component';
import { PatientHomeComponent } from './components/patient-home/patient-home.component';
import { NurseHomeComponent } from './components/nurse-home/nurse-home.component';
import { UpdateExamComponent } from './components/update-exam/update-exam.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'patient',
    component: PatientHomeComponent,
    children: [
      {
        path: 'notifications',
        component: NotificationsComponent
      },
      {
        path: 'medicalRecord',
        component: MedicalRecordComponent
      },
      {
        path: 'scheduleExam',
        component: ScheduleExamComponent
      },
      {
        path: 'chooseDoctor',
        component: ChooseDoctorComponent
      }
    ]
  },
  {
    path: 'nurse',
    component: NurseHomeComponent,
    children: [
      {
        path: 'updateExams',
        component: UpdateExamComponent
      }
    ]
  },
  {
    path: '',
    component: HomeComponent
  },
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
