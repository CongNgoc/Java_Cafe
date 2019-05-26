/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.loaiThucDonDAO;
import DTO.loaiThucDonDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class loaiThucDonBUS {
    public static ArrayList<loaiThucDonDTO> thucDonAll() throws ClassNotFoundException {
        return loaiThucDonDAO.loaiThucDonAll();
    }
    public static ArrayList<loaiThucDonDTO> thucDonSearch(String ten, String id) throws ClassNotFoundException {
        return loaiThucDonDAO.loaiThucDonID(ten, id);
    }
}
