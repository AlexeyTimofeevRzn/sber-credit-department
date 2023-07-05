package com.example.sbercreditdepartment.controller.mvc;

import com.example.sbercreditdepartment.repository.ManagerRepository;
import com.example.sbercreditdepartment.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/managers")
public class MVCManagerController {

    private final ManagerService managerService;

    public MVCManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/all")
    public String getAllManagers(Model model) {
        model.addAttribute("managers", managerService.getAllManagers());
        return "managers/viewAll";
    }

    @GetMapping("/{id}")
    public String getManager(@PathVariable("id") int id, Model model) {
        model.addAttribute("manager", managerService.getManager(id));
        model.addAttribute("users", managerService.getClientsOfManager(id));
        model.addAttribute("contracts", managerService.getContractsOfManager(id));
        return "managers/viewOne";
    }

}

