package com.example.servletapp.Dao;

import com.example.servletapp.models.UserModel;
import com.example.servletapp.models.VinylModel;
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

public class VinylDaoClass implements VinylDao{
    private VinylDaoClass() {
    }

    private static class SingletonHelper {
        private static final VinylDaoClass INSTANCE = new VinylDaoClass();
    }

    public static VinylDaoClass getInstance() {
        return VinylDaoClass.SingletonHelper.INSTANCE;
    }

    @Override
    public VinylModel find(String id) throws SQLException {
        VinylModel vinyl = new VinylModel();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            vinyl = session.get(VinylModel.class, Integer.parseInt(id));

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
        return vinyl;
    }

    @Override
    public List<VinylModel> findAll() throws SQLException {
        List<VinylModel> vinylInCollections = new ArrayList<>();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<VinylModel> cq = cb.createQuery(VinylModel.class);
            Root<VinylModel> rootEntry = cq.from(VinylModel.class);
            CriteriaQuery<VinylModel> all = cq.select(rootEntry);
            TypedQuery<VinylModel> allQuery = session.createQuery(all);
            vinylInCollections = allQuery.getResultList();
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

    @Override
    public void save(VinylModel vinylModel) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(vinylModel);
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
    public void update(VinylModel vinylModel) throws SQLException {

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.update(vinylModel);
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
    public void delete(String id) throws SQLException {

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String query = "from VinylModel v where v.id="+id;

            Query query2 = session.createQuery(query, VinylModel.class);

            VinylModel vinyl= (VinylModel) query2.list().get(0);

            session.delete(vinyl);
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
