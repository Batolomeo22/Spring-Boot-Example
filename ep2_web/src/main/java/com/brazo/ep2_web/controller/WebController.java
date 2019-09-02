package com.brazo.ep2_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WebController {

    @RequestMapping("/index")
    public String getWeb(Map map){
        map.put("backval","you even can input thymeleaf..");
        return "index";
    }

    @RequestMapping("/indexJsp")
    public String getJsp(){
        return "jsp/indexJsp";
    }
}
