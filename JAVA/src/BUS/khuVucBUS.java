/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.khuVucDAO;
import DTO.khuVucDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class khuVucBUS {
    public static ArrayList<khuVucDTO> khuVucAll() throws ClassNotFoundException {
        return khuVucDAO.khuVucAll();
    }
    public static ArrayList<khuVucDTO> khuVucSearch(String ten, String id) throws ClassNotFoundException {
        return khuVucDAO.khuVucID(ten, id);
    }
}
