package com.study.repository;

import com.study.SessionFactory;
import com.study.model.Consumer;
import org.hibernate.Session;

public class ConsumerRepository {

    public Consumer findById(Long id) {
        Session session = SessionFactory.getSession();
        session.beginTransaction();
        Consumer consumer = session.find(Consumer.class, id);
        session.getTransaction().commit();
        session.close();
        return consumer;
    }

    public void save(Consumer consumer) {
        Session session = SessionFactory.getSession();
        session.beginTransaction();
        session.persist(consumer);
        session.getTransaction().commit();
        session.close();
    }

    public Consumer findByName(String name) {
        Session session = SessionFactory.getSession();
        session.beginTransaction();
        String jpql = "select c from Consumer c where c.name = :name";
        Consumer consumer = session
                .createQuery(jpql, Consumer.class)
                .setParameter("name", name).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return consumer;
    }

    public boolean deleteConsumer(String name) {
        Consumer deletedConsumer = findByName(name);
        if(deletedConsumer!=null) {
            Session session = SessionFactory.getSession();
            session.beginTransaction();
            session.remove(deletedConsumer);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        return false;
    }
}
