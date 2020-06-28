/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.UserDAO;
import DTO.User;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author WindZ
 */
public class UserBUS {
    private ArrayList<User> dsUS;
    public UserBUS() {
        
    }
    public void list() {
        UserDAO usDAO = new UserDAO();
        dsUS= new ArrayList<>();
        dsUS= usDAO.list();
    }

    public void add(User hd,int i) {
        UserDAO usDAO = new UserDAO();
        Encryptor enc = new Encryptor();
        // mã hóa mật khẩu
        hd.setPass(enc.encryptPassword(hd.getPass()));
        usDAO.add(hd);
    }
    
    public void delete(String userID) {
        for(User hd : dsUS) {
            if(hd.getUserID().equals(userID)) {
                dsUS.remove(hd);
                UserDAO usDAO = new UserDAO();
                usDAO.delete(userID);
                return;
            }
        }
    }
    
    public void set(User s) {
        for(int i = 0 ; i < dsUS.size() ; i++) {
            if(dsUS.get(i).getUserID().equals(s.getUserID())) {
                dsUS.set(i, s);
                UserDAO usDAO = new UserDAO();
                usDAO.set(s);
                return;
            }
        }
    }
    
    public User check(String userName,char[] pass) {
        for(User us : dsUS) {   
            if( us.getUserName().equals(userName)){
                Decryptor dnc = new Decryptor();
                char[] correctPass = dnc.decryptPassword(us.getPass()).toCharArray();
                if (Arrays.equals(pass, correctPass) && us.getEnable().equals("1")){
                    return us;
                }
            }
                
        }
        return null;
    }
    public ArrayList<User> getList() {
        return dsUS;
    }

    public void delete(String text, int i) {
        list();
        delete(text);
    }
}
