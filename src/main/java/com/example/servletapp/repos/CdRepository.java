package com.example.servletapp.repos;

import com.example.servletapp.models.CdModel;
import com.example.servletapp.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CdRepository {
    private CdRepository() {
    }

    private static class SingletonHelper {
        private static final CdRepository INSTANCE = new CdRepository();
    }

    public static CdRepository getInstance() {
        return CdRepository.SingletonHelper.INSTANCE;
    }

    public List<CdModel> findAll() throws SQLException {
        List<CdModel> cdInCollections = new ArrayList<>();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CdModel> cq = cb.createQuery(CdModel.class);
            Root<CdModel> rootEntry = cq.from(CdModel.class);
            CriteriaQuery<CdModel> all = cq.select(rootEntry);
            TypedQuery<CdModel> allQuery = session.createQuery(all);
            cdInCollections = allQuery.getResultList();
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

        return cdInCollections;
    }

    public void save(CdModel cdModel) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(cdModel);
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
