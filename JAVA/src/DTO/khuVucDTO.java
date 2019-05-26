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
public class khuVucDTO {
    private String IdKhuVuc;
    private String TenKhuVuc;
    private String DienGiai;
    private String TrangThai;

    public khuVucDTO() {
    }

    public khuVucDTO(String IdKhuVuc, String TenKhuVuc, String DienGiai, String TrangThai) {
        this.IdKhuVuc = IdKhuVuc;
        this.TenKhuVuc = TenKhuVuc;
        this.DienGiai = DienGiai;
        this.TrangThai = TrangThai;
    }

    public String getIdKhuVuc() {
        return IdKhuVuc;
    }

    public void setIdKhuVuc(String IdKhuVuc) {
        this.IdKhuVuc = IdKhuVuc;
    }

    public String getTenKhuVuc() {
        return TenKhuVuc;
    }

    public void setTenKhuVuc(String TenKhuVuc) {
        this.TenKhuVuc = TenKhuVuc;
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
