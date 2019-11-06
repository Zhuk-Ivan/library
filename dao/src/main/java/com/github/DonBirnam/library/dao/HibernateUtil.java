package com.github.DonBirnam.library.dao;

import org.hibernate.Session;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static EntityManagerFactory emFactory = null;

    public static Session getSession() {
        if (emFactory == null) {
            emFactory = Persistence.createEntityManagerFactory("com.github.DonBirnam.library");
        }
        return emFactory.createEntityManager().unwrap(Session.class);
    }


    public static void closeEMFactory() {
        emFactory.close();
    }
}

