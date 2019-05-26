/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.banDAO;
import DTO.banDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class banBUS {
    public static ArrayList<banDTO> banAll() throws ClassNotFoundException{
        return banDAO.banAll();
    }
    public static ArrayList<banDTO> banSearch(String tenBan, String idBan) throws ClassNotFoundException{
        return banDAO.banSearch(tenBan, idBan);
    }
    public static ArrayList<banDTO> banSearchHome(String tenKhuVuc) throws ClassNotFoundException{
        return banDAO.banSearchHome(tenKhuVuc);
    }
}
