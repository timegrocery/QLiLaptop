/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BUS.InfoBalloon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import BUS.LaptopBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.KhachHangBUS;
import BUS.HoaDonBUS;
import BUS.NhapHangBUS;

/**
 *
 * @author WindZ
 */
public class ThongKeAllUI extends JDialog {
    private LaptopBUS spBUS = new LaptopBUS();
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private KhachHangBUS khBUS = new KhachHangBUS();
    private NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    private HoaDonBUS hdBUS = new HoaDonBUS();
    private NhapHangBUS nhBUS = new NhapHangBUS();
    
    private int DWIDTH = 1200;
    private JLabel allLaptop, dell, asus, acer, nhanvien, nvnam, nvnu, khachhang, nhacungcap, sohangban, sohangnhap,tongthu,tongchi, loinhuan;
    private JTextField txtAllLaptop, txtDell, txtAsus, txtAcer, txtNhanvien, txtKhachhang, txtNhacungcap, txtSohangban;
    private JTextField txtSohangnhap,txtLoinhuan, txtNvnam, txtNvnu, txtTongthu, txtTongchi;
    
    public ThongKeAllUI() {
        spBUS.listSP();
        nvBUS.listNV();
        khBUS.list();
        nccBUS.list();
        hdBUS.list();
        nhBUS.list();
        setModal(true);
        init();
    }
    public void init(){
        setTitle("Tổng quát");
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
        
        JLabel allLaptop = new JLabel("Tổng số laptop: ");
        allLaptop.setFont(font0);
        allLaptop.setBounds(20,20,150,30);
        txtAllLaptop = new JTextField();
        txtAllLaptop.setBounds(new Rectangle(160,20,200,30));
        txtAllLaptop.setEditable(false);
        txtAllLaptop.setText(String.valueOf(spBUS.getSize()));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtAllLaptop,InfoBalloon.filter_numberOnly, InfoBalloon.limit_ID);
        itemView.add(allLaptop);
        itemView.add(txtAllLaptop);
        
        JLabel dell = new JLabel("Số laptop Dell: ");
        dell.setFont(font0);
        dell.setBounds(20,70,150,30);
        txtDell = new JTextField();
        txtDell.setBounds(new Rectangle(160,70,200,30));
        txtDell.setEditable(false);
        txtDell.setText(String.valueOf(spBUS.getSizeOf("2001")));
        new InfoBalloon(InfoBalloon.errTxt_invalidName, txtDell,InfoBalloon.filter_alphaOnly, InfoBalloon.limit_name);
        itemView.add(dell);
        itemView.add(txtDell);
        
        
        JLabel asus = new JLabel("Số laptop Asus: ");
        asus.setFont(font0);
        asus.setBounds(20,120,150,30);
        txtAsus = new JTextField();
        txtAsus.setBounds(new Rectangle(160,120,200,30));
        txtAsus.setEditable(false);
        txtAsus.setText(String.valueOf(spBUS.getSizeOf("2002")));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtAsus,InfoBalloon.filter_numberOnly, InfoBalloon.limit_sdt);
        itemView.add(asus);
        itemView.add(txtAsus);
        
        JLabel acer = new JLabel("Số laptop Acer: ");
        acer.setFont(font0);
        acer.setBounds(20,170,150,30);
        txtAcer = new JTextField();
        txtAcer.setBounds(new Rectangle(160,170,200,30));
        txtAcer.setEditable(false);
        txtAcer.setText(String.valueOf(spBUS.getSizeOf("2003")));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtAcer,InfoBalloon.filter_numberOnly, InfoBalloon.limit_sdt);
        itemView.add(acer);
        itemView.add(txtAcer);
        
        JLabel nhanvien = new JLabel("Số nhân viên: ");
        nhanvien.setFont(font0);
        nhanvien.setBounds(20,220,150,30);
        txtNhanvien = new JTextField();
        txtNhanvien.setBounds(new Rectangle(160,220,200,30));
        txtNhanvien.setEditable(false);
        txtNhanvien.setText(String.valueOf(nvBUS.getSize()));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtNhanvien,InfoBalloon.filter_numberOnly, InfoBalloon.limit_sdt);
        itemView.add(nhanvien);
        itemView.add(txtNhanvien);
        
        JLabel nvnam = new JLabel("Số nhân viên nam: ");
        nvnam.setFont(font0);
        nvnam.setBounds(440,220,150,30);
        txtNvnam = new JTextField();
        txtNvnam.setBounds(new Rectangle(560,220,200,30));
        txtNvnam.setEditable(false);
        txtNvnam.setText(String.valueOf(nvBUS.getSizeOf("Nam")));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtNvnam,InfoBalloon.filter_numberOnly, InfoBalloon.limit_sdt);
        itemView.add(nvnam);
        itemView.add(txtNvnam);
        
        JLabel nvnu = new JLabel("Số nhân viên nữ: ");
        nvnu.setFont(font0);
        nvnu.setBounds(830,220,150,30);
        txtNvnu = new JTextField();
        txtNvnu.setBounds(new Rectangle(950,220,200,30));
        txtNvnu.setEditable(false);
        txtNvnu.setText(String.valueOf(nvBUS.getSizeOf("Nữ")));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtNvnu,InfoBalloon.filter_numberOnly, InfoBalloon.limit_sdt);
        itemView.add(nvnu);
        itemView.add(txtNvnu);
        
        JLabel khachhang = new JLabel("Số khách hàng: ");
        khachhang.setFont(font0);
        khachhang.setBounds(20,270,150,30);
        txtKhachhang = new JTextField();
        txtKhachhang.setBounds(new Rectangle(160,270,200,30));
        txtKhachhang.setEditable(false);
        txtKhachhang.setText(String.valueOf(khBUS.getSize()));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtKhachhang,InfoBalloon.filter_numberOnly, InfoBalloon.limit_sdt);
        itemView.add(khachhang);
        itemView.add(txtKhachhang);
        
        JLabel nhacungcap = new JLabel("Số nhà cung cấp: ");
        nhacungcap.setFont(font0);
        nhacungcap.setBounds(20,320,150,30);
        txtNhacungcap = new JTextField();
        txtNhacungcap.setBounds(new Rectangle(160,320,200,30));
        txtNhacungcap.setEditable(false);
        txtNhacungcap.setText(String.valueOf(nccBUS.getSize()));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtNhacungcap,InfoBalloon.filter_numberOnly, InfoBalloon.limit_sdt);
        itemView.add(nhacungcap);
        itemView.add(txtNhacungcap);
        
        int tienban = hdBUS.getProfit();
        int tiennhap = nhBUS.getInvest();
        int profit = tienban - tiennhap;
        
        JLabel sohangban = new JLabel("Số hóa đơn bán: ");
        sohangban.setFont(font0);
        sohangban.setBounds(20,370,150,30);
        txtSohangban = new JTextField();
        txtSohangban.setBounds(new Rectangle(160,370,200,30));
        txtSohangban.setEditable(false);
        txtSohangban.setText(String.valueOf(hdBUS.getSize()));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtSohangban,InfoBalloon.filter_numberOnly, InfoBalloon.limit_sdt);
        itemView.add(sohangban);
        itemView.add(txtSohangban);
        
        JLabel tongthu = new JLabel("Tổng thu: ");
        tongthu.setFont(font0);
        tongthu.setBounds(440,370,150,30);
        txtTongthu = new JTextField();
        txtTongthu.setBounds(new Rectangle(560,370,200,30));
        txtTongthu.setEditable(false);
        txtTongthu.setText(String.valueOf(tienban));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtTongthu,InfoBalloon.filter_numberOnly, InfoBalloon.limit_sdt);
        itemView.add(tongthu);
        itemView.add(txtTongthu);
        
        JLabel sohangnhap = new JLabel("Số hóa đơn nhập: ");
        sohangnhap.setFont(font0);
        sohangnhap.setBounds(20,420,150,30);
        txtSohangnhap = new JTextField();
        txtSohangnhap.setBounds(new Rectangle(160,420,200,30));
        txtSohangnhap.setEditable(false);
        txtSohangnhap.setText(String.valueOf(nhBUS.getSize()));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtSohangnhap,InfoBalloon.filter_numberOnly, InfoBalloon.limit_sdt);
        itemView.add(sohangnhap);
        itemView.add(txtSohangnhap);
        
        JLabel tongchi = new JLabel("Tổng chi: ");
        tongchi.setFont(font0);
        tongchi.setBounds(440,420,150,30);
        txtTongchi = new JTextField();
        txtTongchi.setBounds(new Rectangle(560,420,200,30));
        txtTongchi.setEditable(false);
        txtTongchi.setText(String.valueOf(tiennhap));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtTongchi,InfoBalloon.filter_numberOnly, InfoBalloon.limit_sdt);
        itemView.add(tongchi);
        itemView.add(txtTongchi);
        
        JLabel loinhuan = new JLabel("Lợi nhuận: ");
        loinhuan.setFont(font0);
        loinhuan.setBounds(20,470,150,30);
        txtLoinhuan = new JTextField();
        txtLoinhuan.setBounds(new Rectangle(160,470,200,30));
        txtLoinhuan.setEditable(false);
        txtLoinhuan.setText(String.valueOf(profit));
        new InfoBalloon(InfoBalloon.errTxt_numberOnly, txtLoinhuan,InfoBalloon.filter_numberOnly, InfoBalloon.limit_sdt);
        itemView.add(loinhuan);
        itemView.add(txtLoinhuan);
        
        JLabel btnConfirm = new JLabel(new ImageIcon("./src/main/java/image/btnConfirm_150px.png"));
        btnConfirm.setBounds(new Rectangle(550,520,150,50));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirm.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                dispose();
            }
        });
        itemView.add(btnConfirm);
        add(itemView);
        setVisible(true);
    }
}
