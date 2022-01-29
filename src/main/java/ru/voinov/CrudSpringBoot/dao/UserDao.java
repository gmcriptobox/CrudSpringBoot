package ru.voinov.CrudSpringBoot.dao;

import ru.voinov.CrudSpringBoot.model.User;
import java.util.List;


public interface UserDao {

    List<User> getAllUsers();
    User getUserById(long id);
    void deleteById(long id);
    void add(User user);
    void update(long id, User userUpdater);
}
