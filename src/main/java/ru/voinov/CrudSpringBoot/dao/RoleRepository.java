package ru.voinov.CrudSpringBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.voinov.CrudSpringBoot.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
