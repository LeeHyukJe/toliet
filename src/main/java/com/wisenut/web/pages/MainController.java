package com.wisenut.web.pages;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {

    @GetMapping(value = {"/"})
    public String entry(){
        return "index";
    }
}
