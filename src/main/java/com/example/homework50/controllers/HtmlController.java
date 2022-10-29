package com.example.homework50.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {

    @RequestMapping("html")
    public String hh(){
        return "hM57.html";
    }
}
