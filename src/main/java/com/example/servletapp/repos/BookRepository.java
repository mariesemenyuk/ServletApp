package com.example.servletapp.repos;

import com.example.servletapp.models.BookModel;
import com.example.servletapp.models.VinylModel;
import com.example.servletapp.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookRepository {
    private BookRepository() {
    }

    private static class SingletonHelper {
        private static final BookRepository INSTANCE = new BookRepository();
    }

    public static BookRepository getInstance() {
        return BookRepository.SingletonHelper.INSTANCE;
    }

    public List<BookModel> findAll() throws SQLException {
        List<BookModel> bookInCollections = new ArrayList<>();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<BookModel> cq = cb.createQuery(BookModel.class);
            Root<BookModel> rootEntry = cq.from(BookModel.class);
            CriteriaQuery<BookModel> all = cq.select(rootEntry);
            TypedQuery<BookModel> allQuery = session.createQuery(all);
            bookInCollections = allQuery.getResultList();
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

        return bookInCollections;
    }

    public void save(BookModel bookModel) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(bookModel);
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
