package ru.voinov.CrudSpringBoot.dao;

import ru.voinov.CrudSpringBoot.model.User;
import java.util.List;


public interface UserDao {

    List<User> getAllUsers();
    User getUserById(long id);
    User getUserByEmail(String userName);
    void deleteById(long id);
    void add(User user);
    void update(User userUpdater);
}
