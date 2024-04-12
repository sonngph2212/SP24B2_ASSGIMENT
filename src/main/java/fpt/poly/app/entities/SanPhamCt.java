package fpt.poly.app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "SanPhamChiTiet")
public class SanPhamCt {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "MaSPCT")
    private String maSanPhamCt;

    @Column(name = "IdKichThuoc")
    private Integer idKichThuoc;

    @Column(name = "IdMauSac")
    private Integer idMauSac;

    @Column(name = "IdSanPham")
    private Integer idSanPham;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "DonGia")
    private Float donGia;

    @Column(name = "TrangThai")
    private Integer trangThai;

    public SanPhamCt() {
    }

    public SanPhamCt(Integer id, String maSanPhamCt, Integer idKichThuoc, Integer idMauSac, Integer idSanPham, Integer soLuong, Float donGia, Integer trangThai) {
        this.id = id;
        this.maSanPhamCt = maSanPhamCt;
        this.idKichThuoc = idKichThuoc;
        this.idMauSac = idMauSac;
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaSanPhamCt() {
        return maSanPhamCt;
    }

    public void setMaSanPhamCt(String maSanPhamCt) {
        this.maSanPhamCt = maSanPhamCt;
    }

    public Integer getIdKichThuoc() {
        return idKichThuoc;
    }

    public void setIdKichThuoc(Integer idKichThuoc) {
        this.idKichThuoc = idKichThuoc;
    }

    public Integer getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(Integer idMauSac) {
        this.idMauSac = idMauSac;
    }

    public Integer getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Integer idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Float getDonGia() {
        return donGia;
    }

    public void setDonGia(Float donGia) {
        this.donGia = donGia;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
}
