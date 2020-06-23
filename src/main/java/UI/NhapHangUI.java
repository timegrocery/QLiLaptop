/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BUS.NhanVienBUS;
import DTO.NhanVien;
import BUS.NhapHangBUS;
import DTO.NhapHang;
import DTO.Laptop;
import DAL.LaptopDAO;
import BUS.LaptopBUS;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import BUS.Handler;
import BUS.InfoBalloon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author WindZ
 */
public class NhapHangUI extends JPanel implements ActionListener,FocusListener,MouseListener {
    private NhapHangBUS nhBUS = new NhapHangBUS();
    private NhanVienBUS nvBUS = new NhanVienBUS(1);
    private LaptopBUS spBUS = new LaptopBUS();
    private LaptopDAO spDAO = new LaptopDAO();
    
    private String userID;
    private int DWIDTH;
    private JTextField txtMaPN;
    private JTextField txtMaNCC;
    private JTextField txtMaNV;
    private JTextField txtNgayNhap;
    private JTextField txtMaLaptop;
    private JTextField txtDonGia;
    private JTextField txtSoLuong;
    private JTextField txtTongTien;
    
    private DefaultTableModel model;
    private JTable tbl;
    
    private JLabel btnAdd;
    private JLabel btnDelete;
    private JLabel btnConfirm;
    private JLabel btnBack;
    private JButton btnSuggestSP;
    private JButton btnSuggestNCC;
    private JButton btnSuggestNV;
    
    private boolean EditOrAdd = false;//Cờ cho button Cofirm  True:ADD || False:Edit
    public NhapHangUI(int width, String userID) {
        this.DWIDTH = width;
        this.userID = userID;
        spBUS.listSP();
        init();
    }
    public void init()
    {
        setSize(DWIDTH,700);
        setLayout(null);
        
        Font ftitle = new Font("Segoe UI",Font.BOLD,25);
        Font font0 = new Font("Segoe UI",Font.PLAIN,14);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
        
        //HEADER
/***************** PHẦN HIỂN THỊ THÔNG TIN ***************************/
        JPanel itemView = new JPanel(null);
        itemView.setBounds(new Rectangle(0, 0,this.DWIDTH, 700));
        itemView.setBackground(Color.WHITE);
        
        JLabel lbMaPN = new JLabel("Mã PN");
        lbMaPN.setFont(font0);
        lbMaPN.setBounds(20,20,100,30);
        txtMaPN = new JTextField();
        txtMaPN.setFont(font0);
        txtMaPN.addFocusListener(this);
        txtMaPN.setBounds(new Rectangle(120,20,250,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtMaPN,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        itemView.add(lbMaPN);
        itemView.add(txtMaPN);
        
        JLabel lbMaNCC = new JLabel("Mã nhà CC ");
        lbMaNCC.setFont(font0);
        lbMaNCC.setBounds(20,70,100,30);
        txtMaNCC = new JTextField();
        txtMaNCC.setFont(font0);
        txtMaNCC.setBounds(new Rectangle(120,70,220,30));
        btnSuggestNCC = new JButton("...");
        btnSuggestNCC.setFont(font0);
        btnSuggestNCC.addActionListener(this);
        btnSuggestNCC.setBounds(new Rectangle(340,70,30,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtMaNCC,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        itemView.add(lbMaNCC);
        itemView.add(txtMaNCC);
        itemView.add(btnSuggestNCC);
        
        JLabel lbMaNV = new JLabel("Mã Nhân Viên");
        lbMaNV.setFont(font0);
        lbMaNV.setBounds(20,120,100,30);
        
        txtMaNV = new JTextField();
        txtMaNV.setFont(font0);
        txtMaNV.setBounds(new Rectangle(120,120,220,30));
        if(Handler.isNullOrEmpty(userID)) txtMaNV.setText(userID);
        btnSuggestNV = new JButton("...");
        btnSuggestNV.setFont(font0);
        btnSuggestNV.addActionListener(this);
        btnSuggestNV.setBounds(new Rectangle(340,120,30,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtMaNV,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        itemView.add(lbMaNV);
        itemView.add(txtMaNV);
        itemView.add(btnSuggestNV);
        
        
        JLabel lbNgayNhap = new JLabel("Ngày Nhập");
        lbNgayNhap.setFont(font0);
        lbNgayNhap.setBounds(20,170,100,30);
        txtNgayNhap = new JTextField();
        txtNgayNhap.setFont(font0);
        txtNgayNhap.addFocusListener(this);
        txtNgayNhap.setBounds(new Rectangle(120,170,250,30));
        itemView.add(lbNgayNhap);
        itemView.add(txtNgayNhap);
        
        JLabel lbMaLaptop = new JLabel("Mã Laptop");
        lbMaLaptop.setFont(font0);
        lbMaLaptop.setBounds(20,220,100,30);
        txtMaLaptop = new JTextField();
        txtMaLaptop.setFont(font0);
        txtMaLaptop.setBounds(new Rectangle(120,220,220,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtMaLaptop,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        btnSuggestSP = new JButton("...");
        btnSuggestSP.setFont(font0);
        btnSuggestSP.addActionListener(this);
        btnSuggestSP.setBounds(new Rectangle(340,220,30,30));
        itemView.add(lbMaLaptop);
        itemView.add(txtMaLaptop);
        itemView.add(btnSuggestSP);
        
        JLabel lbDonGia = new JLabel("Đơn giá");
        lbDonGia.setFont(font0);
        lbDonGia.setBounds(20,270,100,30);
        txtDonGia = new JTextField();
        txtDonGia.setFont(font0);
        txtDonGia.setBounds(new Rectangle(120,270,250,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtDonGia,InfoBalloon.filter_numberOnly, InfoBalloon.limit_price);
        itemView.add(lbDonGia);
        itemView.add(txtDonGia);
        
        JLabel lbSoLuong = new JLabel("Số lượng");
        lbSoLuong.setFont(font0);
        lbSoLuong.setBounds(20,320,100,30);
        txtSoLuong = new JTextField();
        txtSoLuong.setFont(font0);
        txtSoLuong.setBounds(new Rectangle(120,320,250,30));
        itemView.add(lbSoLuong);
        itemView.add(txtSoLuong);
        
        JLabel lbTongTien = new JLabel("Tổng tiền");
        lbTongTien.setFont(font0);
        lbTongTien.setBounds(20,370,100,30);
        txtTongTien = new JTextField();
        txtTongTien.setFont(font0);
        txtTongTien.addFocusListener(this);
        txtTongTien.setBounds(new Rectangle(120,370,250,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtTongTien,InfoBalloon.filter_numberOnly, InfoBalloon.limit_price);
        itemView.add(lbTongTien);
        itemView.add(txtTongTien);
        
        setEditable(false);
        
        KeyListener keyListener = new KeyListener() {
            public void keyPressed(KeyEvent keyEvent) {
                txtTongTien.setText(Integer.toString(sumHD()));
            }

            public void keyReleased(KeyEvent keyEvent) {
                txtTongTien.setText(Integer.toString(sumHD()));
            }

            public void keyTyped(KeyEvent keyEvent) {
                txtTongTien.setText(Integer.toString(sumHD()));
            }
        };
        txtSoLuong.addKeyListener(keyListener);
        txtDonGia.addKeyListener(keyListener);
        ActionListener task = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                Date date = Timestamp.valueOf(formatter.format((LocalDateTime.now())));
                txtNgayNhap.setText(date.toString().replace(".0",""));
            }
        };
        Timer timer = new Timer(1000 ,task);
        timer.setRepeats(true);
/**************** TẠO CÁC BTN XÓA, SỬA, VIEW, IN BILL ********************/

        btnAdd = new JLabel(new ImageIcon("./src/main/java/image/btnAdd_150px.png"));
        btnAdd.setBounds(new Rectangle(20,420,150,50));
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdd.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                isAddSet(true);
                EditOrAdd = true;
                txtMaPN.setText(nhBUS.remindMaNH());
                
                txtMaNV.setText(userID);
                tbl.setEnabled(false);
                timer.start();
                setEditable(true);
            }
        });
              
        btnDelete = new JLabel(new ImageIcon("./src/main/java/image/btnDelete_150px.png"));
        btnDelete.setBounds(new Rectangle(180,420,150,50));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));    
        btnDelete.addMouseListener(this);
        
        btnConfirm = new JLabel(new ImageIcon("./src/main/java/image/btnConfirm_150px.png"));
        btnConfirm.setBounds(new Rectangle(20,420,150,50));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirm.setVisible(false);
        btnConfirm.addMouseListener(this);
        btnConfirm.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                tbl.setEnabled(false);
                timer.stop();
            };
        });
              
        btnBack = new JLabel(new ImageIcon("./src/main/java/image/btnBack_150px.png"));
        btnBack.setBounds(new Rectangle(180,420,150,50));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));  
        btnBack.setVisible(false);
        btnBack.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                isAddSet(false);
                EditOrAdd = false;
                
                timer.stop();
                setEditable(false);
                tbl.setEnabled(true);
                clean();
            }
        });
        
        itemView.add(btnAdd);
        itemView.add(btnDelete);
        itemView.add(btnConfirm);
        itemView.add(btnBack);
        
/*************************************************************************/
/**************** TẠO TABLE ************************************************************/

    /************** TẠO MODEL VÀ HEADER *********************************/
        Vector header = new Vector();
        header.add("Mã PN");
        header.add("Mã NCC");
        header.add("Mã NV");
        header.add("Ngày nhập");
        header.add("Mã Laptop");
        header.add("Số lượng");
        header.add("Đơn giá");
        header.add("Tổng tiền");
        model = new DefaultTableModel(header,5);
        tbl = new JTable(model);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        tbl.setRowSorter(rowSorter);
        list();
        
    /*******************************************************************/
        

    /******** CUSTOM TABLE ****************/
    
        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(70);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(70);
        tbl.getColumnModel().getColumn(5).setPreferredWidth(70);
        tbl.getColumnModel().getColumn(6).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(7).setPreferredWidth(70);

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
        scroll.setBounds(new Rectangle(400, 20, DWIDTH - 650 , 500));
        scroll.setBackground(null);
        
        itemView.add(scroll);
        
        add(itemView);
    /**************************************/
/*****************************************************************************************/
        tbl.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e)
             {
                int i = tbl.getSelectedRow();
                if(tbl.getRowSorter() != null)
                {
                    try {
                        i = tbl.getRowSorter().convertRowIndexToModel(i);
                    } catch (Exception ex){
                        
                    }
                }
                txtMaPN.setText(tbl.getModel().getValueAt(i, 0).toString());
                txtMaNCC.setText(tbl.getModel().getValueAt(i, 1).toString());
                txtMaNV.setText(tbl.getModel().getValueAt(i, 2).toString());
                txtNgayNhap.setText(tbl.getModel().getValueAt(i, 3).toString());
                txtMaLaptop.setText(tbl.getModel().getValueAt(i, 4).toString());
                txtSoLuong.setText(tbl.getModel().getValueAt(i, 5).toString());
                txtDonGia.setText(tbl.getModel().getValueAt(i, 6).toString());
                txtTongTien.setText(tbl.getModel().getValueAt(i, 7).toString());         
             }
        });
/*********************************************************************/
    }
    public void outModel(DefaultTableModel model , ArrayList<NhapHang> nhaphang) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(NhapHang nh : nhaphang) {
            data = new Vector();
            data.add(nh.getMaPN());
            data.add(nh.getMaNCC());
            data.add(nh.getMaNV());
            data.add(nh.getNgayNhap());
            data.add(nh.getMaLaptop());
            data.add(nh.getSoluong());
            data.add(nh.getDongia());
            data.add(nh.getTongTien());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void list() // Chép ArrayList lên table
    {
        if(nhBUS.getList() == null)nhBUS.list();
        ArrayList<NhapHang> nh = nhBUS.getList();
//        model.setRowCount(0);
        outModel(model,nh);
    }
    public void isAddSet(boolean flag) {
        btnAdd.setVisible(!flag);
        btnDelete.setVisible(!flag);
        
        btnConfirm.setVisible(flag);
        btnBack.setVisible(flag);
        
        clean();
    }

    public void clean()
    {
        txtMaPN.setText("");
        txtMaNCC.setText("");
        txtMaNV.setText("");
        txtNgayNhap.setText("");
        txtMaLaptop.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
        txtTongTien.setText("");
    }
    public void setEditable(boolean flag) {
        txtMaPN.setEditable(false);
        txtMaNCC.setEditable(false);
        txtMaNV.setEditable(false);
        txtNgayNhap.setEditable(false);
        txtMaLaptop.setEditable(false);
        txtSoLuong.setEditable(flag);
        txtDonGia.setEditable(flag);
        txtTongTien.setEditable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj.equals(btnSuggestSP)) { // Suggest san pham
            SuggestSanPham sp = new SuggestSanPham(txtMaLaptop.getText());
            String s = sp.getTextFieldContent();
            txtMaLaptop.setText(s.split("%")[0]);
        }
        if(obj.equals(btnSuggestNV)){ // Suggest Nhan Vien
            SuggestNhanVien rm = new SuggestNhanVien();
            String s = rm.getTextFieldContent();
            txtMaNV.setText(s);
        }
        if(obj.equals(btnSuggestNCC)){ // Suggest nhà cung cấp
            SuggestNhaCungCap rm = new SuggestNhaCungCap();
            String s = rm.getTextFieldContent();
            txtMaNCC.setText(s);
        }
    }
    

    @Override
    public void focusGained(FocusEvent e) {
        Object obj = e.getSource();
        if(obj.equals(txtNgayNhap)){
            if(EditOrAdd && txtNgayNhap.isEditable()== true)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Calendar date = Calendar.getInstance();
                txtNgayNhap.setText(sdf.format(date.getTime()));
            }
        }
        if(obj.equals(txtTongTien))
        {
            if(EditOrAdd)
            {
                try{
                    int sl = Integer.parseInt(txtSoLuong.getText());
                    int gia = Integer.parseInt(txtDonGia.getText());
                    int sum = sl*gia;
                    txtTongTien.setText(String.valueOf(sum));
                }catch(NumberFormatException ex)
                {
                    txtDonGia.requestFocus();
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá và số lượng ");
                }
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if(obj.equals(btnConfirm))
        {
            if(EditOrAdd)
            {
                String maPN = txtMaPN.getText();
                String maNCC = txtMaNCC.getText();
                String maNV = txtMaNV.getText();
                String ngayNhap = txtNgayNhap.getText();
                String maLaptop = txtMaLaptop.getText();
                int soluong = Integer.parseInt(txtSoLuong.getText());
                int dongia = Integer.parseInt(txtDonGia.getText());
                int tongTien = Integer.parseInt(txtTongTien.getText());
                NhapHang nh = new NhapHang(maPN, maNCC, maNV, ngayNhap, maLaptop, soluong, dongia, tongTien);
                nhBUS.add(nh);
                outModel(model, nhBUS.getList());
                Laptop currentLaptop = spBUS.getSP(maLaptop);
                int oldQuantity = currentLaptop.getSoluong();
                int newQuantity = oldQuantity + soluong;
                spBUS.updateSLValue(currentLaptop.getMaLaptop(), newQuantity);
                JOptionPane.showMessageDialog(null, "Nhập hàng thành công");
            }
            
            isAddSet(false);
            EditOrAdd = false;
            setEditable(false);
        }
        
        if(obj.equals(btnDelete))
        {
            String maPN = txtMaPN.getText();
            String maNCC = txtMaNCC.getText();
            String maNV = txtMaNV.getText();
            String ngayNhap = txtNgayNhap.getText();
            String maLaptop = txtMaLaptop.getText();
            int soluong = Integer.parseInt(txtSoLuong.getText());
            int dongia = Integer.parseInt(txtDonGia.getText());
            int tongTien = Integer.parseInt(txtTongTien.getText());
            NhapHang nh = new NhapHang(maPN, maNCC, maNV, ngayNhap, maLaptop, soluong, dongia, tongTien);
            nhBUS.delete(maPN);
            outModel(model, nhBUS.getList());
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            setEditable(false);
        }
    }
    public int sumHD() {
        int sum = 0;
        if ((txtDonGia.getText().equals("") || txtSoLuong.getText().equals(""))){
            return 0;
        }
        int soluong = Integer.parseInt(txtSoLuong.getText());
        int dongia = Integer.parseInt(txtDonGia.getText());
        return soluong*dongia;
    }
    public void updateDate() {
        ActionListener task = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                Date date = Timestamp.valueOf(formatter.format((LocalDateTime.now())));
                txtNgayNhap.setText(date.toString().replace(".0",""));
            }
        };
        Timer timer = new Timer(1000 ,task);
        timer.setRepeats(true);
        timer.start();
    }
    public void stopUpdateDate() {
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
