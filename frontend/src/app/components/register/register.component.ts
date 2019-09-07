import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';

  constructor(    
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService) { }

  ngOnInit() {
    
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
    });
  }

  get f(){ return this.registerForm.controls; }

  onSubmit(){
    console.log('registring..' + this.f.name.value);
    this.submitted = true;

    if(this.registerForm.invalid){
      return;
    }

    this.loading = true;

    this.userService.register(this.f.name.value)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data);
          this.loading = false;
        },
        error => {
          this.error = error.message;
          console.log(error);
          this.loading = false;
        }
      )
  }
}
