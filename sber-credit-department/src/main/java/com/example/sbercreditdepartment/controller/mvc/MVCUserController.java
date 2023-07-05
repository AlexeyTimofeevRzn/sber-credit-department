package com.example.sbercreditdepartment.controller.mvc;

import com.example.sbercreditdepartment.dto.UserDTO;
import com.example.sbercreditdepartment.service.UserService;
import com.example.sbercreditdepartment.service.userdetails.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/users")
public class MVCUserController {

    private final UserService userService;

    public MVCUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllClients());
        return "users/viewAll";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getClient(id));
        model.addAttribute("requests", userService.getActiveRequestsOfUser(id));
        model.addAttribute("contracts", userService.getCreditContractsOfUser(id));
        return "users/viewProfile";
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = customUserDetails.getId();
        model.addAttribute("user", userService.getClient(id));
        model.addAttribute("requests", userService.getActiveRequestsOfUser(id));
        model.addAttribute("contracts", userService.getCreditContractsOfUser(id));
        return "users/viewProfile";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userDTO) {
        userService.create(userDTO);
        return "redirect:/main";
    }

}
