package com.example.sbercreditdepartment.controller.mvc;

import com.example.sbercreditdepartment.dto.CreditDTO;
import com.example.sbercreditdepartment.dto.RequestDTO;
import com.example.sbercreditdepartment.service.CreditService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String getOneCredit(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        CreditDTO dto = creditService.getOneCredit(id);
        model.addAttribute("creditDTO", dto);
        return "credits/showOne";
    }

    @GetMapping("/delete/{id}")
    public String deleteOneCredit(@PathVariable("id") int id) {
        creditService.softDelete(id);
        return "redirect:/credits/getAll";
    }

    @GetMapping("/{id}/request/new")
    public String newRequest(@PathVariable("id") int id, Model model) {
        RequestDTO dto = new RequestDTO();
        dto.setCredit(id);
        model.addAttribute("formRequest", dto);
        model.addAttribute("creditDTO", creditService.getOneCredit(id));
        return "requests/newRequest";
    }

    @GetMapping("/add")
    public String addCredit(Model model) {
        model.addAttribute("formCredit", new CreditDTO());
        return "credits/newCredit";
    }
}
