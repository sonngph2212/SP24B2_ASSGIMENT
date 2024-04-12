package fpt.poly.app.repositories;

import fpt.poly.app.entities.MauSac;
import fpt.poly.app.utils.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MauSacRepository {

    private Session hSession = HibernateUtil.getFACTORY().openSession();

    public List<MauSac> findAll() {
        String sqlStr = "Select ms from MauSac ms";
        Query query = hSession.createQuery(sqlStr, MauSac.class);
        return query.getResultList();
    }

    public void createMauSac(MauSac objInput) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            hSession.persist(objInput);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void updateMauSac(MauSac objInput) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            hSession.merge(objInput);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void delete(MauSac objInput) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            hSession.remove(objInput);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public MauSac finById(int id) {
        return hSession.find(MauSac.class, id);
    }
}
