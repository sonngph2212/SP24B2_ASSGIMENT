package fpt.poly.app.repositories;

import fpt.poly.app.entities.NhanVien;
import fpt.poly.app.entities.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamRepository {

    private List<SanPham> list = new ArrayList<>();;

    public SanPhamRepository() {
        list.add(new SanPham(1, "sanPham1", "PC", 1));
        list.add(new SanPham(1, "sanPham1", "PC", 1));
        list.add(new SanPham(1, "sanPham1", "PC", 1));
    }

    public List<SanPham> getList() {
        return list;
    }
}
