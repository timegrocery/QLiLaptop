/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.bone;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import UI.bone.navItem;

/**
 *
 * @author WindZ
 */
public class header extends JPanel {
    private int height,width;
    private JFrame frame;
    JLabel name = new JLabel("", JLabel.CENTER);
    public header(int w,int h, String text){
        width = w;
        height = h;
        init(text);
    }
    public void init(String text){
        setLayout(null);
        setSize(width,height);
        setBackground(null);
        
        Font font = new Font("Segoe UI",Font.BOLD,16);
        name = new JLabel(text, JLabel.CENTER);
        name.setFont(font);
        name.setForeground(Color.white);
        name.setBounds(new Rectangle(60, 0, 160, 40));
        
        add(name);
    }
    public void clearText() {
        this.name.setText("");
    }
}
