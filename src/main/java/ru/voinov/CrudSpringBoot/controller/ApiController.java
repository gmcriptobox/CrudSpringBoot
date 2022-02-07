package ru.voinov.CrudSpringBoot.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.voinov.CrudSpringBoot.dao.RoleRepository;
import ru.voinov.CrudSpringBoot.model.Role;
import ru.voinov.CrudSpringBoot.model.User;
import ru.voinov.CrudSpringBoot.service.RoleService;
import ru.voinov.CrudSpringBoot.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleService;

    @GetMapping ("/about")
    public User getCurrentUser(Principal principal){
        return userService.getUserByEmail(principal.getName());
    }

    @GetMapping ("/users")
    public List<User> getAllUsers(){
        return userService.listUsers();
    }

    @GetMapping ("/users/{id}")
    public User getUser(@PathVariable("id")long id){
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        Set<Role> rolesSet = new HashSet<>();
        for(Role r: user.getRoles()){
            rolesSet.add(roleService.findByName(r.getName()));
        }
        user.setRoles(rolesSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public boolean removeUser(@PathVariable("id")long id) {
        userService.deleteById(id);
        return true;
    }

    @PutMapping("/users")
    public User editUser(@RequestBody User user) {
        User userDb = null;
        if(user.getRoles().size() == 0 || user.getPassword().equals("")) {
            userDb = userService.getUserById(user.getId());
        }
        if(user.getRoles().size() != 0) {
            Set<Role> rolesSet = new HashSet<>();
            for (Role r : user.getRoles()) {
                rolesSet.add(roleService.findByName(r.getName()));
            }
            user.setRoles(rolesSet);
        } else {
            user.setRoles(userDb.getRoles());
        }
        if(user.getPassword().equals("")) {
            user.setPassword(userDb.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.update(user);
        return user;
    }

}
