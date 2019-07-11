import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from "@component/login/login.component";
import { LoggedInFieldComponent } from "@component/logged-in-field/logged-in-field.component";
import { LoginGuard } from "@guard/login.guard";
import { HogeComponent } from "@component/hoge/hoge.component";
import { ErrorComponent } from '@component/error/error.component';

const routes: Routes = [
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "loggedInField",
    component: LoggedInFieldComponent,
    canActivate: [LoginGuard],
    // canActivateChild: [LoginGuard],
    children: [
      { path: "hoge", component: HogeComponent },
    ]
  },
  {
    path: "error",
    component: ErrorComponent
  },
  {
    path: "",
    redirectTo: "loggedInField",
    pathMatch: "full"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })], // リロードを可能にするためhash形式のurlを使う
  exports: [RouterModule]
})
export class AppRoutingModule {}
