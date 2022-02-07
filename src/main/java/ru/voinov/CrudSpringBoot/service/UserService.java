package ru.voinov.CrudSpringBoot.service;

import ru.voinov.CrudSpringBoot.model.User;
import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getUserById(long id);
    User getUserByEmail(String userName);
    void deleteById(long id);
    void update(User userUpdater);

}