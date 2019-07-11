package com.example.mySource.architecture;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.example.mySource.abstractclass.AbstractComponent;

@ControllerAdvice
public class CustomControllerAdvice extends AbstractComponent {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // WebDataBinderのメソッドを呼び出してカスタマイズする
        log.debug("★★★Init Binder");
        // Stringのものは、コントローラにたどり着く前にすべてトリムする
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

// jspやthymeleafなどを使用時に全画面共通で使用したい情報があればここで設定する
//    @ModelAttribute("trackingId")
//    public String addOneObject(@RequestHeader("X-Tracking-Id") Optional<String> trackingId) {
//        // Modelに追加するオブジェクトを返却する
//
//        log.debug("★★★Model Attribute");
//
//        return trackingId.orElse(UUID.randomUUID().toString());
//    }

    @ExceptionHandler
    public ModelAndView handleSystemException(HttpServletRequest req, HttpServletResponse res, Exception e) {
        // 例外ハンドリングを行う
        log.error("★★★System Error occurred.", e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        // 今回はajax時のエラーしか想定していない
        if(isAjax(req)) {
        	log.debug("★★★ajaxでエラー");
        	mav.setView(new MappingJackson2JsonView());
        	res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        	return mav;
        }else {
        	log.debug("★★★通常の通信でエラー");
        	mav.setViewName("error");
        	return mav;
        }
    }
    
    private boolean isAjax(HttpServletRequest request) {
        String boolStr = request.getHeader("Is-Ajax-Request");
        return Boolean.TRUE.toString().equals(boolStr);
    }
}
