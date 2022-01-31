package ru.voinov.CrudSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voinov.CrudSpringBoot.dao.RoleRepository;
import ru.voinov.CrudSpringBoot.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public void add(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRole() {
        return  roleRepository.findAll();
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
