import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {

  private notifications;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getNotifications().subscribe(
      data => {
        this.notifications = data;
      },
      error => {
        console.log(error);
      }
    )
  }

}
