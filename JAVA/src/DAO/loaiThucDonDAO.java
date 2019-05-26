/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.khuVucDTO;
import DTO.loaiThucDonDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class loaiThucDonDAO {
    public static ArrayList<loaiThucDonDTO> loaiThucDonAll() throws ClassNotFoundException{
        ArrayList<loaiThucDonDTO> list = new ArrayList<>();
        String sql = "select * from loaithucdon";
        dBConnection db = new dBConnection();
        try {
            db.open();
            loaiThucDonDTO loaiThucDonDTO;
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                loaiThucDonDTO = new loaiThucDonDTO(rs.getString("IdLoaiThucDon"), rs.getString("TenLoaiThucDon"), rs.getString("DienGiai"), rs.getString("TrangThai"));
                list.add(loaiThucDonDTO);
            }
        } catch (SQLException e) {
        }
        return list;
        
    }
     public static ArrayList<loaiThucDonDTO> loaiThucDonID(String ten, String id) throws ClassNotFoundException{
        ArrayList<loaiThucDonDTO> list = new ArrayList<>();
        String sql = "select * from loaithucdon WHERE TenLoaiThucDon LIKE '%" + ten + "%' OR IdLoaiThucDon LIKE '%" + id + "%'";
        dBConnection db = new dBConnection();
        try {
            db.open();
            loaiThucDonDTO loaiThucDonDTO;
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                loaiThucDonDTO = new loaiThucDonDTO(rs.getString("IdLoaiThucDon"), rs.getString("TenLoaiThucDon"), rs.getString("DienGiai"), rs.getString("TrangThai"));
                list.add(loaiThucDonDTO);
            }
        } catch (SQLException e) {
        }
        return list;
        
    }
}
