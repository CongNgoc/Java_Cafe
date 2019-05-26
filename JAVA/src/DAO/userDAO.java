/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.userDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class userDAO {
    public static ArrayList<userDTO> userAll(){
        ArrayList<userDTO> list = new ArrayList<>();
        dBConnection db = new dBConnection();
        try {
            String sql = "SELECT * FROM user ";
            db.open();
            ResultSet rs = db.excuteQuery(sql);
            userDTO user;
            while (rs.next()) {
                user = new userDTO(rs.getString("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("phone"),
                        rs.getString("email"), rs.getString("address"), rs.getString("gioitinh"), rs.getString("chucvu"));
                list.add(user);
            }
        } catch (SQLException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static ArrayList<userDTO> userSearch(String ten, String username){
        ArrayList<userDTO> list = new ArrayList<>();
        dBConnection db = new dBConnection();
        try {
            String sql = "SELECT * FROM user WHERE name LIKE '%" + ten + "%' OR username LIKE '%" + username + "%'";
            db.open();
            ResultSet rs = db.excuteQuery(sql);
            userDTO user;
            while (rs.next()) {
                user = new userDTO(rs.getString("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("phone"),
                        rs.getString("email"), rs.getString("address"), rs.getString("gioitinh"), rs.getString("chucvu"));
                list.add(user);
            }
        } catch (SQLException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static ArrayList<userDTO> userId(String id){
        ArrayList<userDTO> list = new ArrayList<>();
        dBConnection db = new dBConnection();
        try {
            String sql = "SELECT * FROM user WHERE id = '"+id+"'";
            db.open();
            ResultSet rs = db.excuteQuery(sql);
            userDTO user;
            while (rs.next()) {
                user = new userDTO(rs.getString("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("phone"),
                        rs.getString("email"), rs.getString("address"), rs.getString("gioitinh"), rs.getString("chucvu"));
                list.add(user);
            }
        } catch (SQLException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
