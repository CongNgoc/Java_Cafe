/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.thucDonDAO;
import DTO.thucDonDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class thucDonBUS {
    public static ArrayList<thucDonDTO> thucDonAll() throws ClassNotFoundException {
        return thucDonDAO.thucDonAll();
    }
    public static ArrayList<thucDonDTO> thucDonSearch(String ten, String id) throws ClassNotFoundException {
        return thucDonDAO.thucDonSearch(ten, id);
    }
    public static ArrayList<thucDonDTO> thucDonSearchTen(String ten) throws ClassNotFoundException {
        return thucDonDAO.thucDonSearchTen(ten);
    }
}
