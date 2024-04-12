package fpt.poly.app.entities;

import jakarta.persistence.*;

/*
    Một class khi  có Entity thì bắt buộc phải có 1 khóa chính
    @Id
 */

@Entity
@Table(name = "MauSac")
public class MauSac {

    @Id // khai báo khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id tự tăng dần
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "TrangThai")
    private Integer trangThai;

    public MauSac() {
    }

    public MauSac(Integer id, String ma, String ten, Integer trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
}
