package com.example.mySource.architecture;

import com.example.mySource.abstractclass.AbstractComponent;

import javax.servlet.*;
import java.io.IOException;

public class CustomFilter2 extends AbstractComponent implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("★★★Filter2 init!!");
    }


    // 実装必須
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("★★★Filter2 前処理!!");
        chain.doFilter(request, response);
        log.debug("★★★Filter2 後処理!!");
    }

    @Override
    public void destroy() {
        log.debug("★★★Filter destroy!!");
    }
}
