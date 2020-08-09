package com.study.daniil.repository;

import com.study.daniil.model.User;
import com.study.daniil.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    public User findById(Long id) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        User user = session.find(User.class, id);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public List<User> findAll() {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    public void save(User user) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
