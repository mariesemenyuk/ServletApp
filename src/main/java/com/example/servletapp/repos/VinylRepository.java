package com.example.servletapp.repos;

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
import java.util.Random;


public class VinylRepository {
    private VinylRepository() {
    }

    private static class SingletonHelper {
        private static final VinylRepository INSTANCE = new VinylRepository();
    }

    public static VinylRepository getInstance() {
        return VinylRepository.SingletonHelper.INSTANCE;
    }

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

    public void addMillionsVinyls() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            for (int i = 0; i < 200_000; i++) {
                VinylModel vinyl = new VinylModel();
                vinyl.setAuthor("Author" + i);
                vinyl.setTitle("Title" + i);
                vinyl.setCountryIssued("AnyCountry");
                vinyl.setPrice((int) (Math.random()*(4000 - 1000 + 1) + 1000));
                session.save(vinyl);
                if (i % 1000 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public List<VinylModel> findMoreThanTwoThousand() throws SQLException {
        List<VinylModel> vinylInCollections = new ArrayList<>();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM VinylModel V WHERE price > 3000";
            Query query = session.createQuery(hql, VinylModel.class).setCacheable(true);
            vinylInCollections = query.list();

            List<VinylModel> vinyls = session.createQuery(hql, VinylModel.class).list();
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
}
