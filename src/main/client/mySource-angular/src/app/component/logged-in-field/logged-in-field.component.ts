import { Component, OnInit } from "@angular/core";
import { AjaxService } from "@service/ajax.service";
import { Observable, of } from "rxjs";
import { Router, ActivatedRoute } from "@angular/router";
import { ENV } from 'environments/environment';


@Component({
  selector: "app-logged-in-field",
  templateUrl: "./logged-in-field.component.html",
  styleUrls: ["./logged-in-field.component.sass"]
})
export class LoggedInFieldComponent implements OnInit {
  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute
  ) {}


  number: number;
  answer: Observable<number>;
  ngOnInit() {}

  square() {
    this.answer = this.ajax.authPost("/square", this.number);
  }

  throw() {
    throw new Error("くらいあんとえらーだよ");
  }

  errorAjax() {
    this.ajax.authGet("/serverError").subscribe(res => console.warn(res));
  }

  gotoThymeleafPage() {
    location.href = `${ENV.HOST}/outsideLogin/serverThrow`
  }

  goto(url: string) {
    const link = [url];
    this.router.navigate(link, { relativeTo: this.route });
  }
}
