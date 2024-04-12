package fpt.poly.app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTiet {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "IdHoaDon")
    private Integer idHoaDon;

    @Column(name = "IdSPCT")
    private Integer idSanPhamCt;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "DonGia")
    private Float donGia;

    @Column(name = "TrangThai")
    private Integer trangThai;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(Integer id, Integer idHoaDon, Integer idSanPhamCt, Integer soLuong, Float donGia, Integer trangThai) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.idSanPhamCt = idSanPhamCt;
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

    public Integer getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(Integer idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public Integer getIdSanPhamCt() {
        return idSanPhamCt;
    }

    public void setIdSanPhamCt(Integer idSanPhamCt) {
        this.idSanPhamCt = idSanPhamCt;
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
