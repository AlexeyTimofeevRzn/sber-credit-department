package com.example.sbercreditdepartment.controller.mvc;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.dto.TwoDatesDTO;
import com.example.sbercreditdepartment.service.CreditContractService;
import com.example.sbercreditdepartment.service.userdetails.CustomUserDetails;
import lombok.experimental.PackagePrivate;
import org.springframework.security.core.context.SecurityContextHolder;
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
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("contracts", creditContractService.getContractsOfManager(customUserDetails.getId()));
        return "creditContracts/viewAllContracts";
    }

    @GetMapping("/overview/betweenTwoDates")
    public String getContractsBetweenTwoDates(Model model) {
        model.addAttribute("twoDatesDTO", new TwoDatesDTO());
        return "overview/contractsBetweenTwoDates";
    }

    @PostMapping("/overview/betweenTwoDates")
    public String getContractsBetweenTwoDates(@ModelAttribute("twoDatesDTO") TwoDatesDTO dto, Model model) {
        model.addAttribute("start", dto.getStartDate());
        model.addAttribute("end", dto.getEndDate());
        model.addAttribute("contracts", creditContractService.getContractsBetweenTwoDates(dto));
        return "overview/contractsBetweenTwoDates";
    }

    @GetMapping("/{id}")
    public String getOneContract(@PathVariable("id") int id, Model model) {
        model.addAttribute("contract", creditContractService.getContract(id));
        return "creditContracts/viewContract";
    }

    @PostMapping("/save")
    public String saveContract(@ModelAttribute("formCreditContract") CreditContractDTO creditContractDTO) {
        creditContractService.saveContract(creditContractDTO);
        return "redirect:/credits/getAll";
    }
}
