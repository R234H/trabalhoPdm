package com.server.autenticacao.repository;

import javax.persistence.EntityManager;

public class DefaultRepository {

    protected interface WithArg<T> {
        T apply() throws Exception;
    }

    protected <T> T tryTransaction(WithArg func, EntityManager em) throws Exception {
        try {
            return (T) func.apply();
        }catch (Exception e) {
            em.getTransaction().rollback();
            throw (e);
        } finally {
            em.close();
        }
    }
}
