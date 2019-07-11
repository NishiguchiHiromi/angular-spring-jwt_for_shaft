package com.example.mySource.architecture;

import com.example.mySource.abstractclass.AbstractComponent;
import com.example.mySource.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


//

@WebListener
public class CustomServletRequestListner extends AbstractComponent implements ServletRequestListener {

    // ServletRequestListenerのdefaultMethodは何も処理をしていない

    // ServletRequestListenerをimplementsしているクラスとしてRequestContextListenerがある。
    // これはユーザーのブラウザ言語設定を取得できるので必要に応じて利用可能
    // https://qiita.com/tsuka816/items/e5091a59cf4ca1dbf803



    // サービスなどもAutowiredできる
    @Autowired
    private SampleService sampleService;


    @Override public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        log.debug("★★★Request initialized（リクエスト受付）");
        log.debug(LocalDateTime.now().toString());
        sampleService.log(" Request initialized ");
        log.debug(req.getRequestURI());
    }

    @Override public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        log.debug("★★★Request destroyed");
        log.debug(LocalDateTime.now().toString());
        sampleService.log(" Request destroyed（レスポンス返却）");
        log.debug(req.getRequestURI());
    }
}
