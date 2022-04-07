/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UI;

import BUS.*;
import DTO.ChiTietHoaDon;
import DTO.HoaDon;
import DTO.Laptop;
import UI.bone.Maintainance;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author WindZ
 */

public class BanHangUI extends JPanel implements ActionListener,KeyListener {
    private String userID;
    private LaptopBUS spBUS = new LaptopBUS();
    private HoaDonBUS hdBUS = new HoaDonBUS();
    private NhanVienBUS nvBUS = new NhanVienBUS(1);
    private KhachHangBUS khBUS = new KhachHangBUS(1);
    private ChiTietHoaDonBUS ctBUS = new ChiTietHoaDonBUS(1);
    
    private ArrayList<ChiTietHoaDon> dsct = new ArrayList<>();
    private int DEFALUT_WIDTH;
    private JTextField txtMaHD;
    private JTextField txtMaKH;
    private JTextField txtMaNV;
    private JTextField txtNgayHD;
    private JTextField txtTongTien;
    private JButton btnMaNV;
    private JTextField txtMaSP;
    private JTextField txtCTSL;
    private JButton btnMaSP;
    private JTextField txtCTGia;
    private JTextField txtCTTenSP;
    private JButton btnAddCT;
    private JButton btnNewHD;
    private JButton btnConfirm;
    private DefaultTableModel model;
    private JTable tbl;
    private JLabel imgSP;
    private JPanel chiTietView;
    private JButton btnDeleteHD;
    private JButton btnEdit;
    private JButton btnRemove;
    private Maintainance page;
    private JButton btnMaKH;
    
     public BanHangUI(int width,String userID)
    {
        this.userID = userID;
        DEFALUT_WIDTH = width;
        hdBUS.list();
        spBUS.listSP();
        init();
    }
    public BanHangUI(int width)
    {
        DEFALUT_WIDTH = width;
        hdBUS.list();
        spBUS.listSP();
        init();
    }
    public void init()
    {   
        setLayout(null);
        setBackground(null);
        setBounds(new Rectangle(50, 0, this.DEFALUT_WIDTH - 220, 1000));
        Font font0 = new Font("Segoe UI",Font.PLAIN,14);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
        
/*********************** PHẦN VIEW THÔNG TIN HD *****************************/
        
        JPanel hdView = new JPanel(null);
        hdView.setBackground(null);
        hdView.setBounds(new Rectangle(30,40,this.DEFALUT_WIDTH - 350,150));

        JLabel lbMaHD = new JLabel("Mã HD");
        lbMaHD.setFont(font0);
        lbMaHD.setBounds(0,0,55,30);
        txtMaHD = new JTextField(hdBUS.remindMaHD());
        txtMaHD.setHorizontalAlignment(JTextField.CENTER);
        txtMaHD.setFont(font0);
        txtMaHD.setEnabled(false);
        txtMaHD.setBounds(new Rectangle(55,0,120,30));
        txtMaHD.addKeyListener(this);
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtMaHD,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        hdView.add(lbMaHD);
        hdView.add(txtMaHD);

        JLabel lbMaKH = new JLabel("Mã KH");
        lbMaKH.setFont(font0);
        lbMaKH.setBounds(195,0,60,30);
        txtMaKH = new JTextField("");
        txtMaKH.setHorizontalAlignment(JTextField.CENTER);
        txtMaKH.setFont(font0);
        txtMaKH.setBounds(new Rectangle(255,0,100,30));
        txtMaKH.addKeyListener(this);
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtMaKH,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        hdView.add(lbMaKH);
        hdView.add(txtMaKH);
        btnMaKH = new JButton("...");
        btnMaKH.setBounds(new Rectangle(355,0,30,30));
        btnMaKH.addActionListener(this);
        hdView.add(btnMaKH);

        JLabel lbMaNV = new JLabel("Mã NV");
        lbMaNV.setFont(font0);
        lbMaNV.setBounds(415,0,60,30);
        txtMaNV = new JTextField(4);
        if( userID != null ) txtMaNV.setText(userID);
        txtMaNV.setHorizontalAlignment(JTextField.CENTER);
        txtMaNV.setFont(font0);
        txtMaNV.setBounds(new Rectangle(475,0,100,30));
        txtMaNV.addKeyListener(this);
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtMaNV,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        hdView.add(lbMaNV);
        hdView.add(txtMaNV);
        btnMaNV = new JButton("...");
        btnMaNV.setBounds(new Rectangle(575,0,30,30));
        btnMaNV.addActionListener(this);
        hdView.add(btnMaNV);
        
        JLabel lbTongTien = new JLabel("Tổng Tiền (VNĐ)");
        lbTongTien.setFont(font0);
        lbTongTien.setBounds(625,0,120,30);
        txtTongTien = new JTextField("0");
        txtTongTien.setEditable(false);
        txtTongTien.setHorizontalAlignment(JTextField.CENTER);
        txtTongTien.setFont(font0);
        txtTongTien.setBounds(new Rectangle(745,0,150,30));
        hdView.add(lbTongTien);
        hdView.add(txtTongTien);

        JLabel lbNgayHD = new JLabel("Ngày HD");
        lbNgayHD.setFont(font0);
        lbNgayHD.setBounds(0,50,60,30);
        txtNgayHD = new JTextField();
        txtNgayHD.setHorizontalAlignment(JTextField.CENTER);
        txtNgayHD.setFont(font0);
        txtNgayHD.setBounds(new Rectangle(80,50,350,30));
        txtNgayHD.setEditable(false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Date date = Timestamp.valueOf(formatter.format((LocalDateTime.now())));
        txtNgayHD.setText(date.toString().replace(".0",""));
        hdView.add(lbNgayHD);
        ActionListener task = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                Date date = Timestamp.valueOf(formatter.format((LocalDateTime.now())));
                txtNgayHD.setText(date.toString().replace(".0",""));
            }
        };
        Timer timer = new Timer(1000 ,task);
        timer.setRepeats(true);
        timer.start();
        hdView.add(txtNgayHD);
        
        btnNewHD = new JButton("Tạo hóa đơn");
        btnNewHD.setFont(font0);
        btnNewHD.setBounds(new Rectangle(500,50,200,30));
        btnNewHD.addActionListener(this);
        hdView.add(btnNewHD);
        
        btnConfirm = new JButton("Xác nhận");
        btnConfirm.setFont(font0);
        btnConfirm.addActionListener(this);
        btnConfirm.setVisible(false);
        btnConfirm.setBounds(new Rectangle(500,50,150,30));
        hdView.add(btnConfirm);
        
        btnDeleteHD = new JButton("Xóa");
        btnDeleteHD.setFont(font0);
        btnDeleteHD.setBounds(new Rectangle(700,50,150,30));
        btnDeleteHD.addActionListener(this);
        btnDeleteHD.setVisible(false);
        hdView.add(btnDeleteHD);
        
        JSeparator sepHD = new JSeparator(0);
        sepHD.setBounds(new Rectangle(0,120,this.DEFALUT_WIDTH - 350,3));
        hdView.add(sepHD);      
                
        add(hdView);
        
/****************************************************************************/
/*********************** PHẦN VIEW THÔNG TIN CHI TIẾT *****************************/
        
        page = new Maintainance(WIDTH, "      Tạo hóa đơn");
        page.setBounds(new Rectangle(100,-20,DEFALUT_WIDTH - 60,500));
        add(page);
        
        chiTietView = new JPanel(null);
        chiTietView.setVisible(false);
        chiTietView.setBackground(null);
        chiTietView.setBounds(new Rectangle(30,190,DEFALUT_WIDTH - 60,500));
        
        imgSP = new JLabel();
        imgSP.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,new Color(155,155,155)));
        imgSP.setBounds(new Rectangle(0,0,280,230));
        imgSP.setHorizontalAlignment(JLabel.CENTER);
        chiTietView.add(imgSP);

        JLabel lbMaSP = new JLabel("Mã Laptop");
        lbMaSP.setFont(font0);
        lbMaSP.setBounds(0,240,55,30);
        txtMaSP = new JTextField();
        txtMaSP.setHorizontalAlignment(JTextField.CENTER);
        txtMaSP.setFont(font0);
        txtMaSP.setBounds(new Rectangle(60,240,70,30));
        txtMaSP.addKeyListener(this);
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtMaSP,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        chiTietView.add(lbMaSP);
        chiTietView.add(txtMaSP);
        btnMaSP = new JButton("...");
        btnMaSP.setBounds(new Rectangle(130,240,30,30));
        btnMaSP.addActionListener(this);
        chiTietView.add(btnMaSP);
        
        JLabel lbCTTenSP = new JLabel("Tên Laptop");
        lbCTTenSP.setFont(font0);
        lbCTTenSP.setBounds(0,280,50,30);
        txtCTTenSP = new JTextField();
        txtCTTenSP.setEditable(false);
        txtCTTenSP.setHorizontalAlignment(JTextField.CENTER);
        txtCTTenSP.setFont(font0);
        txtCTTenSP.setBounds(new Rectangle(60,280,220,30));
        chiTietView.add(lbCTTenSP);
        chiTietView.add(txtCTTenSP);

        JLabel lbCTGia = new JLabel("Đơn giá");
        lbCTGia.setFont(font0);
        lbCTGia.setBounds(0,320,60,30);
        txtCTGia = new JTextField();
        txtCTGia.setEditable(false);
        txtCTGia.setHorizontalAlignment(JTextField.CENTER);
        txtCTGia.setFont(font0);
        txtCTGia.setBounds(new Rectangle(60,320,220,30));
        chiTietView.add(lbCTGia);
        chiTietView.add(txtCTGia);

        JLabel lbCTSL = new JLabel("Số lượng");
        lbCTSL.setFont(font0);
        lbCTSL.setBounds(170,240,60,30);
        txtCTSL = new JTextField();
        txtCTSL.setHorizontalAlignment(JTextField.CENTER);
        txtCTSL.setFont(font0);
        txtCTSL.addKeyListener(this);
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtCTSL,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        txtCTSL.setBounds(new Rectangle(230,240,50,30));
        chiTietView.add(lbCTSL);
        chiTietView.add(txtCTSL);
        
        btnAddCT = new JButton("Thêm");
        btnAddCT.setFont(font0);
        btnAddCT.addActionListener(this);
        btnAddCT.setBounds(new Rectangle(0,360,80,30));
        chiTietView.add(btnAddCT);
        
        btnEdit = new JButton("Sửa");
        btnEdit.setFont(font0);
        btnEdit.addActionListener(this);
        btnEdit.setBounds(new Rectangle(300,420,150,40));
        chiTietView.add(btnEdit);
        
        btnRemove = new JButton("Xóa");
        btnRemove.setFont(font0);
        btnRemove.addActionListener(this);
        btnRemove.setBounds(new Rectangle(500,420,150,40));
        chiTietView.add(btnRemove);
        
/************************* PHẦN TABLE *************************************/
/************** TẠO MODEL VÀ HEADER *********************/
        
        Vector header = new Vector();
        header.add("Mă Sản Phẩm");
        header.add("Tên Sản Phẩm");
        header.add("Số lượng");
        header.add("Đơn Giá");
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
        
/*********************************************************/
        
/**************** TẠO TABLE ************************************************************/

        // Chỉnh width các cột 
        
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(140);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(50);

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
        scroll.setBounds(new Rectangle(300, 0, this.DEFALUT_WIDTH - 650 , 400));
        scroll.setBackground(null);
        
        chiTietView.add(scroll);
        
/*****************************************************************************************/
/****************************************************************************/
        
        add(chiTietView); 
        setVisible(true);
    }     

    public void outModel(DefaultTableModel model , ArrayList<ChiTietHoaDon> ds) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(ChiTietHoaDon ct:ds) {
            data = new Vector();
            data.add(ct.getMaLaptop());
            String tenLaptop = spBUS.getSP(ct.getMaLaptop()).getTen();
            data.add(tenLaptop);
            data.add(ct.getSl());
            data.add(ct.getGia());
            
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public int sumHD()
    {   
        int sum = 0;
        for(ChiTietHoaDon sp : dsct) {
            int sl = sp.getSl();
            int gia = sp.getGia();
            sum += sl*gia;
        }
        return sum;
    }
    public void blockHD(boolean flag)
    {
        txtMaHD.setEditable(false);
        txtMaKH.setEditable(flag);
        txtMaNV.setEditable(flag);
        txtNgayHD.setEditable(false);
        btnMaNV.setEnabled(flag);
    }
    public void clear(boolean flag)
    {
        if(flag)
        {
            // PHẦN HÓA ĐƠN
            txtMaHD.setText(hdBUS.remindMaHD());
            txtMaKH.setText("");
            txtMaNV.setText("");
            if( userID != null ) txtMaNV.setText(userID);
            txtTongTien.setText("0");
            txtNgayHD.setText("");

            //PHẦN CHITIET
            dsct.removeAll(dsct);
            txtMaSP.setText("");
            txtCTTenSP.setText("");
            txtCTSL.setText("");
            txtCTGia.setText("");
            imgSP.setIcon(null);

            model.getDataVector().removeAllElements(); //Xóa trằng table
        }
    }
    public void reset(boolean flag)
    {
        btnNewHD.setVisible(flag);
        btnConfirm.setVisible(!flag);
        btnDeleteHD.setVisible(!flag);
        clear(flag);
        blockHD(flag);

        chiTietView.setVisible(!flag);
        page.setVisible(flag);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnMaNV){ // Suggest Nhan Vien
            SuggestNhanVien rm = new SuggestNhanVien();
            String s = rm.getTextFieldContent();
            txtMaNV.setText(s);
        }
        if(e.getSource() == btnMaKH){ // Suggest Khach hang
        
            SuggestKhachHang rm = new SuggestKhachHang();
            String s = rm.getTextFieldContent();
            txtMaKH.setText(s);
        }
        if(e.getSource().equals(btnMaSP)){ // Suggest San Pham
        
            // Lấy data và gắn lên TextField vs Hình
            SuggestSanPham rm = new SuggestSanPham(txtMaSP.getText());
            String[] s = rm.getTextFieldContent().split("%");
            txtMaSP.setText(s[0]);
            txtCTTenSP.setText(s[1]);
            txtCTSL.setText(s[2]);
            txtCTGia.setText(s[3]);
            
            Image newImage ;
            try{
                newImage = new ImageIcon("./src/main/java/image/SanPham/"+s[4]).getImage().getScaledInstance(200, 230, Image.SCALE_DEFAULT);
            } catch(NullPointerException E) {
                newImage = new ImageIcon("./src/main/java/image/SanPham/NoImage.jpg").getImage().getScaledInstance(200, 230, Image.SCALE_DEFAULT); 
            }
            imgSP.setIcon(new ImageIcon(newImage));
            
            //Chỉnh Focus vào txtCTSL
            txtCTSL.requestFocus();
        }
        if(e.getSource().equals(btnAddCT)) // Thêm Sản Phẩm
        {
            int sl = 0;
            try{
                sl = Integer.parseInt(txtCTSL.getText()); 
            }catch(NumberFormatException E)
            {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng");
                return;
            }
            // Kiểm tra số lượng
            
            int gia = Integer.parseInt(txtCTGia.getText());
            System.out.println(sl);
            
            //Kiểm tra đã có trong giỏ chưa
            boolean flag = true;
            
            for(ChiTietHoaDon sp : dsct) {
                if(sp.getMaLaptop().equals(txtMaSP.getText())) {
                    int old = sp.getSl();
                    if(spBUS.checkSL(txtMaSP.getText(), sl + old)) {
                        return;
                    }
                    sp.setSl(sl + old);
                    flag = false ;
                    break;
                }
            }
            if(flag) {
                if(spBUS.checkSL(txtMaSP.getText(), sl)) {
                    return;
                }
                dsct.add(new ChiTietHoaDon(txtMaHD.getText(), txtMaSP.getText(), sl, gia));
            }
            outModel(model, dsct);
            txtTongTien.setText(String.valueOf(sumHD()));
        }
        
        if(e.getSource().equals(btnNewHD))  // Tạo hóa đơn
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Date date = Timestamp.valueOf(formatter.format((LocalDateTime.now())));
            
            if(txtMaHD.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã hóa đơn");
                txtMaHD.requestFocus();
                return;
            }
            else if(hdBUS.check(txtMaHD.getText()))
            {
                JOptionPane.showMessageDialog(null, "Mã hóa đơn đă tồn tại");
                txtMaHD.requestFocus();
                txtMaHD.setText(hdBUS.remindMaHD());
                return;
            }
            System.out.println(txtMaNV.getText());
            System.out.println(nvBUS.getList().size());
            if(!txtMaKH.getText().isEmpty() && !khBUS.check(txtMaKH.getText())) {
                JOptionPane.showMessageDialog(null, "Mã khách hàng không tồn tại");
                txtMaKH.requestFocus();
                return;
            }
            
            if(txtMaNV.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên");
                txtMaNV.requestFocus();
                return;
            }
            else if(!nvBUS.check(txtMaNV.getText()))
            {
                JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại");
                txtMaNV.requestFocus();
                return;
            }
            txtNgayHD.setText(date.toString().replace(".0",""));

            reset(false);
            
            txtMaSP.requestFocus();
        }
        if(e.getSource().equals(btnDeleteHD)){ //Xóa HD 
            reset(true);
        }
        if(e.getSource().equals(btnConfirm)){ //Xác nhận
        
            if(dsct.isEmpty()){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập sản phẩm");
                return;
            }
            String maHD = txtMaHD.getText();
            String maKH = txtMaKH.getText();
            String maNV = txtMaNV.getText();
            String ngayHD = txtNgayHD.getText();
            String maCtkm = "";
            int tongTien = Integer.parseInt(txtTongTien.getText());
            HoaDon hd = new HoaDon(maHD, maKH, maNV, ngayHD, maCtkm, tongTien);
            
            hdBUS.add(hd);
            for(ChiTietHoaDon ct : dsct) {
                ctBUS.add(ct);
                spBUS.updateSL(ct.getMaLaptop(),ct.getSl());
            }
            
            outBill bill = new outBill(hd, dsct);
            bill.print();
            reset(true);
        }
        if(e.getSource().equals(btnEdit)){ //Sửa sl trong Chitiet sp
            try {
                int i = tbl.getSelectedRow();
                if(tbl.getRowSorter() != null){
                    try {
                        i = tbl.getRowSorter().convertRowIndexToModel(i);
                    } catch (Exception ex) {
                        
                    }
                }
                String masp = tbl.getModel().getValueAt(i, 0).toString();
                int sl = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm :"));
                while(spBUS.checkSL(masp, sl)) {
                    sl = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm :"));
                }
                for(ChiTietHoaDon ct : dsct){
                    if(ct.getMaLaptop().equals(masp)) {
                        ct.setSl(sl);
                    }
                }
                outModel(model, dsct);
            } catch(IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Chưa chọn SP cần sửa");
            }
        }
        // Xóa SP trong CT SP
        if(e.getSource().equals(btnRemove)) {
            try {
                int i = tbl.getSelectedRow();
                if(tbl.getRowSorter() != null) {
                    try {
                        i = tbl.getRowSorter().convertRowIndexToModel(i);
                    }catch (Exception ex) {
                       
                    }
                }
                dsct.remove(i);
                model.removeRow(i);
                txtTongTien.setText(String.valueOf(sumHD()));
            } catch(IndexOutOfBoundsException ex){
                JOptionPane.showMessageDialog(null, "Chưa chọn SP cần xóa");
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        Object a = e.getSource();
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(a.equals(txtMaHD) || a.equals(txtMaKH) || a.equals(txtMaNV)){ //Enter TXT ở Hóa Đơn
                btnNewHD.doClick();
            }
            else if(a.equals(txtMaSP)){ //Enter MASP
                try {
                    Laptop sp = spBUS.getSP(txtMaSP.getText());
                    Image img = new ImageIcon("./src/main/java/image/SanPham/"+sp.getImg()).getImage().getScaledInstance(200, 230, Image.SCALE_DEFAULT);
                    imgSP.setIcon(new ImageIcon(img));
                    txtCTTenSP.setText(sp.getTen());
                    txtCTGia.setText(String.valueOf(sp.getGia()));
                } catch(NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Mã sản phẩm không tồn tại !!");
                }
            }
            else if(a.equals(txtCTSL)){ //Enter SL
                btnAddCT.doClick();
            }
        }
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
}

