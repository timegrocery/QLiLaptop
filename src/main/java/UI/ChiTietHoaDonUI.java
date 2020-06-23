/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BUS.ChiTietHoaDonBUS;
import DTO.ChiTietHoaDon;
import DTO.HoaDon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import BUS.InfoBalloon;

/**
 *
 * @author WindZ
 */
public class ChiTietHoaDonUI extends JFrame {
    private ChiTietHoaDonBUS ctBUS = new ChiTietHoaDonBUS();
    private String mahd;
    private JTextField txtMaSP,txtSL,txtDonGia;
    private DefaultTableModel model;
    private JTable tbl;
    private int DWIDTH = 840;
    private JTextField txtTenSP;
    public ChiTietHoaDonUI(){
        init();
    }
    public ChiTietHoaDonUI(String mahd){
        this.mahd = mahd;
        init();
    }
    public void init(){
        setTitle("Chi tiết hóa đơn");
        setSize(DWIDTH,450);
        getContentPane().setBackground(new Color(55, 63, 81));
        setLayout(null);
        setLocation(250, 150);
                
        Font ftitle = new Font("Segoe UI",Font.BOLD,25);
        Font font0 = new Font("Segoe UI",Font.PLAIN,13);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
        
        //HEADER
        JLabel title = new JLabel("CHI TIẾT HÓA ĐƠN : "+mahd,JLabel.CENTER);
        title.setFont(ftitle);
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, DWIDTH, 60);
        add(title);
/***************** PHẦN HIỂN THỊ THÔNG TIN ***************************/
        JPanel itemView = new JPanel(null);
        itemView.setBounds(new Rectangle(0, 60,this.getSize().width, this.getSize().height - 150));
        itemView.setBackground(Color.WHITE);
        
        JLabel lbMaSP = new JLabel("Mã sản phẩm ");
        lbMaSP.setFont(font0);
        lbMaSP.setBounds(20,20,100,30);
        txtMaSP = new JTextField();
        txtMaSP.setBounds(new Rectangle(120,20,210,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtMaSP, InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        itemView.add(lbMaSP);
        itemView.add(txtMaSP);
        
        JLabel lbTenSP = new JLabel("Tên sản phẩm ");
        lbTenSP.setFont(font0);
        lbTenSP.setBounds(20,60,100,30);
        txtTenSP = new JTextField();
        txtTenSP.setBounds(new Rectangle(120,60,210,30));
        itemView.add(lbTenSP);
        itemView.add(txtTenSP);
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtTenSP, InfoBalloon.filter_all, InfoBalloon.limit_name);
        
        JLabel lbSL = new JLabel("Số lượng ");
        lbSL.setFont(font0);
        lbSL.setBounds(20,100,100,30);
        txtSL = new JTextField();
        txtSL.setBounds(new Rectangle(120,100,210,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtSL, InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        itemView.add(lbSL);
        itemView.add(txtSL);
        
        JLabel lbDonGia = new JLabel("Đơn giá ");
        lbDonGia.setFont(font0);
        lbDonGia.setBounds(20,140,100,30);
        txtDonGia = new JTextField();
        txtDonGia.setBounds(new Rectangle(120,140,210,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtDonGia, InfoBalloon.filter_numberOnly, InfoBalloon.limit_price);
        itemView.add(lbDonGia);
        itemView.add(txtDonGia);
/**************** TẠO CÁC BTN XÓA, SỬA, VIEW, IN BILL ********************/

        JLabel btnConfirm = new JLabel(new ImageIcon("./src/main/java/image/btnConfirm_150px.png"));
        btnConfirm.setBounds(new Rectangle(20,180,150,50));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
              
         
        setEditable(false);
        itemView.add(btnConfirm);
        
        btnConfirm.addMouseListener(new MouseAdapter(){
           @Override
           public void mousePressed(MouseEvent e) {
               dispose();
           }
        });
/*************************************************************************/

/**************** TẠO TABLE ************************************************************/

    /************** TẠO MODEL VÀ HEADER *********************************/
        Vector header = new Vector();
        header.add("Mă SP");
        header.add("Tên SP");
        header.add("Số lượng");
        header.add("Đơn giá");
        model = new DefaultTableModel(header,5);
        tbl = new JTable(model);
        list(); //Đọc từ database lên table 
        
    /*******************************************************************/
        

    /******** CUSTOM TABLE ****************/
    
        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(120);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(30);

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
        scroll.setBounds(new Rectangle(350, 20, this.getSize().width - 450 , this.getSize().height - 180));
        scroll.setBackground(null);
        
        itemView.add(scroll);
        
        add(itemView);
        /**************************************/
        tbl.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    int i = tbl.getSelectedRow();
                    txtMaSP.setText(tbl.getModel().getValueAt(i, 0).toString());
                    txtTenSP.setText(tbl.getModel().getValueAt(i, 1).toString());
                    txtSL.setText(tbl.getModel().getValueAt(i, 2).toString()); 
                    txtDonGia.setText(tbl.getModel().getValueAt(i, 3).toString());
                }
        });
/*****************************************************************************************/
/*********************************************************************/
        
        setVisible(true);
    }
    public void outModel(DefaultTableModel model , ArrayList<ChiTietHoaDon> ct){ // Xuất ra Table từ ArrayList
        Vector data;
        model.setRowCount(0);
        for(ChiTietHoaDon c:ct){
            data = new Vector();
            data.add(c.getMaLaptop());
            data.add(c.getMaHD());
            data.add(c.getSl());
            data.add(c.getGia());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void clean(){
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtSL.setText("");
        txtDonGia.setText("");
    }
    public void setEditable(boolean flag) {
        txtMaSP.setEditable(false);
        txtTenSP.setEditable(false);
        txtSL.setEditable(flag);
        txtDonGia.setEditable(flag);
    }
    public void list(){ // Chép ArrayList lên table
        if(ctBUS.getList()== null)ctBUS.list();
        ArrayList<ChiTietHoaDon> ct = ctBUS.getListHD(mahd);
        model.setRowCount(0);
        outModel(model,ct);
    }
}
