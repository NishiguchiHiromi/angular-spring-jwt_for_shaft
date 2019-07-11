import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '@service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {


  failure: boolean;

  constructor(
    private builder: FormBuilder,
    private loginService: LoginService
  ) { }

  loginForm: FormGroup = this.builder.group({
    'loginId': ['', Validators.required],
    'password': ['', [Validators.required]],
  });

  ngOnInit() {
  }

  submit() {
    this.loginService.login(this.loginForm.value)
      .subscribe(
        res => this.loginService.loginSuccess(res),
        error => {
          console.log("認証失敗")
          console.log(error)
          this.failure = true;
        })
  }
}
