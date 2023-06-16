package com.example.sbercreditdepartment.controller.mvc;

import com.example.sbercreditdepartment.dto.CreditDTO;
import com.example.sbercreditdepartment.dto.RequestDTO;
import com.example.sbercreditdepartment.service.CreditService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/requests")
public class MVCRequestController {

    private final CreditService creditService;

    public MVCRequestController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/new")
    public String newRequest(@ModelAttribute("credit")CreditDTO creditDTO, Model model) {
        RequestDTO dto = new RequestDTO();
        dto.setCredit(creditDTO.getId());
        model.addAttribute("formRequest", dto);
        model.addAttribute("credit", creditDTO);
        return "requests/newRequest";
    }


}
