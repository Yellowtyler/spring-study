package com.study;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class SessionFactory {
    private static final org.hibernate.SessionFactory sf;

    static {
        sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static Session getSession() {
        return sf.getCurrentSession();
    }
}
