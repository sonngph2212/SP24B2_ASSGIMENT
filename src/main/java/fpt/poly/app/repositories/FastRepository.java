package fpt.poly.app.repositories;

import fpt.poly.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FastRepository<T> {
    private final Session session;

    public FastRepository() {
        session = HibernateUtil.getFACTORY().openSession();
    }

    public List<T> getAll(Class<T> type) {
        return session.createQuery("FROM " + type.getSimpleName(), type).getResultList();
    }

    public void create(T objInput) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.persist(objInput);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(T objInput) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.merge(objInput);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void remove(T objInput) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.remove(objInput);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public T findById(Class<T> type, int id) {
        return session.find(type, id);
    }
}
