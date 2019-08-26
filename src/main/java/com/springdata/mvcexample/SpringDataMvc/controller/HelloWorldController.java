package com.springdata.mvcexample.SpringDataMvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }
}
