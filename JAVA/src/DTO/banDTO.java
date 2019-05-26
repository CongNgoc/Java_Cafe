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
public class banDTO {
    private String IdBan;
    private String TenKhuVuc;
    private String TenBan;
    private String DienGiai;
    private String TrangThai;

    public banDTO() {
    }

    public banDTO(String IdBan, String TenKhuVuc, String TenBan, String DienGiai, String TrangThai) {
        this.IdBan = IdBan;
        this.TenKhuVuc = TenKhuVuc;
        this.TenBan = TenBan;
        this.DienGiai = DienGiai;
        this.TrangThai = TrangThai;
    }

    public String getIdBan() {
        return IdBan;
    }

    public void setIdBan(String IdBan) {
        this.IdBan = IdBan;
    }

    public String getTenKhuVuc() {
        return TenKhuVuc;
    }

    public void setTenKhuVuc(String TenKhuVuc) {
        this.TenKhuVuc = TenKhuVuc;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String TenBan) {
        this.TenBan = TenBan;
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
