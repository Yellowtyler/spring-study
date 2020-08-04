package com.study.daniil.repository;

import com.study.daniil.util.SessionUtil;
import com.study.daniil.model.Item;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemRepository {
    public Item findById(Long id) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        Item item = session.find(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public List<Item> findAll() {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("from Item").getResultList();
        session.getTransaction().commit();
        session.close();
        return items;
    }

    public void save(Item item) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }
}
