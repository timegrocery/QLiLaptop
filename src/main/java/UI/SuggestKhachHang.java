/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BUS.InfoBalloon;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
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
public class SuggestKhachHang extends JDialog {
    private KhachHangBUS khBUS = new KhachHangBUS();
    private JTextField txtMaKH,txtSdt;
    private DefaultTableModel model;
    private JTable tbl;
    private int DWIDTH = 1200;
    private JTextField txtHo;
    private JTextField txtSearch;
    private JComboBox cmbChoice;

    
    public SuggestKhachHang(){
        setModal(true);
        init();
    }
    public void init(){
        setTitle("Danh sách khách hàng");
        setSize(DWIDTH,700);
        getContentPane().setBackground(new Color(55, 63, 81));
        setLayout(null);
        setLocationRelativeTo(null);
        
        Font ftitle = new Font("Segoe UI",Font.BOLD,25);
        Font font0 = new Font("Segoe UI",Font.PLAIN,14);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
        
        //HEADER
/***************** PHẦN HIỂN THỊ THÔNG TIN ***************************/
        JPanel itemView = new JPanel(null);
        itemView.setBounds(new Rectangle(0, 0,this.DWIDTH, 700));
        itemView.setBackground(Color.WHITE);
        
        JLabel lbMaKH = new JLabel("Mã khách hàng ");
        lbMaKH.setFont(font0);
        lbMaKH.setBounds(20,20,100,30);
        txtMaKH = new JTextField();
        txtMaKH.setBounds(new Rectangle(120,20,250,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtMaKH,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        itemView.add(lbMaKH);
        itemView.add(txtMaKH);
        
        JLabel lbHo = new JLabel("Họ và Tên");
        lbHo.setFont(font0);
        lbHo.setBounds(20,70,100,30);
        txtHo = new JTextField();
        txtHo.setBounds(new Rectangle(120,70,250,30));
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtHo,InfoBalloon.filter_alphaOnly, InfoBalloon.limit_name);
        itemView.add(lbHo);
        itemView.add(txtHo);
        
        
        JLabel lbSdt = new JLabel("SĐT ");
        lbSdt.setFont(font0);
        lbSdt.setBounds(20,170,100,30);
        txtSdt = new JTextField();
        txtSdt.setBounds(new Rectangle(120,170,250,30));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtSdt,InfoBalloon.filter_numberOnly, InfoBalloon.limit_sdt);
        itemView.add(lbSdt);
        itemView.add(txtSdt);
        
/**************** TẠO CÁC BTN XÓA, SỬA, VIEW, IN BILL ********************/

        JLabel btnConfirm = new JLabel(new ImageIcon("./src/main/java/image/btnConfirm_150px.png"));
        btnConfirm.setBounds(new Rectangle(20,260,150,50));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirm.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                dispose();
            }
        });
              
        JLabel btnBack = new JLabel(new ImageIcon("./src/main/java/image/btnBack_150px.png"));
        btnBack.setBounds(new Rectangle(180,260,150,50));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));    
        btnBack.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.exit(0);
            }
        });
        
        itemView.add(btnConfirm);
        itemView.add(btnBack);
/*************************************************************************/

/**************** TẠO TABLE ************************************************************/

    /************** TẠO MODEL VÀ HEADER *********************************/
        Vector header = new Vector();
        header.add("Mă KH");
        header.add("Họ và tên");
        header.add("SĐT");
        model = new DefaultTableModel(header,5);
        tbl = new JTable(model);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        tbl.setRowSorter(rowSorter);
        listSP();
        
    /*******************************************************************/
        

    /******** CUSTOM TABLE ****************/
    
        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(50);

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
        scroll.setBounds(new Rectangle(400, 20, DWIDTH - 450 , 500));
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
                    } catch (Exception ex) {
                        
                    }
                }
                txtMaKH.setText(tbl.getModel().getValueAt(i, 0).toString());
                txtHo.setText(tbl.getModel().getValueAt(i, 1).toString()); 
                txtSdt.setText(tbl.getModel().getValueAt(i, 2).toString());
             }
        });
/*********************************************************************/
/********************* THANH SEARCH ***********************************************/
        
//         Tạo Search Box
        JPanel searchBox = new JPanel(null);
        searchBox.setBackground(null);
        searchBox.setBounds(new Rectangle(20,220,350, 30)); 
        searchBox.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
        
        //PHẦN CHỌN SEARCH
        cmbChoice = new JComboBox();
        cmbChoice.setEditable(true);
        cmbChoice.setFont(new Font("Segoe UI",Font.PLAIN,14));
        cmbChoice.addItem("Mã KH");
        cmbChoice.addItem("Tên KH");
        cmbChoice.addItem("SĐT");
        cmbChoice.setBounds(new Rectangle(0,0,80,30));
        
        //Phần TextField 
        txtSearch = new JTextField();
        txtSearch.setBounds(new Rectangle(85,0,220,30));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI",Font.PLAIN,15));
        
        // Custem Icon search
        JLabel searchIcon = new JLabel(new ImageIcon("./src/image/search_25px.png"));
        searchIcon.setBounds(new Rectangle(305,-9,50,50));
        searchIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add tất cả vào search box
        searchBox.add(cmbChoice);
        searchBox.add(txtSearch);
        searchBox.add(searchIcon);

        //bắt sự kiện Focus vào search box
        txtSearch.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e) 
            {
                searchIcon.setIcon(new ImageIcon("./src/image/search_25px_focus.png")); //Đổi màu icon
                searchBox.setBorder(createLineBorder(new Color(232,57,99))); // Đổi màu viền 
            }
            public void focusLost(FocusEvent e) //Trờ về như cũ
            {
                searchIcon.setIcon(new ImageIcon("./src/image/search_25px.png"));
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
/*********************************************************************************/
        setVisible(true);
    }
    public void outModel(DefaultTableModel model , ArrayList<KhachHang> kh) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(KhachHang s:kh)
        {
            data = new Vector();
            data.add(s.getMaKH());
            data.add(s.getHoKH().concat(" "+s.getTenKH()));
            data.add(s.getSdt());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void listSP() // Chép ArrayList lên table
    {
        if(khBUS.getList()== null)khBUS.list();
        ArrayList<KhachHang> nv = khBUS.getList();
        model.setRowCount(0);
        outModel(model,nv);
    }
    public String getTextFieldContent() 
    {
        return txtMaKH.getText();
    }
}
