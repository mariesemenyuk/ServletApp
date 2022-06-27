package com.example.servletapp.repos;

import com.example.servletapp.models.UserModel;
import com.example.servletapp.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private UserRepository() {
    }

    private static class SingletonHelper {
        private static final UserRepository INSTANCE = new UserRepository();
    }

    public static UserRepository getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public List<UserModel> findAll() throws SQLException {
        List<UserModel> users = new ArrayList<>();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // resolve n+1 problem
            String hql = "FROM UserModel U left join fetch U.vinyls";
            Query query = session.createQuery(hql, UserModel.class);
            users = query.list();

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
