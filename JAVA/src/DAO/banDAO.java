/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.banDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class banDAO {
    
    public static ArrayList<banDTO> banAll() throws ClassNotFoundException {
        ArrayList<banDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM ban";
        dBConnection db = new dBConnection();
        try {
            db.open();
            banDTO ban;
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                ban = new banDTO(rs.getString("IdBan"), rs.getString("TenKhuVuc"), rs.getString("TenBan"), rs.getString("DienGiai"), rs.getString("TrangThai"));
                list.add(ban);
            }
        } catch (SQLException e) {
            db.displayError(e);
        }

        return list;
    }
//    
    public static ArrayList<banDTO> banSearch(String tenBan, String idBan) throws ClassNotFoundException{
         ArrayList<banDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM ban WHERE TenBan LIKE '%" + tenBan + "%' OR IdBan LIKE '%" + idBan + "%'";
        dBConnection db = new dBConnection();
        try {
            db.open();
            banDTO ban;
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                ban = new banDTO(rs.getString("IdBan"), rs.getString("TenKhuVuc"), rs.getString("TenBan"), rs.getString("DienGiai"), rs.getString("TrangThai"));
                list.add(ban);
            }
        } catch (SQLException e) {
            db.displayError(e);
        }

        return list;
    }
    public static ArrayList<banDTO> banSearchHome(String TenKhuVuc) throws ClassNotFoundException{
         ArrayList<banDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM ban WHERE TenKhuVuc = '" + TenKhuVuc + "'";
        dBConnection db = new dBConnection();
        try {
            db.open();
            banDTO ban;
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                ban = new banDTO(rs.getString("IdBan"), rs.getString("TenKhuVuc"), rs.getString("TenBan"), rs.getString("DienGiai"), rs.getString("TrangThai"));
                list.add(ban);
            }
        } catch (SQLException e) {
            db.displayError(e);
        }

        return list;
    }
}
