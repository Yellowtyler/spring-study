package com.study.daniil;

import com.study.daniil.model.Lot;
import com.study.daniil.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class MainApp {
    private static final SessionFactory sf;
    static {
        sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void forcePrepareData() {
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("createInsert.sql")).collect(Collectors.joining(" "));
            session = sf.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    public static void increaseOptimistic(Long randomNum, User user) {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        Lot lot = (Lot) session.createQuery("from Lot where id=:id").setParameter("id", randomNum).
                setLockMode(LockModeType.OPTIMISTIC).
                uniqueResult();
        lot.setBet(lot.getBet() + 100);
        lot.setUser(user);
        session.update(lot);
        try {
            session.getTransaction().commit();
        } catch (OptimisticLockException e) {
            session.getTransaction().rollback();
            increaseOptimistic(randomNum, user);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void increasePessimistic(Long randomNum, User user) {
        Session session = sf.getCurrentSession();
        try {
            session.beginTransaction();
            Lot lot = (Lot) session.createQuery("from Lot where id=:id").setParameter("id", randomNum).
                    setLockMode(LockModeType.PESSIMISTIC_WRITE).
                    uniqueResult();
            lot.setBet(lot.getBet() + 100);
            lot.setUser(user);
            session.update(lot);
            session.getTransaction().commit();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static boolean checkSum() {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        List<Lot> lots = session.createQuery("from Lot").getResultList();
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
        int sum = 0;
        for (Lot lot : lots) {
            sum+=lot.getBet();
        }
        return sum==800000;
    }

    //Pessimistic: 10793
    //Optimistic: 13750
    public static void main(String[] args) {
        forcePrepareData();
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        final CountDownLatch cdl = new CountDownLatch(8);
        for (int i = 0; i < 8; i++) {
            long finalI = i;

            new Thread(()->{
                Session session = sf.getCurrentSession();
                session.beginTransaction();
                User user = session.get(User.class, finalI +1);
                session.getTransaction().commit();
                if (session != null) {
                    session.close();
                }
                for (int j = 0; j < 1000; j++) {
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
                    //increasePessimistic((long) randomNum, user);
                    increaseOptimistic((long) randomNum, user);
                }
                cdl.countDown();
            }).start();

        }
        long time = System.currentTimeMillis();
        try {
            cdl.await();
            System.out.println(System.currentTimeMillis()-time);
            System.out.println(checkSum());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
