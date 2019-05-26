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
public class thucDonDTO {
    private String IdThucDon;
    private String TenLoaiThucDon;
    private String TenThucDon;
    private String DonViTinh;
    private float SoLuongTon;
    private float DonGiaTon;
    private float TonToiThieu;
    private String TrangThai;

    public thucDonDTO() {
    }

    public thucDonDTO(String IdThucDon, String TenLoaiThucDon, String TenThucDon, String DonViTinh, float SoLuongTon, float DonGiaTon, float TonToiThieu, String TrangThai) {
        this.IdThucDon = IdThucDon;
        this.TenLoaiThucDon = TenLoaiThucDon;
        this.TenThucDon = TenThucDon;
        this.DonViTinh = DonViTinh;
        this.SoLuongTon = SoLuongTon;
        this.DonGiaTon = DonGiaTon;
        this.TonToiThieu = TonToiThieu;
        this.TrangThai = TrangThai;
    }

    public String getIdThucDon() {
        return IdThucDon;
    }

    public void setIdThucDon(String IdThucDon) {
        this.IdThucDon = IdThucDon;
    }

    public String getTenLoaiThucDon() {
        return TenLoaiThucDon;
    }

    public void setTenLoaiThucDon(String TenLoaiThucDon) {
        this.TenLoaiThucDon = TenLoaiThucDon;
    }

    public String getTenThucDon() {
        return TenThucDon;
    }

    public void setTenThucDon(String TenThucDon) {
        this.TenThucDon = TenThucDon;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String DonViTinh) {
        this.DonViTinh = DonViTinh;
    }

    public float getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(float SoLuongTon) {
        this.SoLuongTon = SoLuongTon;
    }

    public float getDonGiaTon() {
        return DonGiaTon;
    }

    public void setDonGiaTon(float DonGiaTon) {
        this.DonGiaTon = DonGiaTon;
    }

    public float getTonToiThieu() {
        return TonToiThieu;
    }

    public void setTonToiThieu(float TonToiThieu) {
        this.TonToiThieu = TonToiThieu;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
}
