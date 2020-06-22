/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import DTO.NhaCungCap;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
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
public class NhaCungCapUI extends JPanel {
    private NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    private JTable tbl;
    private JTextField txtMaNCC,txtTenNCC,txtDiaChi,txtDienThoai;
    private JTextField sortMaNCC,sortTenNCC;
    private DefaultTableModel model;
    private int DEFALUT_WIDTH;
    private boolean EditOrAdd = true;//Cờ cho button Cofirm True:ADD || False:Edit
    
    public NhaCungCapUI (int width)
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
        JLabel lbMaNCC = new JLabel("Mă NCC");
        txtMaNCC = new JTextField("");
        lbMaNCC.setBounds(new Rectangle(50,0,200,30));
        lbMaNCC.setFont(font0);
        txtMaNCC.setBounds(new Rectangle(150,0,220,30));
        
        JLabel lbTenNCC = new JLabel("Tên NCC");
        txtTenNCC = new JTextField("");
        lbTenNCC.setBounds(new Rectangle(400,0,100,30));
        lbTenNCC.setFont(font0);
        txtTenNCC.setBounds(new Rectangle(500,0,220,30));
     
        JLabel lbDienThoai = new JLabel("Số Điện thoại");
        txtDienThoai = new JTextField("");
        lbDienThoai.setBounds(new Rectangle(50,40,200,30));
        lbDienThoai.setFont(font0);
        txtDienThoai.setBounds(new Rectangle(150,40,220,30));
        
        JLabel lbDiaChi = new JLabel("Địa chỉ");
        txtDiaChi = new JTextField("");
        lbDiaChi.setBounds(new Rectangle(50,80,200,30));
        lbDiaChi.setFont(font0);
        txtDiaChi.setBounds(new Rectangle(150,80,500,30));
        
        // THÊM VÀO PHẦN HIỂN THỊ
        itemView.add(lbMaNCC);
        itemView.add(txtMaNCC);
        itemView.add(lbTenNCC);
        itemView.add(txtTenNCC);
        itemView.add(lbDiaChi);
        itemView.add(txtDiaChi);
        itemView.add(lbDienThoai);
        itemView.add(txtDienThoai);
        add(itemView);
        setEditable(false);
        
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
        
        // MouseClick btnADD
        btnAdd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                EditOrAdd = true;
                
                cleanView();
                
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                
                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
                
                tbl.clearSelection();
                tbl.setEnabled(false);
                txtMaNCC.setText(nccBUS.remindMaNCC());
                setEditable(true);
            }
        });
        
        // MouseClick btnDelete
        btnDelete.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){   
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
                if(i == 0){
                    nccBUS.deleteNCC(txtMaNCC.getText());
                    cleanView();
                    tbl.clearSelection();
                    outModel(model, nccBUS.getList());
                    setEditable(false);
                }
            }
        });
        
        // MouseClick btnEdit
        btnEdit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                
                if(txtMaNCC.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần sửa !!!");
                    return;
                }
                
                EditOrAdd = false;
                
                
                txtMaNCC.setEditable(false);
                
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                
                btnConfirm.setVisible(true);
                btnBack.setVisible(true);

                tbl.setEnabled(false);
                setEditable(true);
            }
        });
        
        
        //MouseClick btnBack
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                cleanView();
                
                btnAdd.setVisible(true);
                btnEdit.setVisible(true);
                btnDelete.setVisible(true);
                
                btnConfirm.setVisible(false);
                btnBack.setVisible(false);
//              btnFile.setVisible(false);
                
                tbl.setEnabled(true);
                setEditable(false);
            }
        });
        
        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                int i;
                if(EditOrAdd) //Thêm Sản Phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm sản phẩm","",JOptionPane.YES_NO_OPTION);
                    if(i == 0)
                    {
                        //Lấy dữ liệu từ TextField
                        String maNCC = txtMaNCC.getText();
                        String tenNCC = txtTenNCC.getText();
                        String diaChi = txtDiaChi.getText();
                        String dienThoai = txtDienThoai.getText();
                        if(nccBUS.checkMancc(maNCC))
                        {
                            JOptionPane.showMessageDialog(null, "Mã nhân viên đă tồn tại !!!");
                            setEditable(false);
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi, dienThoai);
                        nccBUS.addNCC(ncc);

                        outModel(model, nccBUS.getList());// Load lại table

                        setEditable(false);
                        cleanView();
                    }
                }
                else    // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa sản phẩm","",JOptionPane.YES_NO_OPTION);
                    if(i == 0)
                    {
                        //Lấy dữ liệu từ TextField
                        String maNCC = txtMaNCC.getText();
                        String tenNCC = txtTenNCC.getText();
                        String diaChi = txtDiaChi.getText();
                        String dienThoai = txtDienThoai.getText();

                        //Upload sản phẩm lên DAO và BUS
                        NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi, dienThoai);
                        nccBUS.setNCC(ncc);
                        
                        outModel(model, nccBUS.getList());// Load lại table
                        
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
        header.add("Mã NCC");
        header.add("Tên NCC");
        header.add("Địa chỉ");
        header.add("SĐT");
        model = new DefaultTableModel(header,5);
        tbl = new JTable(model);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        tbl.setRowSorter(rowSorter);
        listNCC(); //Đọc từ database lên table 
        
/*********************************************************/
        
/**************** TẠO TABLE ************************************************************/

        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(60);


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
            public void mouseClicked(MouseEvent e){
                int i = tbl.getSelectedRow();
                txtMaNCC.setText(tbl.getModel().getValueAt(i, 0).toString());
                txtTenNCC.setText(tbl.getModel().getValueAt(i, 1).toString());
                txtDiaChi.setText(tbl.getModel().getValueAt(i, 2).toString()); 
                txtDienThoai.setText(tbl.getModel().getValueAt(i, 3).toString());  
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
        cmbChoice.addItem("Mã NCC");
        cmbChoice.addItem("Tên NCC");
        cmbChoice.addItem("Địa chỉ");
        cmbChoice.addItem("SĐT");
        cmbChoice.setBounds(new Rectangle(0,0,120,30));
        
        //Phần TextField
        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(new Rectangle(125,0,400,30));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI",Font.PLAIN,15));
        
        // Custem Icon search
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
            public void focusLost(FocusEvent e){ //Trờ về như cũ
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
    public void cleanView(){ //Xóa trắng các TextField
        txtMaNCC.setEditable(false);
        txtMaNCC.setText("");
        txtTenNCC.setText("");
        txtDiaChi.setText("");
        txtDienThoai.setText("");
    }
    
    public void setEditable(boolean flag) {
        txtMaNCC.setEditable(false);
        txtTenNCC.setEditable(flag);
        txtDiaChi.setEditable(flag);
        txtDienThoai.setEditable(flag);
    }
    public void outModel(DefaultTableModel model , ArrayList<NhaCungCap> ncc){ // Xuất ra Table từ ArrayList
    
        Vector data;
        model.setRowCount(0);
        for(NhaCungCap n:ncc){
            data = new Vector();
            data.add(n.getMaNCC());
            data.add(n.getTenNCC());
            data.add(n.getDiaChi());
            data.add(n.getDienThoai());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void listNCC(){ // Chép ArrayList lên table
    
        if(nccBUS.getList()== null)nccBUS.listNCC();
        ArrayList<NhaCungCap> ncc = nccBUS.getList();
        outModel(model,ncc);
    }
}
