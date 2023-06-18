package com.example.sbercreditdepartment.controller.mvc;

import com.example.sbercreditdepartment.dto.CreditDTO;
import com.example.sbercreditdepartment.dto.RequestDTO;
import com.example.sbercreditdepartment.service.CreditService;
import com.example.sbercreditdepartment.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Map;

@Controller
@RequestMapping("/requests")
public class MVCRequestController {

    private final CreditService creditService;

    private final RequestService requestService;

    public MVCRequestController(CreditService creditService, RequestService requestService) {
        this.creditService = creditService;
        this.requestService = requestService;
    }

    @GetMapping("/all")
    public String getAllRequests(Model model) {
        model.addAttribute("requests", requestService.getAllRequests());
        return "requests/allRequests";
    }

    @GetMapping("/{id}")
    public String getOneRequest(@PathVariable("id") int id, Model model) {
        model.addAttribute(requestService.getOneRequest(id));
        return "requests/showOne";
    }

    @GetMapping("/accept/{id}")
    public String acceptRequest(@PathVariable("id") int id) {
        requestService.acceptRequest(requestService.getOneRequest(id));
        return "redirect:/requests/all";
    }

    @GetMapping("/decline/{id}")
    public String declineRequest(@PathVariable("id") int id) {
        requestService.declineRequest(requestService.getOneRequest(id));
        return "redirect:/requests/all";
    }

    @PostMapping("/new")
    public String saveRequest(@ModelAttribute("formRequest") RequestDTO requestDTO) {
        requestService.saveRequest(requestDTO);
        return "redirect:/credits/getAll";
    }
}
