package com.example.sbercreditdepartment.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class MVCLoginController {

    @GetMapping
    public String login() {
        return "login";
    }

//    @PostMapping
//    public String loginConfirmation() {
//        return "redirect:/main/about";
//    }

}
