package com.example.servletapp.Dao;

import com.example.servletapp.models.UserModel;
import com.example.servletapp.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoClass implements UserDao{
    private UserDaoClass() {
    }

    private static class SingletonHelper {
        private static final UserDaoClass INSTANCE = new UserDaoClass();
    }

    public static UserDaoClass getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public UserModel find(String s) throws SQLException {
        return null;
    }

    @Override
    public List<UserModel> findAll() throws SQLException {
        List<UserModel> users = new ArrayList<>();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserModel> cq = cb.createQuery(UserModel.class);
            Root<UserModel> rootEntry = cq.from(UserModel.class);
            CriteriaQuery<UserModel> all = cq.select(rootEntry);
            TypedQuery<UserModel> allQuery = session.createQuery(all);
            users = allQuery.getResultList();
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
        return users;
    }

    @Override
    public void save(UserModel userModel) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(userModel);
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

    @Override
    public void update(UserModel o) throws SQLException {
    }

    @Override
    public void delete(String id) throws SQLException {

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String query = "from UserModel u where u.id="+id;

            Query query2 = session.createQuery(query, UserModel.class);

            UserModel user= (UserModel) query2.list().get(0);

            session.delete(user);
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
