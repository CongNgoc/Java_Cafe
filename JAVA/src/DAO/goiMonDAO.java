/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.goiMonDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class goiMonDAO {
    public static ArrayList<goiMonDTO> goiMonIbBan(String id) throws ClassNotFoundException{
        ArrayList<goiMonDTO> goiMonList = new ArrayList<>();
        dBConnection db = new dBConnection();
        String sql = "SELECT * FROM `goimon` WHERE IdBan ='" + id + "'";
        try {
            db.open();
            ResultSet rs = db.excuteQuery(sql);
            goiMonDTO goiMon;
            while (rs.next()) {
                goiMon = new goiMonDTO(rs.getString("IdBan"), rs.getString("IdThucDon"), rs.getString("TenThucDon"),
                        rs.getFloat("DonGiaTon"), rs.getInt("SoLuong"), rs.getDate("ThoiGian"), rs.getFloat("ThanhTien"));
                goiMonList.add(goiMon);
            }
        } catch (SQLException e) {
        }
        db.close();
        return goiMonList;
    }
}
