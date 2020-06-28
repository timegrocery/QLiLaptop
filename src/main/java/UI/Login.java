/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BUS.InfoBalloon;
import BUS.NhanVienBUS;
import BUS.UserBUS;
import DTO.NhanVien;
import DTO.User;
import UI.bone.header;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import keeptoo.KGradientPanel;
import keeptoo.KButton;

/**
 *
 * @author WindZ
 */

public class Login extends JFrame implements KeyListener {
    private UserBUS usBUS = new UserBUS();
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private JTextField user;
    private JPasswordField pass;
    private int DEFAULT_HEIGHT = 450 ,DEFAULT_WIDTH = 375;
    private KButton btnLogin;
    public Login()
    {
        init();
    }
    public void init()
    {
        
        setLayout(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        header hd = new header(300, 60, "ĐĂNG NHẬP");
        hd.setBackground(new Color(15, 16, 18, 15));
        hd.setBounds(50,80,300,60);
        
        Font font = new Font("Segoe UI",Font.PLAIN,14);
        Font font1 = new Font("Segoe UI",Font.BOLD,15);
        
        KGradientPanel background = new KGradientPanel();
        background.setLayout(null);
        background.setkStartColor(new Color(40, 77, 107));
        background.setkEndColor(new Color(79, 154, 214));
        background.setkGradientFocus(300);
        background.setBounds(0,0,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        
        btnLogin = new KButton();
        btnLogin.setBorder(null);
        btnLogin.setOpaque(false);
        btnLogin.setText("Đăng nhập");
        btnLogin.setFont(font1);
        btnLogin.setkStartColor(new Color(24, 67, 82));
        btnLogin.setkEndColor(new Color(33, 90, 110));
        btnLogin.setkHoverForeGround(Color.WHITE);
        btnLogin.setkHoverStartColor(new Color(8, 39, 64));
        btnLogin.setkHoverEndColor(new Color(13, 68, 112));
        btnLogin.setkBorderRadius(20);
        btnLogin.setkSelectedColor(null);
        btnLogin.setkAllowTab(false);
        btnLogin.setBounds(85, 350, 200, 40);
        
        JLabel exit = new JLabel(new ImageIcon("./src/main/java/image/exit_25px.png"),JLabel.CENTER);
        exit.setBounds(DEFAULT_WIDTH - 40, 10, 30, 30);
        exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel lbUser = new JLabel(new ImageIcon("./src/main/java/image/user_30px.png"),JLabel.CENTER);
        lbUser.setBounds(80, 157, 30, 30);
        JLabel lbPass = new JLabel(new ImageIcon("./src/main/java/image/pwd_30px.png"),JLabel.CENTER);
        lbPass.setBounds(80, 237, 30, 30);
        
        user = new JTextField("Username");
        user.setFont(font);
        user.setForeground(Color.WHITE);
        user.setCaretColor(Color.WHITE);
        user.setBounds(120,160,180,30);
        user.setBorder(null);
        user.setOpaque(false);
        new InfoBalloon(InfoBalloon.errTxt_invalidName, user, InfoBalloon.filter_all, InfoBalloon.limit_name);
        
        pass = new JPasswordField("Password");
        pass.setFont(font);
        pass.setForeground(Color.WHITE);
        pass.setCaretColor(Color.WHITE);
        pass.setBorder(null);
        pass.setBounds(120,240,150,30);
        pass.setOpaque(false);    
        new InfoBalloon(InfoBalloon.errTxt_invalidName, pass, InfoBalloon.filter_all, InfoBalloon.limit_name);
        
        JSeparator sp1 = new JSeparator();
        sp1.setBounds(80,190,220,10);
        
        JSeparator sp2 = new JSeparator();
        sp2.setBounds(80,270,220,10);

        setUndecorated(true);
        background.add(exit);
        background.add(hd);
        background.add(lbUser);
        background.add(user);
        background.add(sp1);
        background.add(lbPass);
        background.add(pass);
        background.add(sp2);
        background.add(btnLogin);
        add(background);
       
        
        
        
        btnLogin.requestFocus();
        
        setSize(DEFAULT_WIDTH ,DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
         
        
        exit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                System.exit(0);
            }
        });
        
        user.addMouseListener((new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                user.setText("");
            }
        }));
        user.addKeyListener(this);
        
        pass.addMouseListener((new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                pass.setText("");
            }
        }));
        pass.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e)
            {
                pass.setText("");
            }
        });
        pass.addKeyListener(this);
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usBUS.getList()== null)usBUS.list();
                String username = user.getText();
                char[] passwd = pass.getPassword();
                User user = usBUS.check(username, passwd);
                if(user == null){
                    JOptionPane.showMessageDialog(null, "Sai tên tài khoản hoặc mật khẩu");
                    return;
                }
                if(nvBUS.getList() == null)nvBUS.listNV();
                NhanVien nv = new NhanVien();
                nv = nvBUS.get(user.getUserID());
                QLiLaptop qllt = new QLiLaptop(nv.getMaNV(),nv.getHoNV().concat(" "+nv.getTenNV()),user.getRole());
                dispose();
            }
        });
    }
   
    public static void main(String[]args)
    {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e)
        {
            
        }
        Login lg = new Login();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object a = e.getSource();
        if(a.equals(user) || a.equals(pass))
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                btnLogin.doClick();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
