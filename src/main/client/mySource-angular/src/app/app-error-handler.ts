import {
  HttpErrorResponse,
  HttpClient,
  HttpHeaders
} from "@angular/common/http";
import { Injectable, NgZone, ErrorHandler, Injector } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { DeviceDetectorService } from "ngx-device-detector";
import { CommonConstant } from "@constant/CommonConstant";
import { ENV } from "environments/environment";
import { LoginService } from '@service/login.service';

@Injectable()
export class CustomErrorHandler implements ErrorHandler {
  private headers = new HttpHeaders({ "Content-Type": "application/json" });
  errorData = {
    deviceType: null,
    deviceInfo: null,
    error: null,
    url: null
  };

  constructor(
    private zone: NgZone,
    private injector: Injector,
    private http: HttpClient,
    private deviceService: DeviceDetectorService
  ) {}

  handleError(error: Error | HttpErrorResponse) {
    // エラー内容をコンソールに出力
    console.error(error);

    // constructorでinject出来ないのでinjectorを使う
    const router = this.injector.get(Router);
    const loginService = this.injector.get(LoginService);

    // ユーザー環境情報をセット
    this.setDeviceInfomation();
    // エラーが発生した画面のURLをセット
    this.errorData.url = router.url;

    // httpステータスが4xx/5xxの場合
    if (error instanceof HttpErrorResponse) {
      // エラー情報を送信オブジェクトにセット
      this.errorData.error = error;

      console.log(this.errorData);
      // 403ならログインページへ遷移する
      if (error.status === 403) {
        router.navigate(["/login"]);
        return; // 以降のプロセスは実行しない
      }
      // その他のエラーの場合
    } else if (error instanceof Error) {
      // エラー情報を送信オブジェクトにセット
      this.errorData.error = error.stack;
    } else {
    }
    console.log(this.errorData);

    // handleError() はボタン操作などの UI イベントとは関係なく呼ばれるため、コンポーネントの変化が検知できず、画面が変化しないことがある。
    // それを防止するため、NgZone#run() 内でサービスを呼び出している。
    this.zone.run(() => {
      // this.message.showError(error.message);
      router.navigate(["/error"]);
    });

    try {
      // エラー情報をサーバーに送信
      this.http
        .post(ENV.HOST + `/errorInfo`, this.errorData, {
          headers: this.headers
        })
        .subscribe(res => {
          console.log(res);
        });
    } catch (e) {
      console.log("sending error failure");
    }

    // ログアウト状態にする
    loginService.removeToken();
  }

  private setDeviceInfomation() {
    this.setDeviceInfo();
    this.setDeviceType();
  }

  private setDeviceInfo() {
    // deviceInfoを送信オブジェクトにセット
    this.errorData.deviceInfo = this.deviceService.getDeviceInfo();
  }

  private setDeviceType() {
    if (this.deviceService.isDesktop()) {
      this.errorData.deviceType = "DeskTop";
    } else if (this.deviceService.isMobile()) {
      this.errorData.deviceType = "Mobile";
    } else if (this.deviceService.isTablet()) {
      this.errorData.deviceType = "Tablet";
    } else {
      this.errorData.deviceType = "Unknown";
    }
  }
}
