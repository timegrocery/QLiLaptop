/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BUS.NhapHangBUS;
import DTO.NhapHang;
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

/**
 *
 * @author WindZ
 */
public class NhapHangUI extends JPanel implements ActionListener,FocusListener,MouseListener {
    private NhapHangBUS nhBUS = new NhapHangBUS();
    
    private String id ;
    private int DWIDTH;
    private JTextField txtMaNCC;
    private JTextField txtMaSP;
    private JTextField txtNgayNhap;
    
    private DefaultTableModel model;
    private JTable tbl;
    private JTextField txtGiaNhap;
    private JTextField txtSoLuong;
    private JTextField txtTongTien;
    private JLabel btnAdd;
    private JLabel btnDelete;
    private JLabel btnConfirm;
    private JLabel btnBack;
    private JButton btnSuggestSP;
    private JButton btnSuggestNCC;
    
    private boolean EditOrAdd = false;//Cờ cho button Cofirm  True:ADD || False:Edit
    public NhapHangUI(int width)
    {
        this.DWIDTH = width;
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
        
        JLabel lbMaNCC = new JLabel("Mã nhà CC ");
        lbMaNCC.setFont(font0);
        lbMaNCC.setBounds(20,20,100,30);
        txtMaNCC = new JTextField();
        txtMaNCC.setFont(font0);
        txtMaNCC.setBounds(new Rectangle(120,20,220,30));
        btnSuggestNCC = new JButton("...");
        btnSuggestNCC.setFont(font0);
        btnSuggestNCC.addActionListener(this);
        btnSuggestNCC.setBounds(new Rectangle(340,20,30,30));
        itemView.add(lbMaNCC);
        itemView.add(txtMaNCC);
        itemView.add(btnSuggestNCC);
        
        JLabel lbMaSP = new JLabel("Mã sản phẩm");
        lbMaSP.setFont(font0);
        lbMaSP.setBounds(20,70,100,30);
        txtMaSP = new JTextField();
        txtMaSP.setFont(font0);
        txtMaSP.setBounds(new Rectangle(120,70,220,30));
        btnSuggestSP = new JButton("...");
        btnSuggestSP.setFont(font0);
        btnSuggestSP.addActionListener(this);
        btnSuggestSP.setBounds(new Rectangle(340,70,30,30));
        itemView.add(lbMaSP);
        itemView.add(txtMaSP);
        itemView.add(btnSuggestSP);
        
        JLabel lbNgayNhap = new JLabel("Ngày Nhập");
        lbNgayNhap.setFont(font0);
        lbNgayNhap.setBounds(20,120,100,30);
        txtNgayNhap = new JTextField();
        txtNgayNhap.setFont(font0);
        txtNgayNhap.addFocusListener(this);
        txtNgayNhap.setBounds(new Rectangle(120,120,250,30));
        itemView.add(lbNgayNhap);
        itemView.add(txtNgayNhap);
        
        JLabel lbGiaNhap = new JLabel("Giá nhập");
        lbGiaNhap.setFont(font0);
        lbGiaNhap.setBounds(20,170,100,30);
        txtGiaNhap = new JTextField();
        txtGiaNhap.setFont(font0);
        txtGiaNhap.setBounds(new Rectangle(120,170,250,30));
        itemView.add(lbGiaNhap);
        itemView.add(txtGiaNhap);
        
        JLabel lbSoLuong = new JLabel("Số lượng");
        lbSoLuong.setFont(font0);
        lbSoLuong.setBounds(20,220,100,30);
        txtSoLuong = new JTextField();
        txtSoLuong.setFont(font0);
        txtSoLuong.setBounds(new Rectangle(120,220,250,30));
        itemView.add(lbSoLuong);
        itemView.add(txtSoLuong);
        
        JLabel lbTongTien = new JLabel("Tổng tiền");
        lbTongTien.setFont(font0);
        lbTongTien.setBounds(20,270,100,30);
        txtTongTien = new JTextField();
        txtTongTien.setFont(font0);
        txtTongTien.addFocusListener(this);
        txtTongTien.setBounds(new Rectangle(120,270,250,30));
        itemView.add(lbTongTien);
        itemView.add(txtTongTien);
        
/**************** TẠO CÁC BTN XÓA, SỬA, VIEW, IN BILL ********************/

        btnAdd = new JLabel(new ImageIcon("./src/main/java/image/btnAdd_150px.png"));
        btnAdd.setBounds(new Rectangle(20,330,150,50));
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdd.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                isAddSet(true);
                EditOrAdd = true;
            }
        });
              
        btnDelete = new JLabel(new ImageIcon("./src/main/java/image/btnDelete_150px.png"));
        btnDelete.setBounds(new Rectangle(180,330,150,50));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));    
        btnDelete.addMouseListener(this);
        
        btnConfirm = new JLabel(new ImageIcon("./src/main/java/image/btnConfirm_150px.png"));
        btnConfirm.setBounds(new Rectangle(20,330,150,50));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirm.setVisible(false);
        btnConfirm.addMouseListener(this);
        btnConfirm.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                
            }
        });
              
        btnBack = new JLabel(new ImageIcon("./src/main/java/image/btnBack_150px.png"));
        btnBack.setBounds(new Rectangle(180,330,150,50));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));  
        btnBack.setVisible(false);
        btnBack.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                isAddSet(false);
                EditOrAdd = false;
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
        header.add("ID");
        header.add("Mă NCC");
        header.add("Mã SP");
        header.add("Ngày nhập");
        header.add("Giá nhập");
        header.add("Số lượng");
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
        tbl.getColumnModel().getColumn(3).setPreferredWidth(100);

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
                    i = tbl.getRowSorter().convertRowIndexToModel(i);
                }
                id = tbl.getModel().getValueAt(i, 0).toString();
                txtMaNCC.setText(tbl.getModel().getValueAt(i, 1).toString());
                txtMaSP.setText(tbl.getModel().getValueAt(i, 2).toString());
                txtNgayNhap.setText(tbl.getModel().getValueAt(i, 3).toString()); 
                txtGiaNhap.setText(tbl.getModel().getValueAt(i, 4).toString()); 
                txtSoLuong.setText(tbl.getModel().getValueAt(i, 5).toString());
                txtTongTien.setText(tbl.getModel().getValueAt(i, 6).toString());            
             }
        });
/*********************************************************************/
    }
    public void outModel(DefaultTableModel model , ArrayList<NhapHang> nhaphang) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(NhapHang nh : nhaphang)
        {
            data = new Vector();
            data.add(nh.getMaPN());
            data.add(nh.getMaNCC());
            data.add(nh.getMaNV());
            data.add(nh.getNgayNhap());
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
    public void isAddSet(boolean flag)
    {
        btnAdd.setVisible(!flag);
        btnDelete.setVisible(!flag);
        
        btnConfirm.setVisible(flag);
        btnBack.setVisible(flag);
        
        clean();
    }

    public void clean()
    {
        txtMaNCC.setText("");
        txtMaSP.setText("");
        txtNgayNhap.setText("");
        txtGiaNhap.setText("");
        txtSoLuong.setText("");
        txtTongTien.setText("");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj.equals(btnSuggestSP))
        {
            SuggestSanPham sp = new SuggestSanPham(txtMaSP.getText());
            String s = sp.getTextFieldContent();
            txtMaSP.setText(s.split("%")[0]);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        Object obj = e.getSource();
        if(obj.equals(txtNgayNhap))
        {
            if(EditOrAdd)
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
                    int gia = Integer.parseInt(txtGiaNhap.getText());
                    int sum = sl*gia;
                    txtTongTien.setText(String.valueOf(sum));
                }catch(NumberFormatException ex)
                {
                    txtGiaNhap.requestFocus();
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
                String maNCC = txtMaNCC.getText();
                String maSP = txtMaSP.getText();
                String ngayNhap = txtNgayNhap.getText();
                int tongTien = Integer.parseInt(txtTongTien.getText());
                NhapHang nh = new NhapHang("", maNCC, maSP, ngayNhap, tongTien);
                nhBUS.add(nh);
                outModel(model, nhBUS.getList());
                JOptionPane.showMessageDialog(null, "Nhập hàng thành công");
            }
            
            isAddSet(false);
            EditOrAdd = false;
        }
        if(obj.equals(btnDelete))
        {
            String maNCC = txtMaNCC.getText();
            String maSP = txtMaSP.getText();
            String ngayNhap = txtNgayNhap.getText();
            int giaNhap = Integer.parseInt(txtGiaNhap.getText());
            NhapHang nh = new NhapHang("", maNCC, maSP, ngayNhap, giaNhap);
            nhBUS.delete(id);
            outModel(model, nhBUS.getList());
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        }
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
