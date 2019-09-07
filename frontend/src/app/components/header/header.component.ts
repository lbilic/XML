import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  userLogged: boolean = false;

  constructor(private userService: UserService) { }

  ngOnInit() {

    this.userService.currentUser
      .subscribe(
        value => {value === null ? this.userLogged = false : this.userLogged = true}
      );
  }

}
