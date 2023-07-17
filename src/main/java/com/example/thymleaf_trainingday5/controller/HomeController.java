package com.example.thymleaf_trainingday5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/employee")
    public String index() {
        return "index";
    }
}
