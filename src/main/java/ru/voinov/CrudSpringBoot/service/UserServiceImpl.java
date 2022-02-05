package ru.voinov.CrudSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voinov.CrudSpringBoot.dao.RoleRepository;
import ru.voinov.CrudSpringBoot.dao.UserDao;
import ru.voinov.CrudSpringBoot.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;


    @Override
    public void add(User user) {
        userDao.add(user);
    }


    @Override
    public List<User> listUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void deleteById(long id) {
        userDao.deleteById(id);
    }

    @Override
    public User getUserByEmail(String userName){
        return userDao.getUserByEmail(userName);
    }

    @Override
    public void update(long id, User userUpdater) {

        userDao.update(id, userUpdater);
    }

    @Override
    public User getUserById(long id){
        return userDao.getUserById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByEmail(username);
    }
}
