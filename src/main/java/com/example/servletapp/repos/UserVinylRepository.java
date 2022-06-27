package com.example.servletapp.repos;

import com.example.servletapp.models.UserModel;
import com.example.servletapp.models.VinylModel;
import com.example.servletapp.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserVinylRepository {
    VinylRepository vinylDao = VinylRepository.getInstance();

    private UserVinylRepository() {
    }

    private static class SingletonHelper {
        private static final UserVinylRepository INSTANCE = new UserVinylRepository();
    }

    public static UserVinylRepository getInstance() {
        return UserVinylRepository.SingletonHelper.INSTANCE;
    }

    public Set<VinylModel> find(Integer user_id) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        Set<VinylModel> vinylInCollections = new HashSet<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // resolve LazyInitializationException by changing FetchType to EAGER
            // in ManyToMany annotation
            String hql = "FROM UserModel U WHERE U.id = :id";
            Query query = session.createQuery(hql, UserModel.class);
            query.setParameter("id", user_id);
            UserModel user = (UserModel) query.list().get(0);
            vinylInCollections = user.getVinyls();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return vinylInCollections;
    }

    public void save(String username, String title) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM UserModel U WHERE U.username = :username";
            Query query = session.createQuery(hql, UserModel.class);
            query.setParameter("username", username);
            UserModel user = (UserModel) query.list().get(0);

            hql = "FROM VinylModel U WHERE U.title = :title";
            query = session.createQuery(hql, VinylModel.class);
            query.setParameter("title", title);
            VinylModel vinyl = (VinylModel) query.list().get(0);

            user.addVinyl(vinyl);

            session.persist(user);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void saveNew(String username, String author, String title) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            VinylModel vinyl = new VinylModel(author, title);
            vinylDao.save(vinyl);

            save(username, title);
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(Integer user_id, Integer vinyl_id) throws SQLException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM UserModel U WHERE U.id = :id";
            Query query = session.createQuery(hql, UserModel.class);
            query.setParameter("id", user_id);
            UserModel user = (UserModel) query.list().get(0);

            hql = "FROM VinylModel U WHERE U.id = :id";
            query = session.createQuery(hql, VinylModel.class);
            query.setParameter("id", vinyl_id);
            VinylModel vinyl = (VinylModel) query.list().get(0);

            user.getVinyls().remove(vinyl);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
