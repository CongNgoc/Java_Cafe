/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.loaiThucDonDTO;
import DTO.thucDonDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class thucDonDAO {
    public static ArrayList<thucDonDTO> thucDonAll() throws ClassNotFoundException{
        ArrayList<thucDonDTO> list = new ArrayList<>();
        String sql = "select * from thucdon";
        dBConnection db = new dBConnection();
        try {
            db.open();
            thucDonDTO thucDon;
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                thucDon = new thucDonDTO(rs.getString("IdThucDon"), rs.getString("TenLoaiThucDon"),
                        rs.getString("TenThucDon"), rs.getString("DonViTinh"),
                        rs.getFloat("SoLuongTon"), rs.getFloat("DonGiaTon"),
                        rs.getFloat("TonToiThieu"), rs.getString("TrangThai"));
                list.add(thucDon);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    public static ArrayList<thucDonDTO> thucDonSearch(String ten, String id) throws ClassNotFoundException{
        ArrayList<thucDonDTO> list = new ArrayList<>();
        String sql = "select * from thucdon WHERE TenThucDon LIKE '%" + ten + "%' OR IdThucDon LIKE '%" + id + "%'"; 
        dBConnection db = new dBConnection();
        try {
            db.open();
            thucDonDTO thucDon;
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                thucDon = new thucDonDTO(rs.getString("IdThucDon"), rs.getString("TenLoaiThucDon"),
                        rs.getString("TenThucDon"), rs.getString("DonViTinh"),
                        rs.getFloat("SoLuongTon"), rs.getFloat("DonGiaTon"),
                        rs.getFloat("TonToiThieu"), rs.getString("TrangThai"));
                list.add(thucDon);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    public static ArrayList<thucDonDTO> thucDonSearchTen(String ten) throws ClassNotFoundException{
        ArrayList<thucDonDTO> list = new ArrayList<>();
        String sql = "select * from thucdon WHERE TenThucDon LIKE '%" + ten + "%'"; 
        dBConnection db = new dBConnection();
        try {
            db.open();
            thucDonDTO thucDon;
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                thucDon = new thucDonDTO(rs.getString("IdThucDon"), rs.getString("TenLoaiThucDon"),
                        rs.getString("TenThucDon"), rs.getString("DonViTinh"),
                        rs.getFloat("SoLuongTon"), rs.getFloat("DonGiaTon"),
                        rs.getFloat("TonToiThieu"), rs.getString("TrangThai"));
                list.add(thucDon);
            }
        } catch (SQLException e) {
        }
        return list;
    }
}
