import { Component, OnInit } from '@angular/core';
import { LoginService } from '@service/login.service';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.sass']
})
export class ErrorComponent implements OnInit {

  constructor(private loginService: LoginService) { }

  ngOnInit() {
  }

  goToLoginPage() {
    this.loginService.goToLoginPage();
  }

}
