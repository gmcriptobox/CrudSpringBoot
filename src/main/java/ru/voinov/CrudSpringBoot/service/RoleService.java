package ru.voinov.CrudSpringBoot.service;

import ru.voinov.CrudSpringBoot.model.Role;

import java.util.List;

public interface RoleService {
    void add(Role role);
    List<Role> getAllRole();
    Role findByName(String name);
}
