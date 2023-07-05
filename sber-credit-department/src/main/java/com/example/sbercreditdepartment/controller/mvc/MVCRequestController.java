package com.example.sbercreditdepartment.controller.mvc;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.dto.ManagerDTO;
import com.example.sbercreditdepartment.dto.RequestDTO;
import com.example.sbercreditdepartment.dto.TwoDatesDTO;
import com.example.sbercreditdepartment.model.Manager;
import com.example.sbercreditdepartment.model.Request;
import com.example.sbercreditdepartment.service.CreditService;
import com.example.sbercreditdepartment.service.ManagerService;
import com.example.sbercreditdepartment.service.RequestService;
import com.example.sbercreditdepartment.service.userdetails.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/requests")
public class MVCRequestController {

    private final CreditService creditService;

    private final RequestService requestService;

    private final ManagerService managerService;
    public MVCRequestController(CreditService creditService, RequestService requestService, ManagerService managerService) {
        this.creditService = creditService;
        this.requestService = requestService;
        this.managerService = managerService;
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

    @GetMapping("/overview/betweenTwoDates")
    public String getRequestsBetweenTwoDates(Model model) {
        model.addAttribute("twoDatesDTO", new TwoDatesDTO());
        return "overview/requestsBetweenTwoDates";
    }

    @PostMapping("/overview/betweenTwoDates")
    public String getRequestsBetweenTwoDates(@ModelAttribute("twoDatesDTO") TwoDatesDTO dto, Model model) {
        model.addAttribute("start", dto.getStartDate());
        model.addAttribute("end", dto.getEndDate());
        model.addAttribute("requests", requestService.getRequestsBetweenTwoDates(dto));
        return "overview/requestsBetweenTwoDates";
    }

    @GetMapping("/accept/{id}")
    public String acceptRequest(@PathVariable("id") int id) {
        Request request = requestService.getOneRequest(id);
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ManagerDTO manager = managerService.getManager(customUserDetails.getId());
        requestService.acceptRequest(request, manager);
        return "redirect:/requests/all";
    }

    @GetMapping("/decline/{id}")
    public String declineRequest(@PathVariable("id") int id) {
        requestService.declineRequest(requestService.getOneRequest(id));
        return "redirect:/requests/all";
    }

    @GetMapping("/sign/{id}")
    public String signRequest(@PathVariable("id") int id, Model model) {
        Request request = requestService.getOneRequest(id);
        model.addAttribute("request", request);
        model.addAttribute("manager", request.getManager());
        model.addAttribute("credit", request.getCredit());
        model.addAttribute("formCreditContract", new CreditContractDTO());
        return "creditContracts/newContract";
    }

    @PostMapping("/new")
    public String saveRequest(@ModelAttribute("formRequest") RequestDTO requestDTO) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        requestDTO.setUser(customUserDetails.getId());
        requestService.saveRequest(requestDTO);
        return "redirect:/credits/getAll";
    }
}
