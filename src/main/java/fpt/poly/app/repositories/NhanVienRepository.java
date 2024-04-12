package fpt.poly.app.repositories;

import fpt.poly.app.entities.NhanVien;
import fpt.poly.app.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class NhanVienRepository {

    private Session session;

    public NhanVienRepository() {
        session = HibernateUtil.getFACTORY().openSession();
    }

    public List<NhanVien> getAll() {
        return session.createQuery("FROM NhanVien").getResultList();
    }
}
