package fpt.poly.app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "KhachHang")
public class KhachHang {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "SDT")
    private String soDienThoai;

    @Column(name = "Ma")
    private String maKhachHang;

    @Column(name = "TrangThai")
    private Integer trangThai;

    public KhachHang() {
    }

    public KhachHang(Integer id, String ten, String soDienThoai, String maKhachHang, Integer trangThai) {
        this.id = id;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.maKhachHang = maKhachHang;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
}
