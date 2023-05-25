package com.noname.kata.crud.DAO;

import com.noname.kata.crud.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoJPAImpl implements UserDao {

    private final EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserDaoJPAImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();
            List<User> users = em.createQuery("select u from User u", User.class).getResultList();
            System.out.println(users.size());
            return users;
        } finally {
            if (em != null) {
                em.close();
            }
        }


    }

    @Override
    public void removeById(Long id) {
        User userToRemove = em.find(User.class, id);
        em.remove(userToRemove);
    }


    @Override
    public void saveNewUserProfile(User user) {
        em.persist(user);
    }

    @Override
    public User getById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void updateUserProfile(User user) {
        em.merge(user);
    }

    @Override
    public Boolean isUserExistsWithEmail(String email) {
        Query isExists = em.createQuery("SELECT COUNT(u) > 0 FROM User u WHERE LOWER(u.email)=LOWER(:email)");
        isExists.setParameter("email", email);
        return (Boolean) isExists.getSingleResult();
    }

}
