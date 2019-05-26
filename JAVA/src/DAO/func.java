/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class func {

    dBConnection db = new dBConnection();

    public String MaTuDong(String id, String table, String a) throws SQLException, ClassNotFoundException {
        String ID_OUT;
        int Max;
        String sql = "SELECT " + id + " FROM `" + table + "` ORDER BY `" + id + "` DESC LIMIT 1";
        db.open();
        ResultSet rs = db.excuteQuery(sql);
        if (rs.next()) {
            String idnv = rs.getString(id);
            int leng = idnv.length();
            int subc;
            subc = (a.equals("BAN") || a.equals("LTD")) ? 3 : 2;
            String subID = idnv.substring(subc, leng);
            Max = Integer.parseInt(subID) + 1;
        } else {
            Max = 1;
        }
        if (Max < 10) {
            ID_OUT = a + "0000" + String.valueOf(Max);
        } else if (Max < 100) {
            ID_OUT = a + "000" + String.valueOf(Max);
        } else if (Max < 1000) {
            ID_OUT = a + "00" + String.valueOf(Max);
        } else if (Max < 10000) {
            ID_OUT = a + "0" + String.valueOf(Max);
        } else {
            ID_OUT = a + String.valueOf(Max);
        }

        return ID_OUT;
    }
}
