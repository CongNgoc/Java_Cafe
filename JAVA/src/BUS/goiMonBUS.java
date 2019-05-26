/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.goiMonDAO;
import DTO.goiMonDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class goiMonBUS {
    public static ArrayList<goiMonDTO> goiMonIdBan(String id) throws ClassNotFoundException{
        return goiMonDAO.goiMonIbBan(id);
    }
}
