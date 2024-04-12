package fpt.poly.app.repositories;

import fpt.poly.app.entities.KhachHang;
import fpt.poly.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class KhachHangRepository {

    private Session session;

    public KhachHangRepository() {
        session = HibernateUtil.getFACTORY().openSession();
    }

    public List<KhachHang> getAll() {
        return session.createQuery("from KhachHang").getResultList();
    }

    public void create(KhachHang objInput) {
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

    public void update(KhachHang objInput) {
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

    public void remove(KhachHang objInput) {
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

    public KhachHang findById(int id) {
        return session.find(KhachHang.class, id);
    }

}
