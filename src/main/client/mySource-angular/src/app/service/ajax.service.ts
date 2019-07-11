import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable, empty } from "rxjs";
import { tap, map } from "rxjs/operators";
import { LoginService } from "@service/login.service";
import { ENV } from "environments/environment";

@Injectable({
  providedIn: "root"
})
export class AjaxService {
  constructor(private http: HttpClient, private loginService: LoginService) {}

  get defaultHeader(): HttpHeaders {
    const header = this.authorizationHeader.set(
      "Is-Ajax-Request",
      "true"
    );
    return header;
  }
  get headerForJson(): HttpHeaders {
    const header = this.defaultHeader.set(
      "Content-Type",
      "application/json"
    );
    return header;
  }

  get authorizationHeader(): HttpHeaders {
    return new HttpHeaders({ Authorization: this.loginService.token });
  }

  authGet<T>(url: string): Observable<T> {
    if (this.loginService.isLoggedIn) {
      return this.get<T>(url);
    } else {
      this.loginService.goToLoginPage();
      return empty();
    }
  }

  authPost<T>(url: string, body: any): Observable<T> {
    if (this.loginService.isLoggedIn) {
      return this.post<T>(url, body);
    } else {
      this.loginService.goToLoginPage();
      return empty();
    }
  }

  get<T>(url: string): Observable<T> {
    return this.http
      .get<T>(ENV.HOST + url, {
        headers: this.defaultHeader,
        observe: "response"
      })
      .pipe(
        tap(res => this.loginService.storeToken(res)),
        map(res => res.body)
      );
  }

  post<T>(url: string, body: any): Observable<T> {
    return this.http
      .post<T>(ENV.HOST + url, body, {
        headers: this.headerForJson,
        observe: "response"
      })
      .pipe(
        tap(res => this.loginService.storeToken(res)),
        map(res => res.body)
      );
  }
}
