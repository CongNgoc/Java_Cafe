/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.userDAO;
import DTO.userDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class userBUS {
    public static ArrayList<userDTO> userAll() throws ClassNotFoundException{
        return userDAO.userAll();
    }
    public static ArrayList<userDTO> userSearch(String ten, String userName) throws ClassNotFoundException{
        return userDAO.userSearch(ten, userName);
    }
    public static ArrayList<userDTO> userId(String id) throws ClassNotFoundException{
        return userDAO.userId(id);
    }
}
