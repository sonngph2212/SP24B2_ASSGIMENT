package fpt.poly.app.repositories;

import fpt.poly.app.entities.HoaDonChiTiet;
import fpt.poly.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HoaDonCtRepository {

    private Session session;

    public HoaDonCtRepository() {
        session = HibernateUtil.getFACTORY().openSession();
    }

    public List<HoaDonChiTiet> getAll() {
        return session.createQuery("FROM HoaDonChiTiet").getResultList();
    }

    public HoaDonChiTiet findById(int id) {
        return session.find(HoaDonChiTiet.class, id);
    }

    public void create(HoaDonChiTiet objInput) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.persist(objInput);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void update(HoaDonChiTiet objInput) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.merge(objInput);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void delete(HoaDonChiTiet objInput) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.remove(objInput);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

}
