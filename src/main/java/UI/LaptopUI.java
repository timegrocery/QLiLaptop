/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BUS.InfoBalloon;
import BUS.LaptopBUS;
import BUS.NhaCungCapBUS;
import BUS.NhaSanXuatBUS;
import DTO.Laptop;
import DTO.NhaCungCap;
import DTO.NhaSanXuat;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.BorderFactory.createLineBorder;

/**
 *
 * @author WindZ
 */
public class LaptopUI extends JPanel implements KeyListener {
    private final LaptopBUS spBUS = new LaptopBUS();
    private final NhaSanXuatBUS nsxBUS = new NhaSanXuatBUS();
    private final NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    private JTable tbl;
    private BufferedImage i = null;//Hình ảnh chọn từ file
    private JLabel img;
    private String imgName = "null";
    private JTextField txtId,txtTenSP,txtGia,txtCPU,txtRAM,txtGPU,txtManhinh,txtOcung,txtSearch;
    private DefaultTableModel model;
    private final int DEFALUT_WIDTH;
    private boolean EditOrAdd = true;//Cờ cho button Cofirm True:ADD || False:Edit
    private JTextField txtMaxPrice;
    private JTextField sortMaSP;
    private JTextField txtMinPrice;
    private JComboBox cmbNCC;
    private JComboBox cmbSortNCC;
    private JComboBox cmbNSX;
    private JComboBox cmbSortNSX;
    
    
    //        

    public LaptopUI(int width)
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

        JPanel ItemView = new JPanel(null);
        ItemView.setBounds(new Rectangle(30, 20, this.DEFALUT_WIDTH - 220 , 310));
        ItemView.setBackground(Color.WHITE);
        
        /******** Tao Cac Label & TextField ************************/
        JLabel lbId = new JLabel("Mă Laptop");
        lbId.setBounds(new Rectangle(250,0,200,30));
        lbId.setFont(font0);
        txtId = new JTextField("");
        txtId.setBounds(new Rectangle(350,0,220,30));
        txtId.setFont(font0);
        txtId.setEditable(false);
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtId, InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        
        JLabel lbName = new JLabel("Tên Sản Phẩm");
        lbName.setBounds(new Rectangle(250,40,200,30));
        lbName.setFont(font0);
        txtTenSP = new JTextField("");
        txtTenSP.setBounds(new Rectangle(350,40,220,30));
        txtTenSP.setFont(font0);
        txtTenSP.setEditable(false);
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtTenSP, InfoBalloon.filter_all, InfoBalloon.limit_name);


        JLabel lbGia = new JLabel("Đơn giá ($)");
        lbGia.setBounds(new Rectangle(250,80,200,30));
        lbGia.setFont(font0);
        txtGia = new JTextField("");
        txtGia.setBounds(new Rectangle(350,80,220,30));
        txtGia.setFont(font0);
        txtGia.setEditable(false);
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtGia, InfoBalloon.filter_numberOnly, InfoBalloon.limit_price);
        
        JLabel lbCPU = new JLabel("CPU");
        lbCPU.setBounds(new Rectangle(250,120,200,30));
        lbCPU.setFont(font0);
        txtCPU = new JTextField("");
        txtCPU.setBounds(new Rectangle(350,120,220,30));
        txtCPU.setFont(font0);
        txtCPU.setEditable(false);
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtCPU, InfoBalloon.filter_all, InfoBalloon.limit_name);
        
        JLabel lbRAM = new JLabel("RAM");
        lbRAM.setBounds(new Rectangle(250,160,200,30));
        lbRAM.setFont(font0);
        txtRAM = new JTextField("");
        txtRAM.setBounds(new Rectangle(350,160,220,30));
        txtRAM.setFont(font0);
        txtRAM.setEditable(false);
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtRAM, InfoBalloon.filter_all, InfoBalloon.limit_name);
        
        JLabel lbGPU = new JLabel("GPU");
        lbGPU.setBounds(new Rectangle(250,200,200,30));
        lbGPU.setFont(font0);
        txtGPU = new JTextField("");
        txtGPU.setBounds(new Rectangle(350,200,220,30));
        txtGPU.setFont(font0);
        txtGPU.setEditable(false);
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtGPU, InfoBalloon.filter_all, InfoBalloon.limit_name);
        
        JLabel lbManhinh = new JLabel("Màn hình");
        lbManhinh.setBounds(new Rectangle(250,240,200,30));
        lbManhinh.setFont(font0);
        txtManhinh = new JTextField("");
        txtManhinh.setBounds(new Rectangle(350,240,220,30));
        txtManhinh.setFont(font0);
        txtManhinh.setEditable(false);
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtManhinh, InfoBalloon.filter_all, InfoBalloon.limit_name);
        
        JLabel lbOcung = new JLabel("Ổ cứng");
        lbOcung.setBounds(new Rectangle(250,280,200,30));
        lbOcung.setFont(font0);
        txtOcung = new JTextField("");
        txtOcung.setBounds(new Rectangle(350,280,220,30));
        txtOcung.setFont(font0);
        txtOcung.setEditable(false);
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtOcung, InfoBalloon.filter_all, InfoBalloon.limit_name);

        JLabel lbNCC = new JLabel("NCC");
        lbNCC.setBounds(new Rectangle(620,240,50,30));
        lbNCC.setFont(font0);
        cmbNCC = new JComboBox();
        cmbNCC.setEditable(false);
        cmbNCC.setFont(font0);
        cmbNCC.setBounds(new Rectangle(690,240,100,30));
        listNCC(cmbNCC);
        
        JLabel lbNSX = new JLabel("NSX");
        lbNSX.setBounds(new Rectangle(620,280,50,30));
        lbNSX.setFont(font0);
        cmbNSX = new JComboBox();
        cmbNCC.setEditable(false);
        cmbNSX.setFont(font0);
        cmbNSX.setBounds(new Rectangle(690,280,100,30));
        listNSX(cmbNSX);
        
        img = new JLabel("Image");
        img.setBorder(createLineBorder(Color.BLACK));
        img.setBounds(new Rectangle(0,0,200,230));
        
        // THÊM VÀO PHẦN HIỂN THỊ
        ItemView.add(img);
        ItemView.add(lbId);
        ItemView.add(txtId);
        ItemView.add(lbName);
        ItemView.add(txtTenSP);
        ItemView.add(lbGia);
        ItemView.add(txtGia);
        ItemView.add(lbCPU);
        ItemView.add(txtCPU);
        ItemView.add(lbRAM);
        ItemView.add(txtRAM);
        ItemView.add(lbGPU);
        ItemView.add(txtGPU);
        ItemView.add(lbManhinh);
        ItemView.add(txtManhinh);
        ItemView.add(lbOcung);
        ItemView.add(txtOcung);
        ItemView.add(lbNCC);
        ItemView.add(cmbNCC);
        ItemView.add(lbNSX);
        ItemView.add(cmbNSX);
//        ItemView.add(txtNCC);
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
        
        JLabel btnXuatExcel = new JLabel(new ImageIcon("./src/main/java/image/btnXuatExcel.png"));
        btnXuatExcel.setBounds(new Rectangle(820,0,200,50));
        btnXuatExcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel btnNhapExcel = new JLabel(new ImageIcon("./src/main/java/image/btnNhapExcel.png"));
        btnNhapExcel.setBounds(new Rectangle(820,60,200,50));
        btnNhapExcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        ItemView.add(btnConfirm);
        ItemView.add(btnBack);
        ItemView.add(btnFile);
        ItemView.add(btnXuatExcel);
        ItemView.add(btnNhapExcel);
        // toggle editable field
        
        
        // MouseClick btnADD
        btnAdd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                EditOrAdd = true;
                
                cleanView();
                editableStatus(true);
                txtId.setText(spBUS.remindMaLaptop());
                
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                
                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
                btnFile.setVisible(true);
                
                tbl.clearSelection();
                tbl.setEnabled(false);
            }
        });
        
        // MouseClick btnDelete
        btnDelete.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {   
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
                if(i == 0)
                {
                    spBUS.deleteSP(txtId.getText());
                    cleanView();
                    editableStatus(false);
                    tbl.clearSelection();
                    outModel(model, spBUS.getList());
                }
            }
        });
        
        // MouseClick btnEdit
        btnEdit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                if(txtId.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần sửa !!!");
                    return;
                }
                
                EditOrAdd = false;
                
                
                editableStatus(true);
                
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                
                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
                btnFile.setVisible(true);
                
//              tbl.clearSelection();
                tbl.setEnabled(false);
            }
        });
        
        // MouseClick btnFile
        btnFile.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG images", "jpg", "png");
                fc.setFileFilter(filter);
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) 
                {
                    try {
                        File file = fc.getSelectedFile(); //Lấy URL hình
                        i = ImageIO.read(file); // Lấy hình
                        imgName = txtId.getText().concat(".jpg"); //Tên hình
                        
                        // Thay đổi hình hiển thị
                        img.setText("");
                        img.setIcon(new ImageIcon(i.getScaledInstance(200, 230, Image.SCALE_DEFAULT)));
                        
                        revalidate();
                        repaint();
                    } catch (IOException ex) {
                        Logger.getLogger(LaptopUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        //MouseClick btnBack
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                cleanView();
                
                editableStatus(false);
                btnAdd.setVisible(true);
                btnEdit.setVisible(true);
                btnDelete.setVisible(true);
                
                btnConfirm.setVisible(false);
                btnBack.setVisible(false);
                btnFile.setVisible(false);
                
                tbl.setEnabled(true);
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
                        String maSP = txtId.getText();
                        NhaSanXuat nsx = (NhaSanXuat) cmbNSX.getSelectedItem();
                        String maNsx = nsx.getMaNSX();
                        NhaCungCap ncc = (NhaCungCap) cmbNCC.getSelectedItem();
                        String maNcc = ncc.getMaNCC();
                        String tenSP = txtTenSP.getText();
                        int sl = 0;
                        int gia = Integer.parseInt(txtGia.getText());
                        String CPU = txtCPU.getText();
                        String RAM = txtRAM.getText();
                        String GPU = txtGPU.getText();
                        String manhinh = txtManhinh.getText();
                        String ocung = txtOcung.getText();
                        String IMG = imgName;
                        if(spBUS.checkMasp(maSP))
                        {
                            JOptionPane.showMessageDialog(null, "Mã sản phẩm đă tồn tại !!!");
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        Laptop sp = new Laptop(maSP, maNsx, maNcc, tenSP, sl, gia, CPU, RAM, GPU, manhinh, ocung, IMG);
                        spBUS.addSP(sp);

                        outModel(model, spBUS.getList());// Load lại table

                        saveIMG();// Lưu hình ảnh 

                        cleanView();
                        editableStatus(false);
                    }
                }
                else    // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa sản phẩm","",JOptionPane.YES_NO_OPTION);
                    if(i == 0)
                    {
                        //Lấy dữ liệu từ TextField
                        String maSP = txtId.getText();
                        NhaSanXuat nsx = (NhaSanXuat) cmbNSX.getSelectedItem();
                        String maNsx = nsx.getMaNSX();
                        NhaCungCap ncc = (NhaCungCap) cmbNCC.getSelectedItem();
                        String maNcc = ncc.getMaNCC();
                        String tenSP = txtTenSP.getText();
                        int sl = spBUS.getSP(maSP).getSoluong();
                        int gia = Integer.parseInt(txtGia.getText());
                        String CPU = txtCPU.getText();
                        String RAM = txtRAM.getText();
                        String GPU = txtGPU.getText();
                        String manhinh = txtManhinh.getText();
                        String ocung = txtOcung.getText();
                        String IMG = imgName;

                        //Upload sản phẩm lên DAO và BUS
                        Laptop sp = new Laptop(maSP, maNsx, maNcc, tenSP, sl, gia, CPU, RAM, GPU, manhinh, ocung, IMG);
                        spBUS.setSP(sp);
                        
                        outModel(model, spBUS.getList());// Load lại table
                        
                        saveIMG();// Lưu hình ảnh 
                        
                        JOptionPane.showMessageDialog(null, "Sửa thành công","Thành công",JOptionPane.INFORMATION_MESSAGE);
                        editableStatus(false);
                    }
                }
                
            }
        });
        
        
        btnXuatExcel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {   
                spBUS.ExportExcelDatabase();
                JOptionPane.showMessageDialog(null, "Xuat file excel thanh cong");            
            }
        });

        btnNhapExcel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {   
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Excel", "xlsx");
                fc.setFileFilter(filter);
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) 
                {
                    File file = fc.getSelectedFile(); //Lấy URL
                    spBUS.ImportExcelDatabase(file);
                    spBUS.listSP();
                    outModel(model, spBUS.getList());
                    JOptionPane.showMessageDialog(null, "Nhap file excel thanh cong");
                }
            }
        });
        
        /****************************************************************/
        
/************************* PHẦN TABLE *************************************/
/************** TẠO MODEL VÀ HEADER *********************/
        Vector header = new Vector();
        header.add("Mă Sản Phẩm");
        header.add("Mă NSX");
        header.add("Mã NCC");
        header.add("Tên Sản Phẩm");
        header.add("Số lượng");
        header.add("Đơn giá");
        header.add("CPU");
        header.add("RAM");
        header.add("GPU");
        header.add("Màn hình");
        header.add("Ổ cứng");
        header.add("IMG");
        model = new DefaultTableModel(header,0)
        {
             public Class getColumnClass(int column)
             {
                 switch(column){
                     case 2:
                         return Integer.class;
                     case 3:
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
        tbl.getColumnModel().getColumn(0).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(5).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(6).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(7).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(8).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(9).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(10).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(11).setPreferredWidth(60);

        DefaultTableCellRenderer leftAlign = new DefaultTableCellRenderer();
        leftAlign.setHorizontalAlignment(JLabel.LEFT);
        tbl.getColumnModel().getColumn(2).setCellRenderer(leftAlign);
        tbl.getColumnModel().getColumn(3).setCellRenderer(leftAlign);
        
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
        scroll.setBounds(new Rectangle(30, 450, this.DEFALUT_WIDTH - 400 , 200));
        scroll.setBackground(null);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(5,100));
        add(scroll);
        add(ItemView);
/*****************************************************************************************/

        
        tbl.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e)
             {
                int i = tbl.getSelectedRow();
                if(tbl.getRowSorter() != null) {
                    try {
                        i = tbl.getRowSorter().convertRowIndexToModel(i);
                    } catch (Exception ex) {
                            
                    }
                }
                try {
                    imgName = tbl.getModel().getValueAt(i, 11).toString();
                } catch (Exception ex) {
                    
                }
                Image newImage ;
                try {
                    newImage = new ImageIcon("./src/main/java/image/SanPham/"+imgName).getImage().getScaledInstance(200, 230, Image.SCALE_DEFAULT);
                } catch(NullPointerException E)
                {
                    newImage = new ImageIcon("./src/main/java/image/SanPham/NoImage.jpg").getImage().getScaledInstance(200, 230, Image.SCALE_DEFAULT); 
                }
                try {
                    txtId.setText(tbl.getModel().getValueAt(i, 0).toString());
                    cmbNSX.setSelectedItem(nsxBUS.searchMaNsx(tbl.getModel().getValueAt(i, 1).toString()));
                    cmbNCC.setSelectedItem(nccBUS.searchMaNcc(tbl.getModel().getValueAt(i, 2).toString()));
                    txtTenSP.setText(tbl.getModel().getValueAt(i, 3).toString());
                    //txtSl.setText(tbl.getModel().getValueAt(i, 4).toString());
                    txtGia.setText(tbl.getModel().getValueAt(i, 5).toString());
                    txtCPU.setText(tbl.getModel().getValueAt(i, 6).toString());
                    txtRAM.setText(tbl.getModel().getValueAt(i, 7).toString());
                    txtGPU.setText(tbl.getModel().getValueAt(i, 8).toString());
                    txtManhinh.setText(tbl.getModel().getValueAt(i, 9).toString());
                    txtOcung.setText(tbl.getModel().getValueAt(i, 10).toString());


                    img.setText("");
                    img.setIcon(new ImageIcon(newImage));
                } catch (Exception ex) {
                    
                }
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
        txtSearch.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e) 
            {
                searchIcon.setIcon(new ImageIcon("./src/main/java/image/search_25px_focus.png")); //Đổi màu icon
                searchBox.setBorder(createLineBorder(new Color(232,57,99))); // Đổi màu viền 
            }
            public void focusLost(FocusEvent e) //Trờ về như cũ
            {
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
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^"+ text +".*", 1));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^"+ text +".*", 1));
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
        sort.setBounds(30,350,this.DEFALUT_WIDTH - 400,100);

        JLabel sortTitle = new JLabel("___________________________________________________________________________ TÌM KIẾM THÔNG TIN _________________________________________________________________________",JLabel.CENTER); // Mỗi bên 74 dấu ( - )
        sortTitle.setFont(font1);
        sortTitle.setBounds(new Rectangle(0,0,this.DEFALUT_WIDTH - 400,30));
        sort.add(sortTitle);

        /******** SORT MAKH **************/
        JLabel lbSortMaSP = new JLabel("Mă SP :");
        lbSortMaSP.setFont(font0);
        lbSortMaSP.setBounds(0,40,50,30);
        sort.add(lbSortMaSP);

        sortMaSP = new JTextField();
        sortMaSP.setFont(font0);
        sortMaSP.setBounds(new Rectangle(50,42,100,30));
        sortMaSP.addKeyListener(this);
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, sortMaSP,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        sort.add(sortMaSP);
        /*************************************/
        
        /*************************************/
        
        /******** SORT MANSX **************/
        JLabel lbSortMaNSX = new JLabel("Mă NSX :");
        lbSortMaNSX.setFont(font0);
        lbSortMaNSX.setBounds(340,40,60,30);
        sort.add(lbSortMaNSX);

        cmbSortNSX = new JComboBox();
        cmbSortNSX.setFont(font0);
        cmbSortNSX.setBounds(new Rectangle(400,42,100,30));
        cmbSortNSX.addItem("Không");
        cmbSortNSX.addKeyListener(this);
        
        listNSX(cmbSortNSX);
        sort.add(cmbSortNSX);
        
        /*************************************/
        
        /************ SORT THEO GIÁ ***************/
        JLabel sortPrice = new JLabel("Giá (VNĐ) :");
        sortPrice.setFont(font0);
        sortPrice.setBounds(510,40,70,30);
        sort.add(sortPrice);

        txtMinPrice = new JTextField();
        txtMinPrice.setFont(font0);
        txtMinPrice.setBounds(new Rectangle(580,42,100,30));
        txtMinPrice.addKeyListener(this);
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtMinPrice,InfoBalloon.filter_numberOnly, InfoBalloon.limit_price);
        sort.add(txtMinPrice);

        JSeparator sepPrice = new JSeparator(JSeparator.HORIZONTAL);
        sepPrice.setBounds(690, 56, 10, 6);
        sort.add(sepPrice);

        txtMaxPrice = new JTextField();
        txtMaxPrice.setFont(font0);
        txtMaxPrice.setBounds(new Rectangle(710,42,100,30));
        txtMaxPrice.addKeyListener(this);
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtMaxPrice,InfoBalloon.filter_numberOnly, InfoBalloon.limit_price);
        sort.add(txtMaxPrice);
        /******************************************/

        JLabel btnSearch = new JLabel(new ImageIcon("./src/main/java/image/btnSearch_45px.png"));
        btnSearch.setBounds(new Rectangle(840,26,63,63));
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearch.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e)
           {
               search();
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
                File save = new File("src/main/java/image/SanPham/"+ imgName);//Tạo file
                ImageIO.write(i,"jpg",save); // Lưu hình i vào đường dẫn file save

                i = null; //Xóa hình trong bộ nhớ 
            }
        } catch (IOException ex) {
            Logger.getLogger(LaptopUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // toggle editable field
    public void editableStatus(boolean flag) {
        txtId.setEditable(false);
        txtTenSP.setEditable(flag);
        txtGia.setEditable(flag);
        txtCPU.setEditable(flag);
        txtRAM.setEditable(flag);
        txtGPU.setEditable(flag);
        txtManhinh.setEditable(flag);
        txtOcung.setEditable(flag);
    }
    //Xóa trắng các TextField
    public void cleanView() {
        txtId.setText("");
        txtTenSP.setText("");
        txtGia.setText("");
        txtCPU.setText("");
        txtRAM.setText("");
        txtGPU.setText("");
        txtManhinh.setText("");
        txtOcung.setText("");
        img.setIcon(null);
        img.setText("Image");
        imgName = "null";
    }
    public void outModel(DefaultTableModel model , ArrayList<Laptop> sp){ // Xuất ra Table từ ArrayList
        Vector data;
        model.setRowCount(0);
        for(Laptop s:sp){
            data = new Vector();
            data.add(s.getMaLaptop());
            data.add(s.getMaNhaSanXuat());
            data.add(s.getMaNhaCungCap());
            data.add(s.getTen());
            data.add(s.getSoluong());
            data.add(s.getGia());
            data.add(s.getCPU());
            data.add(s.getRAM());
            data.add(s.getGPU());
            data.add(s.getManhinh());
            data.add(s.getOcung());
            data.add(s.getImg());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void listSP(){ // Chép ArrayList lên table
        if(spBUS.getList()== null)spBUS.listSP();
        ArrayList<Laptop> sp = spBUS.getList();
        model.setRowCount(0);
        outModel(model,sp);
    }
    public void listNCC(JComboBox cmb){
        if(nccBUS.getList()== null)nccBUS.listNCC();
        ArrayList<NhaCungCap> ncc = nccBUS.getList();
        addCombo(cmb,ncc);
    }
    public void listNSX(JComboBox cmb){
        if(nsxBUS.getList()== null)nsxBUS.listNSX();
        ArrayList<NhaSanXuat> nsx = nsxBUS.getList();
        addCombo(cmb,nsx);
    }
    public void addCombo(JComboBox cmb,ArrayList list){
        for(Object a:list){
            cmb.addItem(a);
        }
    }

    public void search() {
        String masp = sortMaSP.getText();
        String mansx = "";
        if(cmbSortNSX.getSelectedIndex() != 0)
        {
             NhaSanXuat nsx = (NhaSanXuat) cmbSortNSX.getSelectedItem();
             mansx = nsx.getMaNSX();
             System.out.println(mansx);
        }
        int max = txtMaxPrice.getText().equals("") ? 999999 : Integer.parseInt(txtMaxPrice.getText());
        int min = txtMinPrice.getText().equals("") ? 0      : Integer.parseInt(txtMinPrice.getText());

        outModel(model,spBUS.searchSP(masp, mansx, max, min));
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        Object a = e.getSource();
        if(a.equals(sortMaSP) || a.equals(txtMinPrice) || a.equals(txtMaxPrice) 
                || a.equals(cmbSortNCC))
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                search();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
