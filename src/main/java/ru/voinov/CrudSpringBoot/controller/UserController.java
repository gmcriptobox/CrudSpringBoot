package ru.voinov.CrudSpringBoot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.voinov.CrudSpringBoot.service.RoleService;
import ru.voinov.CrudSpringBoot.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService,RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String index(Model model, Principal principal){
        model.addAttribute("user", userService.getUserByUserName(principal.getName()));
        model.addAttribute("roles", roleService.getAllRole());
        return "user/index";
    }
}
