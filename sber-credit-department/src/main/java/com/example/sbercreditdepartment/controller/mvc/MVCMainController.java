package com.example.sbercreditdepartment.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MVCMainController {

    @GetMapping
    public String getMainPage() {
        return "mainPage";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }

}
