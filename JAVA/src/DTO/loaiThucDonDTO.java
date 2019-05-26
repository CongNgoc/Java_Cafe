/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Admin
 */
public class loaiThucDonDTO {
    private String IdLoaiThucDon;
    private String TenLoaiThucDon;
    private String DienGiai;
    private String TrangThai;

    public loaiThucDonDTO() {
    }

    public loaiThucDonDTO(String IdLoaiThucDon, String TenLoaiThucDon, String DienGiai, String TrangThai) {
        this.IdLoaiThucDon = IdLoaiThucDon;
        this.TenLoaiThucDon = TenLoaiThucDon;
        this.DienGiai = DienGiai;
        this.TrangThai = TrangThai;
    }

    public String getIdLoaiThucDon() {
        return IdLoaiThucDon;
    }

    public void setIdLoaiThucDon(String IdLoaiThucDon) {
        this.IdLoaiThucDon = IdLoaiThucDon;
    }

    public String getTenLoaiThucDon() {
        return TenLoaiThucDon;
    }

    public void setTenLoaiThucDon(String TenLoaiThucDon) {
        this.TenLoaiThucDon = TenLoaiThucDon;
    }

    public String getDienGiai() {
        return DienGiai;
    }

    public void setDienGiai(String DienGiai) {
        this.DienGiai = DienGiai;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
}
