import { BrowserModule } from "@angular/platform-browser";
import { NgModule, ErrorHandler } from "@angular/core";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { DeviceDetectorModule } from "ngx-device-detector";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { CustomErrorHandler } from "./app-error-handler";
import { LoginComponent } from "./component/login/login.component";
import { HttpClientModule } from "@angular/common/http";
import { LoggedInFieldComponent } from "./component/logged-in-field/logged-in-field.component";
import { HogeComponent } from "./component/hoge/hoge.component";
import { ErrorComponent } from './component/error/error.component';
import { HeaderComponent } from './component/header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LoggedInFieldComponent,
    HogeComponent,
    ErrorComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DeviceDetectorModule.forRoot(),
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    { provide: ErrorHandler, useClass: CustomErrorHandler } // 自作エラーハンドラを設定
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
