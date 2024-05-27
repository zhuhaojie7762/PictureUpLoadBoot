package com.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UpLoadController {

    @RequestMapping(value = "uploadPicture")
    public String getHtml(){
        return "upload";
    }
}
