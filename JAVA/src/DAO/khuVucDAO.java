/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.khuVucDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class khuVucDAO {

    public static ArrayList<khuVucDTO> khuVucAll() throws ClassNotFoundException {
        ArrayList<khuVucDTO> khuvucList = new ArrayList<>();
        String sql = "select * from khuvuc";
        dBConnection db = new dBConnection();
        try {
            db.open();
            khuVucDTO khuVucDTO;
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                khuVucDTO = new khuVucDTO(rs.getString("IdKhuVuc"), rs.getString("TenKhuVuc"), rs.getString("DienGiai"), rs.getString("TrangThai"));
                khuvucList.add(khuVucDTO);
            }
        } catch (SQLException e) {
            db.displayError(e);
        } finally {
            db.close();
        }
        return khuvucList;
    }
    public static ArrayList<khuVucDTO> khuVucID(String ten, String id) throws ClassNotFoundException {
        ArrayList<khuVucDTO> khuvucList = new ArrayList<>();
        String sql = "select * from khuvuc WHERE TenKhuVuc LIKE '%" + ten + "%' OR IdKhuVuc LIKE '%" + id + "%'";
        dBConnection db = new dBConnection();
        try {
            db.open();
            khuVucDTO khuVucDTO;
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                khuVucDTO = new khuVucDTO(rs.getString("IdKhuVuc"), rs.getString("TenKhuVuc"), rs.getString("DienGiai"), rs.getString("TrangThai"));
                khuvucList.add(khuVucDTO);
            }
        } catch (SQLException e) {
            db.displayError(e);
        } finally {
            db.close();
        }
        return khuvucList;
    }
}
