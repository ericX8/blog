package com.ec.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutPreController {

    @GetMapping("/about")
    public String about(){

    return "about";
    }
}
