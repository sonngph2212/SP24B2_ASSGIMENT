package fpt.poly.app.repositories;

import fpt.poly.app.entities.HoaDon;
import fpt.poly.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HoaDonRepository {

    private Session session;

    public HoaDonRepository() {
        session = HibernateUtil.getFACTORY().openSession();
    }

    public List<HoaDon> getAll() {
        return session.createQuery("FROM HoaDon", HoaDon.class).getResultList();
    }

    public void create(HoaDon objInput) {
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

    public void update(HoaDon objInput) {
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

    public void remove(HoaDon objInput) {
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

    public HoaDon findById(int id) {
        return session.find(HoaDon.class, id);
    }
}
