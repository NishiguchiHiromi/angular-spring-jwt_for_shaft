package com.example.mySource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mySource.abstractclass.AbstractController;

@Controller
public class MainController extends AbstractController {

    @RequestMapping(path="/")
    public String root() {
        return "redirect:/main/index.html";
    }
    
    @RequestMapping(path="/outsideLogin/serverThrow")
    public String error() {
    	int a = 1/0;
        return "hoge";
    }
}
