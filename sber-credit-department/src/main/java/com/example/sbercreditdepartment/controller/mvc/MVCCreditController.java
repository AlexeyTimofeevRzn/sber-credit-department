package com.example.sbercreditdepartment.controller.mvc;

import com.example.sbercreditdepartment.service.CreditService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/credits")
public class MVCCreditController {

    private final CreditService creditService;

    public MVCCreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/getAll")
    public String getAllCredits(Model model) {
        model.addAttribute("credits", creditService.getAllCredits());
        return "credits/listAll";
    }

    @GetMapping("/{id}")
    public String getOneCredit(@PathVariable("id") int id, Model model) {
        model.addAttribute("credit", creditService.getOneCredit(id));
        return "credits/showOne";
    }
}
