package com.study.daniil.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtil {
    private final static SessionFactory sf;
    static {
        sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static Session getSession() {
        return sf.getCurrentSession();
    }

    public static void close() {
        if(sf!=null) {
            sf.close();
        }
    }
}
