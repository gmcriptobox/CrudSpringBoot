package ru.voinov.CrudSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voinov.CrudSpringBoot.dao.UserDao;
import ru.voinov.CrudSpringBoot.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public void add(User user) {
        userDao.add(user);
    }


    @Override
    public List<User> listUsers() {
        List<User> list = userDao.getAllUsers();
        return list;
    }

    @Override
    public void deteleById(long id) {
        userDao.deleteById(id);
    }

    @Override
    public void update(long id, User userUpdater) {

        userDao.update(id, userUpdater);
    }

    @Override
    public User getUserById(long id){
        return userDao.getUserById(id);
    }




}
