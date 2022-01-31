package ru.voinov.CrudSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.voinov.CrudSpringBoot.model.Role;
import ru.voinov.CrudSpringBoot.model.User;
import ru.voinov.CrudSpringBoot.service.RoleService;
import ru.voinov.CrudSpringBoot.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping()
    public String getAllUser(Model model){
        model.addAttribute("userList", userService.listUsers());
        model.addAttribute("user", new User());
        return "admin/index";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable("id")long id, Model model){
        userService.deleteById(id);
        model.addAttribute("userList", userService.listUsers());
        model.addAttribute("user", new User());
        return "admin/index";
    }

    @GetMapping("/edit/{id}")
    public String editUsers(@PathVariable("id")long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user")User user,
                           @PathVariable ("id")long id, @RequestParam(name = "role", required = false) String[] roles){
        Set<Role> rolesSet = new HashSet<>();
        for(String s: roles){
            rolesSet.add(roleService.findByName(s));
        }
        user.setRoles(rolesSet);
        user.setPassword(userService.getUserById(user.getId()).getPassword());
        userService.update(id, user);
        return "redirect:/admin";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user")User user,
            @RequestParam(name = "role", required = false) String[] roles){
        Set<Role> rolesSet = new HashSet<>();
        for(String s: roles){
            rolesSet.add(roleService.findByName(s));
        }
        user.setRoles(rolesSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(user);
        return "redirect:/admin";
    }


}
