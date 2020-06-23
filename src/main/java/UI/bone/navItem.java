/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.bone;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author WindZ
 */
public class navItem extends JPanel implements MouseListener {
    private JLabel lb,icon;
    private Color hover = new Color(250, 160, 185);
    private Color normal = new Color(109, 172, 181);
    private boolean active ;
    private String name,img,imgActive,imgHover;
    private Rectangle rec = new Rectangle();
    public navItem(String s,Rectangle r,String img,String imgActive ){
        name = s;
        lb = new JLabel(name);
        this.img = img;
        this.imgActive = imgActive;
        this.imgHover = this.img;
        this.icon = new JLabel();
        rec = r;
        init();
    }
    public navItem(String s,Rectangle r,String img,String imgActive,String imgHover,Color hover){
        name = s;
        lb = new JLabel(name);
        this.img = img;
        this.imgActive = imgActive;
        this.imgHover = imgHover;
        this.icon = new JLabel();
        rec = r;
        this.hover = hover;
        init();
    }
    public navItem(String s,Rectangle r){
        lb = new JLabel(s);
        icon = new JLabel();
        rec = r;
        init();
    }
    
    public void setColorNormal(Color e){
        this.normal = e;
        lb.setBackground(normal);
        lb.repaint();   
    }
    
    public JPanel isButton(){
        icon.setBounds(new Rectangle(rec.width/4 + 2 , rec.height/4, 50, 30));
        normal = null;
        lb.setBackground(normal);
//        repaint();
        return this;
    }
    public void init(){

        lb.addMouseListener(this);
        Font font = new Font("Segoe UI",Font.BOLD,13);
        setLayout(null);
        setBounds(rec);
        
        icon.setIcon(new ImageIcon("./src/main/java/image/"+img));
        icon.setBackground(new Color(255, 66, 119));
        icon.setBounds(new Rectangle(rec.width/7 , rec.height/4, 50, 30));
        
        lb.setFont(font);
        lb.setForeground(Color.WHITE);
        lb.setBounds(new Rectangle(rec.width/4+10, rec.height/4, 150, 30));
           
        if(active) {
            setBackground(new Color(255, 66, 119));
        }
        else
        {
            setBackground(normal);
        } 
        add(icon);
        add(lb);
        
    }
    public void setActive(boolean a){
        active = a;
        
    }

    public String getName() {
        return name;
    }
    
    public JLabel getlb(){
        return lb;
    }

    public void doActive(){
        active = true;
        icon.setIcon(new ImageIcon("./src/main/java/image/"+imgActive));
        lb.setForeground(Color.BLACK);
        setBackground(new Color(255, 66, 119));
    }
    public void noActive(){
        active = false;
        icon.setIcon(new ImageIcon("./src/main/java/image/"+img));
        lb.setForeground(Color.WHITE);
        setBackground(normal);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
//        active = true;
//        if(active)
//        {
//            setBackground(Color.WHITE);
//        }
//        else
//        {
//            setBackground(null);
//        } 
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!active){
            setBackground(hover);
            icon.setIcon(new ImageIcon("./src/main/java/image/"+imgHover));
        }
    }

    @Override
    public void mouseExited(MouseEvent e){
        if(!active){
            setBackground(normal);
            icon.setIcon(new ImageIcon("./src/main/java/image/"+img));
        }
    }
}
