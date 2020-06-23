/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import UI.bone.header;
import UI.bone.navItem;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author WindZ
 */
public class QLiLaptop extends JFrame implements MouseListener {
    private String userID;
    private String userName;
    private String role;
    private boolean flag = true;
    private JPanel header,nav,main;
    private final int DEFAULT_HEIGHT = 730,DEFALUT_WIDTH = 1300 ;
    private ArrayList<String> navItem = new ArrayList<>();  //Chứa thông tin có button cho menu gồm
    private ArrayList<navItem> navObj = new ArrayList<>();  //Chứa cái button trên thanh menu
    public QLiLaptop(String userID, String userName, String role) {
        this.userID = userID;
        this.userName = userName;
        this.role = role;
        init();
    }
    public QLiLaptop() {
        init();
    }
    public void init() {
        Font font = new Font("Segoe UI",Font.BOLD,14);
        setTitle("Quản lý cửa hàng");
        ImageIcon logo = new ImageIcon("./src/main/java/image/shopicon.png");
        setIconImage(logo.getImage());
        setLayout(new BorderLayout());
        setSize(DEFALUT_WIDTH,DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        
/************ PHẦN HEADER *************************************/      
        header = new JPanel(null);
        header.setBackground(new Color(44, 70, 112));
        header.setPreferredSize(new Dimension(DEFALUT_WIDTH,40));
        
        header hmain = new header(DEFALUT_WIDTH, 40, "Quản lý cửa hàng");
        
        if(userName != null) {
            if(role.equals("Admin")) userName = "Admin";
            JLabel user = new JLabel("Chào, " + userName);
            user.setFont(font);
            user.setForeground(Color.WHITE);
            user.setBounds(new Rectangle(DEFALUT_WIDTH - 300, -7, 150, 50));
            hmain.add(user);
            
            //Tạo btn Logout
            navItem btnLogOut = new navItem("", new Rectangle(DEFALUT_WIDTH-150, -8, 50, 50), "logout_25px.png", "logout_25px.png", "logout_hover_25px.png", new Color(101, 135, 235));
            hmain.add(btnLogOut.isButton());
            btnLogOut.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    Login lg = new Login();
                    dispose();
               }
            });
        }
        
        //Tạo btn EXIT & MINIMIZE
        navItem exit = new navItem("", new Rectangle(DEFALUT_WIDTH-50, -8, 50, 50), "exit_25px.png", "exit_25px.png", "exit_hover_25px.png", new Color(240, 71, 74));
        navItem minimize = new navItem("", new Rectangle(DEFALUT_WIDTH-100, -8, 50, 50), "minimize_25px.png", "minimize_25px.png", "minimize_hover_25px.png" ,new Color(197, 240, 233));
        
        hmain.add(exit.isButton());
        hmain.add(minimize.isButton());
        
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });

        exit.getlb().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        
        minimize.addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent e) {
              setState(Frame.ICONIFIED);
           }
        });

        minimize.getlb().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               setState(Frame.ICONIFIED);
            }
         });
        
        header.add(hmain);
        
/****************************************************************/    


/************ PHẦN NAVIGATION ( MENU ) **************************/  
        nav = new JPanel(null);
        nav.setBackground(new Color(56, 121, 164));
        nav.setPreferredSize(new Dimension(220,DEFAULT_HEIGHT));
        
        JScrollPane scroll = new JScrollPane(nav);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(1,100));
        scroll.setHorizontalScrollBarPolicy(scroll.HORIZONTAL_SCROLLBAR_NEVER);
     
        
        //Thêm item vào thanh menu (Tên item : icon : icon hover)
        navItem = new ArrayList<>();  //Chứa thông tin có button cho menu gồm ( Tên btn : icon : icon hover )
        navItem.add("Bán hàng:Shop_20px.png:Shop_20px_active.png");
        navItem.add("Quản lý Sản Phẩm:QLSP_20px.png:QLSP_20px_active.png");
        navItem.add("Quản lý nhân viên:NhanVien_20px.png:NhanVien_20px_active.png");
        navItem.add("Quản lý Khách Hàng:KhachHang_20px.png:KhachHang_20px_active.png");
        navItem.add("Nhập & Xuất:ThongKe_20px.png:ThongKe_20px_active.png");
        navItem.add("Nhà cung cấp:CongCu_20px.png:CongCu_20px_active.png");
        if( role == null || role.equals("Admin")) {
            navItem.add("Tài Khoản:CaiDat_20px.png:CaiDat_20px_active.png");
            navItem.add("Thống kê:ThongKe_20px.png:ThongKe_20px_active.png");
        }
        
        outNav();
        
/************ PHẦN MAIN ( HIỂN THỊ ) **************************/        
        main = new JPanel(null);
        main.setBackground(Color.WHITE);
        navObj.get(0).doActive();
        changeMainInfo(0);
/**************************************************************/   

        add(header,BorderLayout.NORTH);
        add(scroll,BorderLayout.WEST);
        add(main,BorderLayout.CENTER);
      
        setVisible(true);
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            
        }
        QLiLaptop ql = new QLiLaptop();
        
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        for(int i  = 0 ; i<navObj.size();i++)
        {
            navItem item = navObj.get(i); // lấy vị trí item trong menu
            if(e.getSource() == item) {
                item.doActive(); // Active NavItem đc chọn 
                changeMainInfo(i); // Hiển thị ra phần main
            }
            else if (e.getSource() == item.getlb())
            {
                item.doActive();
                changeMainInfo(i);
            }
            else{
                item.noActive();
            }
        }
    }
    
    public void changeMainInfo(int i) //Đổi Phần hiển thị khi bấm btn trên menu
    {
        if(flag && i>4 && i<8) // Thay đổi nếu Thông kế đang dropdown
        {
            i = i + 2;
        }
        switch(i)
        {
            case 0: //  BÁN HÀNG 
                main.removeAll();
                main.add(new BanHangUI(DEFALUT_WIDTH,userID));
                main.repaint();
                main.revalidate();
            break;
            case 1: // QUẢN LÝ SẢN PHẨM
                main.removeAll();
                main.add(new LaptopUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;

            case 2: // QUẢN LÝ NHÂN VIÊN
                main.removeAll();
                main.add(new NhanVienUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            case 3: // QUẢN LÝ KHÁCH HÀNG
                main.removeAll();
                main.add(new KhachHangUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            case 4: //NHẬP VẦ XUẤT
                if(flag)
                {
                    // Thêm 2 btn vào dưới thống kê
                    navItem.add(5, "Bán Hàng:KhachHang_20px.png:KhachHang_20px_active.png");
                    navItem.add(6, "Nhập Hàng:KhachHang_20px.png:KhachHang_20px_active.png");
                    
                    flag = false; // Thông báo là đang Dropdown thống kê
                }
                else
                {
                    // Xóa 2 btn của thống kê
                    navItem.remove(5);
                    navItem.remove(5);
                    
                    flag = true;  // Thông báo là Dropdown thống kê đă ẩn
                }
                outNav(); //Load lại phần Navigation
            break;
            case 5: // BÁN HÀNG
                main.removeAll();
                main.add(new HoaDonUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            case 6: // NHẬP HÀNG
                main.removeAll();
                //main.add(new Maintainance(DEFALUT_WIDTH, "THỐNG KÊ - NHẬP HÀNG"));
                main.add(new NhapHangUI(DEFALUT_WIDTH, userID));
                main.repaint();
                main.revalidate();
            break;
            case 7: //NHÀ CUNG CẤP
                main.removeAll();
                main.add(new NhaCungCapUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            case 8: //USER
                main.removeAll();
                main.add(new UserUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            case 9: // THỐNG KÊ
                main.removeAll();
                main.add(new ThongKeUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            default:
            break;
        }
    }
      
    public void outNav()
    {
        //Gắn cái NavItem vào NavOBJ
        navObj.clear();
        for(int i = 0 ; i < navItem.size() ; i++) {
            String s = navItem.get(i).split(":")[0];
            String icon = navItem.get(i).split(":")[1];
            String iconActive = navItem.get(i).split(":")[2];
            navObj.add(new navItem(s, new Rectangle(0,200+50*i,220,50),icon,iconActive));
            navObj.get(i).addMouseListener(this);
            navObj.get(i).getlb().addMouseListener(this);
        }
        //Đổi màu phần DropDown của thống kê 
        if(!flag && navObj.size() > 8) {
            navObj.get(5).setColorNormal(new Color(109, 173, 255));
            navObj.get(6).setColorNormal(new Color(109, 173, 255));
        }
        
        //Xuất ra Nagivation
        nav.removeAll();
        JLabel profile = new JLabel(new ImageIcon("./src/main/java/image/profile_150px.png"));
        profile.setBounds(0,0,220,200);
        nav.add(profile);
        for(navItem n : navObj) {
            nav.add(n); 
        }
        repaint();
        revalidate();
    }
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }
}
