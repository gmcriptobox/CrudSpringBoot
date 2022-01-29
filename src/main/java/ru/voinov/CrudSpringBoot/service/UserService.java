package ru.voinov.CrudSpringBoot.service;

import ru.voinov.CrudSpringBoot.model.User;
import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getUserById(long id);
    void deteleById(long id);
    void update(long id, User userUpdater);

}