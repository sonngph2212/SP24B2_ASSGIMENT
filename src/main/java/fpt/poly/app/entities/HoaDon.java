package fpt.poly.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "HoaDon")
@AllArgsConstructor
@Data
public class HoaDon {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "IdKH")
    private Integer idKh;

    @Column(name = "IdNV")
    private Integer idNv;

    @Column(name = "NgayMuaHang")
    private Date ngayMuaHang;

    @Column(name = "TrangThai")
    private Integer trangThai;

    public HoaDon() {
    }
}
