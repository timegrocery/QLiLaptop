/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BUS.Encryptor;
import BUS.InfoBalloon;
import BUS.NhanVienBUS;
import BUS.UserBUS;
import DTO.NhanVien;
import DTO.User;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author WindZ
 */
public class NhanVienUI extends JPanel{
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private JTable tbl;
    private BufferedImage i = null;//Hình ảnh chọn từ file
    private JLabel img;
    private String imgName = "null";
    private JTextField txtMaNV,txtHoNV,txtTenNV,txtNamSinh,txtPhai,txtDiaChi,txtSearch;
    private DefaultTableModel model;
    private int DEFALUT_WIDTH;
    private boolean EditOrAdd = true;//Cờ cho button Cofirm True:ADD || False:Edit
    private JTextField sortMaNV;
    private JTextField sortHoNV;
    private JTextField sortTenNV;
    private Choice sortPhai;
    private JComboBox cmbPhai;
    private final String DEFAULT_PASSWORD = "123";
    public NhanVienUI(int width)
    {
        DEFALUT_WIDTH = width;
        try {
            init();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhanVienUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void init() throws ClassNotFoundException, SQLException
    {
        setLayout(null);
        setBackground(null);
        setBounds(new Rectangle(50, 0, this.DEFALUT_WIDTH - 220, 1000));
        Font font0 = new Font("Segoe UI",Font.PLAIN,14);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
/****************************** PHẦN HIỂN THỊ THÔNG TIN ******************************************/

        JPanel ItemView = new JPanel(null);
        ItemView.setBounds(new Rectangle(30, 20, this.DEFALUT_WIDTH - 220 , 250));
        ItemView.setBackground(Color.WHITE);
        
        /******** Tao Cac Label & TextField ************************/
        JLabel lbMaNV = new JLabel("Mă nhân viên");
        txtMaNV = new JTextField("");
        lbMaNV.setBounds(new Rectangle(250,0,200,30));
        lbMaNV.setFont(font0);
        txtMaNV.setBounds(new Rectangle(350,0,220,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtMaNV,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        
        JLabel lbHoNV = new JLabel("Họ");
        txtHoNV = new JTextField("");
        lbHoNV.setBounds(new Rectangle(250,40,200,30));
        lbHoNV.setFont(font0);
        txtHoNV.setBounds(new Rectangle(350,40,220,30));
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtHoNV, InfoBalloon.filter_alphaOnly, InfoBalloon.limit_name);
        
        JLabel lbTenNV = new JLabel("Tên nhân viên");
        txtTenNV = new JTextField("");
        lbTenNV.setBounds(new Rectangle(250,80,200,30));
        lbTenNV.setFont(font0);
        txtTenNV.setBounds(new Rectangle(350,80,220,30));
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtTenNV, InfoBalloon.filter_alphaOnly, InfoBalloon.limit_name);
        
        JLabel lbDiaChi = new JLabel("Địa chỉ");
        txtDiaChi = new JTextField("");
        lbDiaChi.setBounds(new Rectangle(250,160,200,30));
        lbDiaChi.setFont(font0);
        txtDiaChi.setBounds(new Rectangle(350,160,220,30));
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtDiaChi, InfoBalloon.filter_all, InfoBalloon.limit_name);
        
        JLabel lbNamSinh = new JLabel("Năm sinh");
        txtNamSinh = new JTextField("");
        lbNamSinh.setBounds(new Rectangle(420,200,80,30));
        lbNamSinh.setFont(font0);
        txtNamSinh.setBounds(new Rectangle(490,200,80,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtNamSinh, InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        
        JLabel lbPhai = new JLabel("Phái");
        lbPhai.setBounds(new Rectangle(250,200,30,30));
        lbPhai.setFont(font0);
        String []phai = {"Nam","Nữ"};
        cmbPhai = new JComboBox(phai);
        cmbPhai.setBounds(new Rectangle(310,200,80,30));
        
        img = new JLabel("Image");
        img.setBorder(createLineBorder(Color.BLACK));
        img.setBounds(new Rectangle(0,0,200,230));
        
        
        // THÊM VÀO PHẦN HIỂN THỊ
        ItemView.add(img);
        ItemView.add(lbMaNV);
        ItemView.add(txtMaNV);
        ItemView.add(lbHoNV);
        ItemView.add(txtHoNV);
        ItemView.add(lbTenNV);
        ItemView.add(txtTenNV);
        ItemView.add(lbNamSinh);
        ItemView.add(txtNamSinh);
        ItemView.add(lbPhai);
        ItemView.add(cmbPhai);
        ItemView.add(lbDiaChi);
        ItemView.add(txtDiaChi);
        setEditable(false);
        /************************************************************/
        
        /**************** TẠO CÁC BTN THÊM ,XÓA, SỬA ********************/
        JLabel btnAdd = new JLabel(new ImageIcon("./src/main/java/image/btnAdd.png"));
        btnAdd.setBounds(new Rectangle(620,0,200,50));
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        JLabel btnEdit = new JLabel(new ImageIcon("./src/main/java/image/btnEdit.png"));
        btnEdit.setBounds(new Rectangle(620,70,200,50));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        
        JLabel btnDelete = new JLabel(new ImageIcon("./src/main/java/image/btnDelete.png"));
        btnDelete.setBounds(new Rectangle(620,140,200,50));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        ItemView.add(btnAdd);
        ItemView.add(btnEdit);
        ItemView.add(btnDelete);
        
        
        
        JLabel btnConfirm= new JLabel(new ImageIcon("./src/main/java/image/btnConfirm.png"));
        btnConfirm.setVisible(false);
        btnConfirm.setBounds(new Rectangle(620,0,200,50));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel btnBack = new JLabel(new ImageIcon("./src/main/java/image/btnBack.png"));
        btnBack.setVisible(false);
        btnBack.setBounds(new Rectangle(620,70,200,50));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel btnFile = new JLabel(new ImageIcon("./src/main/java/image/btnFile.png"));
        btnFile.setVisible(false);
        btnFile.setBounds(new Rectangle(620,140,200,50));
        btnFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        ItemView.add(btnConfirm);
        ItemView.add(btnBack);
        ItemView.add(btnFile);
        
        // MouseClick btnADD
        btnAdd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                EditOrAdd = true;
                txtMaNV.requestFocus();
                cleanView();
                
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                
                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
                btnFile.setVisible(true);
                
                tbl.clearSelection();
                tbl.setEnabled(false);
                txtMaNV.setText(nvBUS.remindMaNV());
                setEditable(true);
            }
        });
        
        // MouseClick btnDelete
        btnDelete.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {   
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
                if(i == 0)
                {
                    UserBUS usBUS = new UserBUS();
                    usBUS.delete(txtMaNV.getText(),1);
                    nvBUS.deleteNV(txtMaNV.getText());
                    cleanView();
                    tbl.clearSelection();
                    outModel(model, nvBUS.getList());
                }
            }
        });
        
        // MouseClick btnEdit
        btnEdit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                
                if(txtMaNV.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa !!!");
                    return;
                }
                
                EditOrAdd = false;
                
                
                setEditable(true);
                
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                
                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
                btnFile.setVisible(true);
                
//                tbl.clearSelection();
                tbl.setEnabled(false);
            }
        });
        
        // MouseClick btnFile
        btnFile.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) 
                {
                    try {
                        File file = fc.getSelectedFile(); //Lấy URL hình
                        i = ImageIO.read(file); // Lấy hình
                        imgName = txtMaNV.getText().concat(".jpg"); //Tên hình
                        
                        // Thay đổi hình hiển thị
                        img.setText("");
                        img.setIcon(new ImageIcon(i.getScaledInstance(200, 230, Image.SCALE_DEFAULT)));
                        
                        revalidate();
                        repaint();
                    } catch (IOException ex) {
                        Logger.getLogger(NhanVienUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        //MouseClick btnBack
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                cleanView();
                
                btnAdd.setVisible(true);
                btnEdit.setVisible(true);
                btnDelete.setVisible(true);
                
                btnConfirm.setVisible(false);
                btnBack.setVisible(false);
                btnFile.setVisible(false);
                
                tbl.setEnabled(true);
                setEditable(false);
            }
        });
        
        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int i;
                if(EditOrAdd){ //Thêm Nhân Viên
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm nhân viên","",JOptionPane.YES_NO_OPTION);
                    if(i == 0){
                        try {
                        //Lấy dữ liệu từ TextField
                        String maNV = txtMaNV.getText();
                        String hoNV = txtHoNV.getText();
                        String tenNV = txtTenNV.getText();
                        int namSinh = Integer.parseInt(txtNamSinh.getText());
                        String phai = cmbPhai.getSelectedItem().toString();
                        String diaChi = txtDiaChi.getText();
                        String IMG = imgName;
                       
                        if(nvBUS.check(maNV)) {
                            JOptionPane.showMessageDialog(null, "Mã nhân viên đă tồn tại !!!");
                            return;
                        }
                        //Upload nhân viên lên DAO và BUS
                        NhanVien nv = new NhanVien(maNV, hoNV, tenNV,  phai, diaChi, namSinh, IMG);
                        nvBUS.addNV(nv);
                        UserBUS usBUS = new UserBUS();
                        Encryptor enc = new Encryptor();
                        User user = new User(maNV, removeAccent(tenNV.concat(maNV)).toLowerCase(), DEFAULT_PASSWORD, "Nhân Viên", "1");
                        usBUS.add(user, 1);
                        outModel(model, nvBUS.getList());// Load lại table

                        saveIMG();// Lưu hình ảnh 
                            
                        cleanView();
                        setEditable(false);
                        } catch(NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null,"Loi");
                        }
                        
                    }
                }
                else    // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa sản phẩm","",JOptionPane.YES_NO_OPTION);
                    if(i == 0)
                    {
                        //Lấy dữ liệu từ TextField
                        String maNV = txtMaNV.getText();
                        String hoNV = txtHoNV.getText();
                        String tenNV = txtTenNV.getText();
                        int namSinh = Integer.parseInt(txtNamSinh.getText());
                        String phai = cmbPhai.getSelectedItem().toString();
                        String diaChi = txtDiaChi.getText();
                        String IMG = imgName;

                        //Upload sản phẩm lên DAO và BUS
                        NhanVien sp = new NhanVien(maNV, hoNV, tenNV, phai, diaChi, namSinh, IMG);
                        nvBUS.setNV(sp);
                        
                        outModel(model, nvBUS.getList());// Load lại table
                        
                        saveIMG();// Lưu hình ảnh 
                        
                        JOptionPane.showMessageDialog(null, "Sửa thành công","Thành công",JOptionPane.INFORMATION_MESSAGE);
                        setEditable(false);
                    }
                }
                
            }
        });
        
        /****************************************************************/
        
/**********************************************************************************/
     
/************** TẠO MODEL VÀ HEADER *********************/
        Vector header = new Vector();
        header.add("Mă NV");
        header.add("Họ NV");
        header.add("Tên NV");
        header.add("Năm sinh");
        header.add("Phái");
        header.add("Địa chỉ");
        header.add("IMG"); 
        model = new DefaultTableModel(header,5)
        {
            public Class getColumnClass(int column)
            {
                switch(column){
                    case 3:
                        return Integer.class;
                    case 5:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
                        
        };
        tbl = new JTable(model);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        tbl.setRowSorter(rowSorter);
        listSP(); //Đọc từ database lên table 
        
/*********************************************************/
        
/**************** TẠO TABLE ************************************************************/

        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(20);
        //tbl.getColumnModel().getColumn(5).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(6).setPreferredWidth(100);

        DefaultTableCellRenderer leftAlign = new DefaultTableCellRenderer();
        leftAlign.setHorizontalAlignment(JLabel.LEFT);
        tbl.getColumnModel().getColumn(3).setCellRenderer(leftAlign);
        tbl.getColumnModel().getColumn(5).setCellRenderer(leftAlign);
        
        // Custom table
        tbl.setFocusable(false);
        tbl.setIntercellSpacing(new Dimension(0,0));     
        tbl.getTableHeader().setFont(font1);
        tbl.setRowHeight(30);
        tbl.setShowVerticalLines(false);              
        tbl.getTableHeader().setOpaque(false);
        tbl.setFillsViewportHeight(true);
        tbl.getTableHeader().setBackground(new Color(57, 127, 232));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.setSelectionBackground(new Color(232,57,99));          
        
        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbl);
        scroll.setBounds(new Rectangle(30, 360, this.DEFALUT_WIDTH - 400 , 300));
        scroll.setBackground(null);
        
        add(scroll);
        add(ItemView);
/*****************************************************************************************/

        
        tbl.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e)
             {
                int i = tbl.getSelectedRow();
                try {
                    imgName = tbl.getModel().getValueAt(i, 6).toString();
                } catch (Exception ex) {
                    
                }
                Image newImage ;
                try{
                    newImage = new ImageIcon("./src/main/java/image/NhanVien/"+imgName).getImage().getScaledInstance(200, 230, Image.SCALE_DEFAULT);
                }catch(NullPointerException E)
                {
                    newImage = new ImageIcon("./src/main/java/image/NhanVien/NoImage.jpg").getImage().getScaledInstance(200, 230, Image.SCALE_DEFAULT); 
                }
                txtMaNV.setText(tbl.getModel().getValueAt(i, 0).toString());
                txtHoNV.setText(tbl.getModel().getValueAt(i, 1).toString());
                txtTenNV.setText(tbl.getModel().getValueAt(i, 2).toString()); 
                txtNamSinh.setText(tbl.getModel().getValueAt(i, 3).toString());
                cmbPhai.setSelectedItem(tbl.getModel().getValueAt(i, 4).toString());
                //txtPhai.setText( tbl.getModel().getValueAt(i, 4).toString());
                txtDiaChi.setText(tbl.getModel().getValueAt(i, 5).toString()); 
                img.setText("");
                img.setIcon(new ImageIcon(newImage));
                
             }
        });
/********************* THANH SEARCH ***********************************************/
        
//         Tạo Search Box
        JPanel searchBox = new JPanel(null);
        searchBox.setBackground(null);
        searchBox.setBounds(new Rectangle(620,200,250, 30)); 
        searchBox.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
        
        //Phần TextField 
        txtSearch = new JTextField();
        txtSearch.setBounds(new Rectangle(5,0,200,30));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI",Font.PLAIN,15));
        
        // Custem Icon search
        JLabel searchIcon = new JLabel(new ImageIcon("./src/main/java/image/search_25px.png"));
        searchIcon.setBounds(new Rectangle(200,-9,50,50));
        searchIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add tất cả vào search box
        searchBox.add(searchIcon);
        searchBox.add(txtSearch);

        //bắt sự kiện Focus vào search box
        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                searchIcon.setIcon(new ImageIcon("./src/main/java/image/search_25px_focus.png")); //Đổi màu icon
                searchBox.setBorder(createLineBorder(new Color(232,57,99))); // Đổi màu viền 
            }
            //Trờ về như cũ
            public void focusLost(FocusEvent e) {
                searchIcon.setIcon(new ImageIcon("./src/main/java/image/search_25px.png"));
                searchBox.setBorder(createLineBorder(Color.BLACK));
            }
        });
        txtSearch.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^"+ text +".*",1,2));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^"+ text +".*", 1,2));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        ItemView.add(searchBox);
/*********************************************************************************/
        /*********************** PHẦN SEARCH TABLE *****************************/
        JPanel sort = new JPanel(null);
        sort.setBackground(null);
        sort.setBounds(30,265,this.DEFALUT_WIDTH - 400,100);

        JLabel sortTitle = new JLabel("------------------------------------------------------------------------------ TÌM KIẾM THÔNG TIN ------------------------------------------------------------------------------",JLabel.CENTER); // Mỗi bên 78 dấu ( - )
        sortTitle.setFont(font1);
        sortTitle.setBounds(new Rectangle(0,0,this.DEFALUT_WIDTH - 400,30));
        sort.add(sortTitle);

        /******** SORT MAKH **************/
        JLabel lbSortMaNV = new JLabel("Mă NV :");
        lbSortMaNV.setFont(font0);
        lbSortMaNV.setBounds(0,40,50,30);
        sort.add(lbSortMaNV);

        sortMaNV = new JTextField();
        sortMaNV.setFont(font0);
        sortMaNV.setBounds(new Rectangle(50,42,100,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, sortMaNV,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        sort.add(sortMaNV);
        /*************************************/

        /******** SORT HONV **************/
        JLabel lbSortHoNV = new JLabel("Họ :");
        lbSortHoNV.setFont(font0);
        lbSortHoNV.setBounds(170,40,30,30);
        sort.add(lbSortHoNV);

        sortHoNV = new JTextField();
        sortHoNV.setFont(font0);
        sortHoNV.setBounds(new Rectangle(200,42,100,30));
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtHoNV,InfoBalloon.filter_all, InfoBalloon.limit_name);
        sort.add(sortHoNV);
        /*************************************/
        
        /******** SORT TEN NV **************/
        JLabel lbSortTenNV = new JLabel("Tên :");
        lbSortTenNV.setFont(font0);
        lbSortTenNV.setBounds(320,40,30,30);
        sort.add(lbSortTenNV);

        sortTenNV = new JTextField();
        sortTenNV.setFont(font0);
        sortTenNV.setBounds(new Rectangle(350,42,100,30));
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtTenNV,InfoBalloon.filter_all, InfoBalloon.limit_name);
        sort.add(sortTenNV);
        /*************************************/
        /************ SORT PHÁI **************/
        JLabel lbSortPhai = new JLabel("Phái :");
        lbSortPhai.setFont(font0);
        lbSortPhai.setBounds(470,40,35,30);
        sort.add(lbSortPhai);
        
        sortPhai = new Choice();
        sortPhai.addItem("Tất cả");
        sortPhai.addItem("Nam");
        sortPhai.addItem("Nữ");
        sortPhai.setFont(font0);
        sortPhai.setBounds(new Rectangle(505,43,100,30));
        sort.add(sortPhai);
        /*************************************/
        /******************************************/

        JLabel btnSearch = new JLabel(new ImageIcon("./src/main/java/image/btnSearch_45px.png"));
        btnSearch.setBounds(new Rectangle(840,26,63,63));
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearch.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e)
           {
               System.out.println(sortPhai.getSelectedItem());
               System.out.println(sortPhai.getSelectedIndex());
               String manv = sortMaNV.getText();
               String ho = sortHoNV.getText();
               String ten = sortTenNV.getText();
               String phai = sortPhai.getSelectedIndex()!= 0 ? sortPhai.getSelectedItem() : "";
               
               outModel(model, nvBUS.search(manv, ho, ten, phai));
           }
        });
        sort.add(btnSearch);
        
        add(sort);
/*******************************************************************/
    }
    public void saveIMG()
    {
        try {
            if(i != null)
            {
                File save = new File("src/main/java/image/NhanVien/"+ imgName);// Tạo file
                ImageIO.write(i,"jpg",save); // Lưu hình i vào đường dẫn file save

                i = null; //Xóa hình trong bộ nhớ 
            }
        } catch (IOException ex) {
            Logger.getLogger(NhanVienUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cleanView() //Xóa trắng các TextField
    {
        txtMaNV.setText("");
        txtHoNV.setText("");
        txtTenNV.setText("");
        txtNamSinh.setText("");
        txtDiaChi.setText("");
        
        img.setIcon(null);
        img.setText("Image");
        
        imgName = "null";
    }
    
    private void setEditable(boolean flag) {
        txtMaNV.setEditable(false); // tránh lỗi trong lúc chỉnh sửa csdl
        txtHoNV.setEditable(flag);
        txtTenNV.setEditable(flag);
        txtNamSinh.setEditable(flag);
        txtDiaChi.setEditable(flag);
    }
    
    public void outModel(DefaultTableModel model , ArrayList<NhanVien> nv) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(NhanVien n:nv) {
            data = new Vector();
            data.add(n.getMaNV());
            data.add(n.getHoNV());
            data.add(n.getTenNV());
            data.add(n.getNamSinh());
            data.add(n.getPhai());
            data.add(n.getDiaChi());
            data.add(n.getImg());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void listSP() // Chép ArrayList lên table
    {
        if(nvBUS.getList()== null)nvBUS.listNV();
        ArrayList<NhanVien> nv = nvBUS.getList();
//        model.setRowCount(0);
        outModel(model,nv);
    }
    public String removeAccent(String s){  // Xóa dấu tiếng việt
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replace('đ','d').replace('Đ','D');
   }
}
