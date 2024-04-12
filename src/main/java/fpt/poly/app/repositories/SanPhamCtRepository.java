package fpt.poly.app.repositories;

import fpt.poly.app.entities.SanPhamCt;
import fpt.poly.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SanPhamCtRepository {

    private Session session;

    public SanPhamCtRepository() {
       session = HibernateUtil.getFACTORY().openSession();
    }

    public List<SanPhamCt> getAll() {
        return session.createQuery("FROM SanPhamCt").getResultList();
    }

    public SanPhamCt findById(int id) {
        return session.find(SanPhamCt.class, id);
    }

    public void create(SanPhamCt objInput) {
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

    public void update(SanPhamCt objInput) {
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

    public void delete(SanPhamCt objInput) {
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
