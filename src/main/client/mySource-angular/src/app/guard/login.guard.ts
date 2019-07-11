import { Injectable } from "@angular/core";
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  CanActivateChild
} from "@angular/router";
import { Observable } from "rxjs";
import { LoginService } from "@service/login.service";

@Injectable({
  providedIn: "root"
})
export class LoginGuard implements CanActivate, CanActivateChild {
  constructor(private loginService: LoginService) {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {
    const isOk: boolean = this.loginService.isLoggedIn;
    if (!isOk) {
      this.loginService.storeUrl();
      this.loginService.goToLoginPage();
    }
    return isOk;
    // return true;
  }

  canActivateChild(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {
    // return this.loginService.checkLoggedIn();
    return true;
  }
}
