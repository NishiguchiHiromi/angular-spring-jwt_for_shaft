import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { CommonConstant } from "@constant/CommonConstant";
import { ENV } from "environments/environment";

@Injectable({
  providedIn: "root"
})
export class LoginService {
  private sotoredUrl: string;
  private SUCCESS_URL: string = "/loggedInField";
  private LOGIN_PAGE_URL: string = "/login";
  private AUTHENTICATE_URL: string = "/authenticate";

  constructor(private router: Router, private http: HttpClient) {}

  login(data: { loginId: string; password: string }) {
    return this.http.post(ENV.HOST + this.AUTHENTICATE_URL, data, { observe: "response" })
  }

  loginSuccess(res) {
    this.storeToken(res);
    let url;
    if (this.sotoredUrl) {
      url = this.sotoredUrl;
      this.sotoredUrl = null;
    } else {
      url = this.SUCCESS_URL;
    }
    this.router.navigate([url]);
  }

  logout() {
    this.removeToken();
    this.goToLoginPage();
  }

  removeToken() {
    localStorage.removeItem(CommonConstant.LocalStorage.JWT);
  }

  storeToken<T>(res: HttpResponse<T>) {
    const token = res.headers.get("Authorization");
    localStorage.setItem(CommonConstant.LocalStorage.JWT, token);
  }

  storeUrl() {
    // console.log(this.route.snapshot, this.route.toString(), this.route.url);
    // console.log(this.router.url);
    console.log(location.hash);
    const hash = location.hash.substring(1);
    if (hash != this.LOGIN_PAGE_URL) {
      console.log(hash);
      this.sotoredUrl = hash;
    }
  }

  goToLoginPage() {
    this.router.navigate([this.LOGIN_PAGE_URL]);
  }

  get isLoggedIn(): boolean {
    return !this.isLoggedOut;
  }

  get isLoggedOut(): boolean {
    if (!this.token) {
      return true;
    } else {
      const payLoad: { sub: string; exp: number } = JSON.parse(
        window.atob(this.token.split(".")[1])
      );
      const expireSec = payLoad.exp;
      console.log(new Date(expireSec * 1000));
      console.log("expire:" + (expireSec < new Date().getTime() / 1000));
      return expireSec < new Date().getTime() / 1000;
    }
  }

  get token(): string {
    const token = localStorage.getItem(CommonConstant.LocalStorage.JWT);
    return token ? token : "";
  }

  // CommonConstant;.JWT_PREFIX;
}
