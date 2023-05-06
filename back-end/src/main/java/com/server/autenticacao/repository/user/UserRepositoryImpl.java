package com.server.autenticacao.repository.user;

import com.server.autenticacao.dtos.RegisterUserDTO;
import com.server.autenticacao.model.User;
import com.server.autenticacao.repository.DefaultRepository;
import com.server.autenticacao.utils.HibernateUtils;


import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserRepositoryImpl extends DefaultRepository implements UserRepository {


    @Override
    public User registrarUsuario(RegisterUserDTO registerUserDTO) throws Exception {
        EntityManager em = HibernateUtils.createEntityManager();
        return tryTransaction(() -> {
            User user = new User(registerUserDTO);
            em.persist(user);
            em.getTransaction().commit();
            return user;
        }, em);
    }

    @Override
    public User buscarUsuario(String username) throws Exception {
        EntityManager em = HibernateUtils.createEntityManager();
        return tryTransaction(() -> {
            String jpql = "select u from User u where u.email = :pEmail";
            Query query = em.createQuery(jpql);
            query.setParameter("pEmail", username);
            return (User) query.getSingleResult();
        }, em);
    }

    @Override
    public User buscarUsuarioPorId(Long id) throws Exception {
        EntityManager em = HibernateUtils.createEntityManager();
        return tryTransaction(() -> {
            String jpql = "select u from User u where u.id = :pId";
            Query query = em.createQuery(jpql);
            query.setParameter("pId", id);
            return (User) query.getSingleResult();
        }, em);
    }
}
