/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DAL.MySQLConnect;
import DAL.NhanVienDAO;
import DTO.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WindZ
 */
public class UserDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public UserDAO()
    {
        
    }
    public ArrayList<User> list()
    {
        ArrayList<User> dsnv = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM user WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String userID = rs.getString("userID");
                String username = rs.getString("username");
                String pass = rs.getString("pass");
                String role = rs.getString("role");
                String enable= rs.getString("enable");
                
                User us= new User(userID, username, pass, role, enable);
                dsnv.add(us);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsnv;
    }

    public void set(User us) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE user SET ";
            sql += "username='"+us.getUserName()+"', ";
            sql += "password='"+us.getPass()+"', ";
            sql += "role='"+us.getRole()+"', ";
            sql += "enable='"+us.getEnable()+"' ";
            sql += " WHERE userID='"+us.getUserID()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(User us) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO user VALUES (";
               sql += "'"+us.getUserID()+"',";
               sql += "'"+us.getUserName()+"',";
               sql += "'"+us.getPass()+"',";
               sql += "'"+us.getRole()+"',";
               sql += "'"+us.getEnable()+"')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String userID)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM user WHERE userID='"+userID+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
