package com.server.autenticacao.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtils {

    private static final String persistenceUnitName = "autenticacao";
    private static EntityManagerFactory ENTITY_MANEGER_FACTORY;

    public static EntityManager createEntityManager(){
        if(ENTITY_MANEGER_FACTORY == null){
            generateEntityManegerFactory();
        }
        return ENTITY_MANEGER_FACTORY.createEntityManager();
    }

    private static void generateEntityManegerFactory(){
        ENTITY_MANEGER_FACTORY = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

}
