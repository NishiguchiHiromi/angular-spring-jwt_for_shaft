package com.example.mySource.architecture;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.example.mySource.abstractclass.AbstractComponent;


// Filterの設定方法
// https://qiita.com/suke_masa/items/7bdfab8e974931afdac5


// 単一filterの場合のみ@WebFilterと@ServletComponentScanでFilterを使用できるようになる。
// ただし、Filterを複数使用したい場合、@WebFilterだと順番を決められない
// @WebFilter
public class CustomFilter extends AbstractComponent implements Filter {

    // configからbeanを登録するとAutowiredが出来なくなる。@WebFilterを使うパターンだとOK
//    @Autowired
//    private SampleService sampleService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {// defaultMethodではなにもしていない
        // 初期化処理を行う。このメソッドはアプリケーション起動時に呼び出される。
        // サーブレットフィルタの初期化パラメータは引数のFilterConfigから取得できる。
        log.debug("★★★Filter init!!");
    }


    // 実装必須
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // ここに前処理を実装する
        log.debug("★★★Filter 前処理!!");
//        sampleService.log("★★★Filter 前処理!!");


        // 後続処理(次のFilter又はServlet)を呼び出したくない場合は、このタイミングでメソッドを終了(return)すればよい。
//        return;

        // 後続処理(次のFilter又はServlet)を呼び出す
        chain.doFilter(request, response);

        // ここに後処理を実装する
        log.debug("★★★Filter 後処理!!");
    }

    @Override
    public void destroy() {// defaultMethodではなにもしていない
        // アプリケーション終了時に行う処理を実装する
        log.debug("★★★Filter destroy!!");
    }
}
