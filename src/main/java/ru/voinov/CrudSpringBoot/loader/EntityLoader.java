package ru.voinov.CrudSpringBoot.loader;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.voinov.CrudSpringBoot.model.Role;
import ru.voinov.CrudSpringBoot.model.User;
import ru.voinov.CrudSpringBoot.service.RoleService;
import ru.voinov.CrudSpringBoot.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class EntityLoader implements InitializingBean {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        Role adminRole  = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleService.add(adminRole);
        roleService.add(userRole);

        User[] users=new User[] {
                new User("Ivan", "Ivanov", (byte) 23
                        , "Ivan@mail.ru"
                        ,passwordEncoder.encode("pro100pass")
                        ,Set.of(adminRole, userRole)),

                new User("Petr", "Petrov", (byte)35
                        , "Petr@mail.ru"
                        ,passwordEncoder.encode("pro100pass")
                        ,Set.of(userRole))
        };
        userService.add(users[0]);
        userService.add(users[1]);
    }
}