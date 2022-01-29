package ru.voinov.CrudSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.voinov.CrudSpringBoot.model.User;
import ru.voinov.CrudSpringBoot.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService userService;

    @GetMapping()
    public String getAllUser(Model model){
        model.addAttribute("userList", userService.listUsers());
        model.addAttribute("user", new User());
        return "users/index";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable("id")long id, Model model){
        userService.deteleById(id);
        model.addAttribute("userList", userService.listUsers());
        model.addAttribute("user", new User());
        return "users/index";
    }

    @GetMapping("/edit/{id}")
    public String editUsers(@PathVariable("id")long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user")User user,
                           @PathVariable ("id")long id){
        userService.update(id, user);
        return "redirect:/users";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user")User user){
        userService.add(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id")long id){
        userService.deteleById(id);
        return "redirect:/users";
    }


}
