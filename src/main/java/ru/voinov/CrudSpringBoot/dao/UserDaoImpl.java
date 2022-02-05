package ru.voinov.CrudSpringBoot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.voinov.CrudSpringBoot.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<User> getAllUsers() {
        return manager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public void deleteById(long id) {
        manager.createQuery("DELETE FROM User u WHERE u.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public void add(User user){
        manager.persist(user);
    }

    @Override
    public void update(long id, User userUpdater) {
        User user = getUserById(id);
        user.setMail(userUpdater.getMail());
        user.setName(userUpdater.getName());
        user.setSecondName(userUpdater.getSecondName());
        user.setAge(userUpdater.getAge());
        manager.merge(user);
    }

    @Override
    public User getUserById(long id){
        List<User> list = manager.createQuery("SELECT u FROM User u WHERE u.id =:id")
                .setParameter("id", id).getResultList();
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    public User getUserByEmail(String email){
        List<User> list = manager.createQuery("SELECT u FROM User u WHERE u.mail =:mail")
                .setParameter("mail", email).getResultList();
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

}
