package com.study.repository;

import com.study.SessionFactory;
import com.study.model.Product;

import org.hibernate.Session;



public class ProductRepository {

    public void save(Product product) {
        Session session = SessionFactory.getSession();
        session.beginTransaction();
        session.persist(product);
        session.getTransaction().commit();
        session.close();
    }

    public Product findById(Long id) {
        Session session = SessionFactory.getSession();
        session.beginTransaction();
        Product product = session.find(Product.class, id);
        session.getTransaction().commit();
        session.close();
        return product;
    }

    public Product findByName(String title) {
        Session session = SessionFactory.getSession();
        session.beginTransaction();
        String jpql = "select p from Product p where p.title = :title";
        Product product = session
                .createQuery(jpql, Product.class)
                .setParameter("title", title)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return product;
    }

    public boolean deleteProduct(String title) {
        Product deletedProduct = findByName(title);
        if(deletedProduct!=null) {
            Session session = SessionFactory.getSession();
            session.beginTransaction();
            session.remove(deletedProduct);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        return false;
    }
}
