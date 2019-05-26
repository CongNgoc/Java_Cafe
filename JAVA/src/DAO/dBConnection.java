/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class dBConnection {

    public Connection conn = null;
    private final String nameDB = "ql_caffe";

    private final String url = "jdbc:mysql://localhost:3306/" + nameDB + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
    private final String user = "root";
    private final String passwork = "";

    public void displayError(SQLException ex) {
        System.out.println(" Error Message:" + ex.getMessage());
        System.out.println(" SQL State:" + ex.getSQLState());
        System.out.println(" Error Code:" + ex.getErrorCode());
    }

    public void open() throws ClassNotFoundException {// mo ket noi den csdl
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, passwork);//tao ket noi den co so
        } catch (SQLException ex) {// xu ly ngoai le
            displayError(ex);
        }
    }

    public void close() {// dong ket noi co so du lieu
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            displayError(ex);
        }
    }

    public ResultSet excuteQuery(String sql) {// danh cho cau lenh secect
        ResultSet rs = null;
        try {
            Statement stm = (Statement) conn.createStatement();
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            displayError(ex);
        }
        return rs;
    }

    public int excuteUpdate(String ssql) throws SQLException {
        int rs = 0;
        try {
            Statement stm = (Statement) conn.createStatement();
            rs = stm.executeUpdate(ssql);
        } catch (SQLException ex) {
            displayError(ex);
        }
        return rs;
    }


}
