package com.example.sbercreditdepartment.controller.mvc;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.service.CreditContractService;
import lombok.experimental.PackagePrivate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/creditContracts")
public class MVCCreditContractsController {

    private final CreditContractService creditContractService;

    public MVCCreditContractsController(CreditContractService creditContractService) {
        this.creditContractService = creditContractService;
    }

    @GetMapping("/all")
    public String getCreditContracts(Model model) {
        model.addAttribute("contracts", creditContractService.getAllContracts());
        return "creditContracts/viewAllContracts";
    }

    @GetMapping("/{id}")
    public String getOneContract(@PathVariable("id") int id, Model model) {
        model.addAttribute("contract", creditContractService.getContract(id));
        return "creditContracts/viewContract";
    }

    @PostMapping("/save")
    public String saveContract(@ModelAttribute("formCreditContract") CreditContractDTO creditContractDTO, Model model) {
        creditContractService.saveContract(creditContractDTO);
        return "redirect:/credits/getAll";
    }
}
