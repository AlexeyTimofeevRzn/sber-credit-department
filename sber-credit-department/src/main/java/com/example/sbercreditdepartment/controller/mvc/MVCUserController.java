package com.example.sbercreditdepartment.controller.mvc;

import com.example.sbercreditdepartment.dto.UserDTO;
import com.example.sbercreditdepartment.exception.AuthenticationException;
import com.example.sbercreditdepartment.service.UserService;
import com.example.sbercreditdepartment.service.userdetails.CustomUserDetails;
import com.example.sbercreditdepartment.utils.constants.UserRolesConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
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
    public String registration(@ModelAttribute("userForm") UserDTO userDTO, BindingResult bindingResult) {
        if (userDTO.getLogin().equalsIgnoreCase(UserRolesConstants.ADMIN) || userService.getUserByLogin(userDTO.getLogin()) != null) {
            bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
            return "registration";
        }
        if (userDTO.getFirstName() == null) {
            bindingResult.rejectValue("firstName", "error.firstName", "Имя нужно заполнить");
            return "registration";
        }
        if (userDTO.getLastName() == null) {
            bindingResult.rejectValue("lastName", "error.lastName", "Фамилию нужно заполнить");
            return "registration";
        }
        if (userService.getUserByEmail(userDTO.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email", "Такой e-mail уже существует");
            return "registration";
        }
        userService.create(userDTO);
        log.info("Создан новый пользователь: " + userDTO.toString());
        return "redirect:/main";
    }

    @ExceptionHandler({AuthenticationException.class})
    public RedirectView handleError(HttpServletRequest request,
                                    Exception ex,
                                    RedirectAttributes redirectAttributes) {
        log.error("Запрос " + request.getRequestURL() + " вызвал ошибку " + ex.getMessage());
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        redirectAttributes.addFlashAttribute("exception", ex.getMessage());
        return new RedirectView("/users/profile/" + customUserDetails.getId(), true);
    }


}
