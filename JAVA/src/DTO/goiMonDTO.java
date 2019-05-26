/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class goiMonDTO {
    private String IdBan;
    private String IdThucDon;
    private String TenThucDon;
    private Float DonGiaTon;
    private int SoLuong;
    private Date ThoiGian;
    private Float ThanhTien;

    public goiMonDTO() {
    }

    public goiMonDTO(String IdBan, String IdThucDon, String TenThucDon, Float DonGiaTon, int SoLuong, Date ThoiGian, Float ThanhTien) {
        this.IdBan = IdBan;
        this.IdThucDon = IdThucDon;
        this.TenThucDon = TenThucDon;
        this.DonGiaTon = DonGiaTon;
        this.SoLuong = SoLuong;
        this.ThoiGian = ThoiGian;
        this.ThanhTien = ThanhTien;
    }

    public String getIdBan() {
        return IdBan;
    }

    public void setIdBan(String IdBan) {
        this.IdBan = IdBan;
    }

    public String getIdThucDon() {
        return IdThucDon;
    }

    public void setIdThucDon(String IdThucDon) {
        this.IdThucDon = IdThucDon;
    }

    public String getTenThucDon() {
        return TenThucDon;
    }

    public void setTenThucDon(String TenThucDon) {
        this.TenThucDon = TenThucDon;
    }

    public Float getDonGiaTon() {
        return DonGiaTon;
    }

    public void setDonGiaTon(Float DonGiaTon) {
        this.DonGiaTon = DonGiaTon;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public Date getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(Date ThoiGian) {
        this.ThoiGian = ThoiGian;
    }

    public Float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(Float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
}
