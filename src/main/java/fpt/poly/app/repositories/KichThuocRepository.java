package fpt.poly.app.repositories;

import fpt.poly.app.entities.KichThuoc;
import fpt.poly.app.entities.MauSac;
import fpt.poly.app.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class KichThuocRepository {

    private Session session;

    public KichThuocRepository() {
        session = HibernateUtil.getFACTORY().openSession();
    }

    public List<MauSac> getAll() {
        return session.createQuery("FROM MauSac").getResultList();
    }
}
