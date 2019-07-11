import { Component, OnInit } from '@angular/core';
import { tap } from "rxjs/operators";
import { AjaxService } from '@service/ajax.service';
import { LoginService } from '@service/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass']
})
export class HeaderComponent implements OnInit {

  constructor(
    private ajax: AjaxService,
    private loginService: LoginService,) { }

  ngOnInit() {
  }

  userName = this.ajax.authGet("/userInfo").pipe(tap(res => console.log(res)));

  logout() {
    this.loginService.logout();
  }

}
