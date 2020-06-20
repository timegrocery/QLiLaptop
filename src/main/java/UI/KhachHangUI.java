/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BUS.KhachHangBUS;
import DTO.KhachHang;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author WindZ
 */
public class KhachHangUI extends JPanel {
    private KhachHangBUS khBUS = new KhachHangBUS();
    
    private JTable tbl = new JTable();
    
    private JTextField txtMaKH,txtHoKH,txtTenKH,txtSDT;
    private JTextField sortMaKH,sortHoKH,sortTenKH;
    DefaultTableModel model;
    private int DEFALUT_WIDTH;
    private boolean EditOrAdd = true;//Cờ cho button Cofirm True:ADD || False:Edit
    
    public KhachHangUI (int width)
    {
        DEFALUT_WIDTH = width;
        init();
    }
    public void init()
    {
        
        setLayout(null);
        setBackground(null);
        setBounds(new Rectangle(50, 0, this.DEFALUT_WIDTH - 220, 1000));
        
        Font font0 = new Font("Segoe UI",Font.PLAIN,13);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
/****************************** PHẦN HIỂN THỊ THÔNG TIN ******************************************/

        JPanel itemView = new JPanel(null);
        itemView.setBounds(new Rectangle(30, 40, this.DEFALUT_WIDTH - 220 , 180));
        itemView.setBackground(null);
        
        /******** Tao Cac Label & TextField ************************/
        JLabel lbMaKH = new JLabel("Mă khách hàng");
        txtMaKH = new JTextField("");
        lbMaKH.setBounds(new Rectangle(50,0,200,30));
        lbMaKH.setFont(font0);
        txtMaKH.setBounds(new Rectangle(150,0,220,30));
        
        JLabel lbSDT = new JLabel("Số điện thoại");
        txtSDT = new JTextField("");
        lbSDT.setBounds(new Rectangle(400,0,100,30)); 
        lbSDT.setFont(font0);
        txtSDT.setBounds(new Rectangle(500,0,220,30));
        
        JLabel lbHoKH = new JLabel("Họ");
        txtHoKH = new JTextField("");
        lbHoKH.setBounds(new Rectangle(50,40,200,30));
        lbHoKH.setFont(font0);
        txtHoKH.setBounds(new Rectangle(150,40,220,30));
     
        JLabel lbTenKH = new JLabel("Tên");
        txtTenKH = new JTextField("");
        lbTenKH.setBounds(new Rectangle(400,40,200,30));
        lbTenKH.setFont(font0);
        txtTenKH.setBounds(new Rectangle(500,40,220,30));
        setEditable(false);
            
        
        // THÊM VÀO PHẦN HIỂN THỊ
        itemView.add(lbMaKH);
        itemView.add(txtMaKH);
        itemView.add(lbHoKH);
        itemView.add(txtHoKH);
        itemView.add(lbTenKH);
        itemView.add(txtTenKH);
        itemView.add(lbSDT);
        itemView.add(txtSDT);
        
        add(itemView);
        
        /**************** TẠO CÁC BTN THÊM ,XÓA, SỬA ********************/
        JLabel btnAdd = new JLabel(new ImageIcon("./src/main/java/image/btnAdd.png"));
        btnAdd.setBounds(new Rectangle(750,0,200,50));
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        JLabel btnEdit = new JLabel(new ImageIcon("./src/main/java/image/btnEdit.png"));
        btnEdit.setBounds(new Rectangle(750,55,200,50));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        
        JLabel btnDelete = new JLabel(new ImageIcon("./src/main/java/image/btnDelete.png"));
        btnDelete.setBounds(new Rectangle(750,110,200,50));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        itemView.add(btnAdd);
        itemView.add(btnEdit);
        itemView.add(btnDelete);
        
        
        
        JLabel btnConfirm= new JLabel(new ImageIcon("./src/main/java/image/btnConfirm.png"));
        btnConfirm.setVisible(false);
        btnConfirm.setBounds(new Rectangle(750,0,200,50));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel btnBack = new JLabel(new ImageIcon("./src/main/java/image/btnBack.png"));
        btnBack.setVisible(false);
        btnBack.setBounds(new Rectangle(750,55,200,50));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        itemView.add(btnConfirm);
        itemView.add(btnBack);
        
        // set editable
        
        // MouseClick btnADD
        btnAdd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                EditOrAdd = true;
                
                cleanView();
                setEditable(true);
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                
                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
//                btnFile.setVisible(true);
                txtMaKH.setText(khBUS.remindMaKH());
                
                tbl.clearSelection();
                tbl.setEnabled(false);
            }
        });
        
        // MouseClick btnDelete
        btnDelete.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {   
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
                if(i == 0) {
                    khBUS.delete(txtMaKH.getText());
                    cleanView();
                    tbl.clearSelection();
                    outModel(model, khBUS.getList());
                }
            }
        });
        
        // MouseClick btnEdit
        btnEdit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                
                if(txtMaKH.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa !!!");
                    return;
                }
                
                EditOrAdd = false;
                
                setEditable(true);
                
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                
                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
                tbl.setEnabled(false);
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
                
                tbl.setEnabled(true);
                setEditable(false);
            }
        });
        
        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                int i;
                if(EditOrAdd) {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm khách hàng?","",JOptionPane.YES_NO_OPTION);
                    if(i == 0) {
                        //Lấy dữ liệu từ TextField
                        String maKH = txtMaKH.getText();
                        String hoKH = txtHoKH.getText();
                        String tenKH = txtTenKH.getText();
                        String dienThoai = txtSDT.getText();
                        if(khBUS.check(maKH)) {
                            JOptionPane.showMessageDialog(null, "Mã khách hàng đă tồn tại !!!");
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        KhachHang ncc = new KhachHang(maKH, hoKH, tenKH, dienThoai);
                        khBUS.add(ncc);

                        outModel(model, khBUS.getList());// Load lại table
                        
                        cleanView();
                        setEditable(false);
                    }
                }
                else    // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa khách hàng?","",JOptionPane.YES_NO_OPTION);
                    if(i == 0) {
                        //Lấy dữ liệu từ TextField
                        String maKH = txtMaKH.getText();
                        String hoKH = txtHoKH.getText();
                        String tenKH = txtTenKH.getText();
                        String sdt = txtSDT.getText();

                        //Upload sản phẩm lên DAO và BUS
                        KhachHang kh = new KhachHang(maKH, hoKH, tenKH, sdt);
                        khBUS.set(kh);

                        outModel(model, khBUS.getList());// Load lại table
                        
                        
                        JOptionPane.showMessageDialog(null, "Sửa thành công","Thành công",JOptionPane.INFORMATION_MESSAGE);
                        setEditable(false);
                        
                    }
                }
                
            }
        });
/***************************************************************/
/************************************************************************************************************/       

/************** TẠO MODEL VÀ HEADER *********************/
        Vector header = new Vector();
        header.add("Mă KH");
        header.add("Họ và tên");
        header.add("SĐT");
        model = new DefaultTableModel(header,5);
        tbl = new JTable(model);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        tbl.setRowSorter(rowSorter);
        list(); //Đọc từ database lên table 
        
/*********************************************************/
        
/**************** TẠO TABLE ************************************************************/

        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(50);


        // Custom table
        tbl.setFocusable(false);
        tbl.setIntercellSpacing(new Dimension(0,0));     
        tbl.getTableHeader().setFont(font1);
        tbl.setRowHeight(30);
        tbl.setShowVerticalLines(false);              
        tbl.getTableHeader().setOpaque(false);
        tbl.setFillsViewportHeight(true);
        tbl.getTableHeader().setBackground(new Color(232,57,99));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.setSelectionBackground(new Color(52,152,219));          
        
        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbl);
        scroll.setBounds(new Rectangle(30, 220, this.DEFALUT_WIDTH - 400 , 450));
        scroll.setBackground(null);
        
        add(scroll);
/*****************************************************************************************/

        
        tbl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tbl.getSelectedRow();
                txtMaKH.setText(tbl.getModel().getValueAt(i, 0).toString());
                String name = tbl.getModel().getValueAt(i, 1).toString();
                String lastName = "";
                String firstName= "";
                if(name.split("\\w+").length>1){
                    lastName = name.substring(name.lastIndexOf(" ")+1);
                    firstName = name.substring(0, name.lastIndexOf(' '));
                } else {
                    firstName = name;
                }
                txtHoKH.setText(firstName);
                txtTenKH.setText(lastName);
                txtSDT.setText(tbl.getModel().getValueAt(i, 2).toString());
            }
        });
/*********************** SORT TABLE *****************************/
       JPanel searchBox = new JPanel(null);
        searchBox.setBackground(null);
        searchBox.setBounds(new Rectangle(50,120,530,30)); 
        searchBox.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
        //PHẦN CHỌN SEARCH
        JComboBox cmbChoice = new JComboBox();
        cmbChoice.setEditable(true);
        cmbChoice.setFont(new Font("Segoe UI",Font.PLAIN,14));
        cmbChoice.addItem("Mã KH");
        cmbChoice.addItem("Tên KH");
        cmbChoice.addItem("SĐT");
        cmbChoice.setBounds(new Rectangle(0,0,120,30));
        
        //Phần TextField
        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(new Rectangle(125,0,400,30));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI",Font.PLAIN,15));
        
        // Custom Icon search
        JLabel searchIcon = new JLabel(new ImageIcon("./src/main/java/image/search_25px.png"));
        searchIcon.setBounds(new Rectangle(485,-10,50,50));
        searchIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add tất cả vào search box
        searchBox.add(cmbChoice);
        searchBox.add(txtSearch);
        searchBox.add(searchIcon);

        //bắt sự kiện Focus vào search box
        txtSearch.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e) {
                searchIcon.setIcon(new ImageIcon("./src/main/java/image/search_25px_focus.png")); //Đổi màu icon
                searchBox.setBorder(createLineBorder(new Color(52,152,219))); // Đổi màu viền 
            }
            // trở về như cũ
            public void focusLost(FocusEvent e) {
                searchIcon.setIcon(new ImageIcon("./src/main/java/image/search_25px.png"));
                searchBox.setBorder(createLineBorder(Color.BLACK));
            }
        });
        txtSearch.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                int choice = cmbChoice.getSelectedIndex();
                
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ text +"", choice));
                }
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                int choice = cmbChoice.getSelectedIndex();
                
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ text +"", choice));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        itemView.add(searchBox);
/******************************************************************/
    }
    
    private void setEditable(boolean flag) {
        txtMaKH.setEditable(false); // tránh lỗi trong lúc chỉnh sửa csdl
        txtHoKH.setEditable(flag);
        txtTenKH.setEditable(flag);
        txtSDT.setEditable(flag);
    }
    public void cleanView() //Xóa trắng các TextField
    {
        txtMaKH.setEditable(false);

        txtMaKH.setText("");
        txtHoKH.setText("");
        txtTenKH.setText("");
        txtSDT.setText("");
        
    }
    public void outModel(DefaultTableModel model , ArrayList<KhachHang> nv) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(KhachHang n:nv) {
            data = new Vector();
            data.add(n.getMaKH());
            data.add(n.getHoKH() + " " + n.getTenKH());
            data.add(n.getSdt());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void list() // Chép ArrayList lên table
    {
        if(khBUS.getList()== null)khBUS.list();
        ArrayList<KhachHang> nv = khBUS.getList();
//        model.setRowCount(0);
        outModel(model,nv);
    }
}
