package com.example.sbercreditdepartment.controller.mvc;

import com.example.sbercreditdepartment.dto.UserDTO;
import com.example.sbercreditdepartment.model.CreditContract;
import com.example.sbercreditdepartment.service.UserService;
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
        model.addAttribute("contracts", userService.getCreditContractsByUserId(id));
        return "users/viewProfile";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userDTO) {
//        userDTO.setRoleId(1);
//        userDTO.setClientStatus(1);
        userService.create(userDTO);
        return "redirect:/main";
    }

//    @GetMapping("/admin")
//    public String getAdmin() {
////        model.addAttribute("");
//        return "users/viewAdminProfile";
//    }
}
