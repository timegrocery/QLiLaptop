/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

/**
 *
 * @author WindZ
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class InfoBalloon extends JPanel {
    private static final int PREF_WIDTH = 400;
    private static final int PREF_HEIGHT = 300;
    // message
    public static final String errTxt_numberOnly = "Xin hãy nhập một số!";
    public static final String errTxt_invalidName = "Xin hãy nhập một tên hợp lệ!";
    // length limit for numbers 
    public static final int limit_ID = 4;
    public static final int limit_name = 100;
    public static final int limit_price = 9;
    public static final int limit_sdt = 11;
    // regex for different cases
    public static final String filter_numberOnly = "\\d*";
    private static final String ctrlZ = Character.toString((char)26); // ASCII 26 (Ctrl+Z) character
    private static final String modulus = Character.toString((char)37); // ASCII 37 (%) character
    public static String sqlInjectionCharacter = "\0\'\\\"\b\n\r\t\f";
    public static String filter_all; 
    public static final String filter_alphaOnly = "^((?![0-9]).)*$";


    private final String ERROR_TEXT;
    private final JTextField TXT_FIELD;
    private final String FILTER;
    private JWindow errorWindow;
    private JWindow tooLongErrorWindow;
    private String tooLongMessage;
   

    public InfoBalloon(String errorTxt, JTextField txtField, String filterType, int limit) {
        this.ERROR_TEXT = errorTxt;
        this.TXT_FIELD = txtField;
        this.FILTER = filterType;
        this.tooLongMessage = String.format("Dữ liệu nhập quá dài. Chỉ được nhập %d kí tự!", limit);
        ((PlainDocument)txtField.getDocument()).setDocumentFilter(new DocFilter(limit));
        // remove all characters that are actually capable of causing SQL injection in MYSQL
        /*
        //https://codegym.cc/groups/posts/escaping-characters-java
        for(int i=0;i<47;i++) {
            String temp = Character.toString((char)i);
            sqlInjectionCharacter += temp;
        }
        for(int i=58;i<64;i++) {
            String temp = Character.toString((char)i);
            sqlInjectionCharacter += temp;
        }
        for(int i=91;i<96;i++) {
            String temp = Character.toString((char)i);
            sqlInjectionCharacter += temp;
        }
        for(int i=123;i<126;i++) {
            String temp = Character.toString((char)i);
            sqlInjectionCharacter += temp;
        }
        */
        
        filter_all = String.format("^((?![%s]).)*$", sqlInjectionCharacter);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_WIDTH, PREF_HEIGHT);
    }

    private void showErrorWin() {
        Font font0 = new Font("Segoe UI",Font.PLAIN,15);
        if (errorWindow == null) {
            JLabel errorLabel = new JLabel(ERROR_TEXT);
            errorLabel.setFont(font0);
            Window topLevelWin = SwingUtilities.getWindowAncestor(this);
            errorWindow = new JWindow(topLevelWin);
            JPanel contentPane = (JPanel) errorWindow.getContentPane();
            contentPane.add(errorLabel);
            contentPane.setBackground(Color.white);
            contentPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            errorWindow.pack();
        }

        Point loc = TXT_FIELD.getLocationOnScreen();
        errorWindow.setLocation(loc.x + 7, loc.y - 27);
        errorWindow.setVisible(true);
        ActionListener task = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                errorWindow.setVisible(false);
            }
        };
        Timer timer = new Timer(2500 ,task); // balloon tooltip will be disable after 2.5 seconds xD
        timer.setRepeats(false);
        timer.start(); 
    }
    private void showTooLongErrorWin() {
        Font font0 = new Font("Segoe UI",Font.PLAIN,15);
        if (tooLongErrorWindow == null) {
            JLabel errorLabel = new JLabel(this.tooLongMessage);
            errorLabel.setFont(font0);
            Window topLevelWin = SwingUtilities.getWindowAncestor(this);
            tooLongErrorWindow = new JWindow(topLevelWin);
            JPanel contentPane = (JPanel) tooLongErrorWindow.getContentPane();
            contentPane.add(errorLabel);
            contentPane.setBackground(Color.white);
            contentPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            tooLongErrorWindow.pack();
        }

        Point loc = TXT_FIELD.getLocationOnScreen();
        tooLongErrorWindow.setLocation(loc.x + 7, loc.y - 57);
        tooLongErrorWindow.setVisible(true);
        ActionListener task = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                tooLongErrorWindow.setVisible(false);
            }
        };
        Timer timer = new Timer(2500 ,task); // balloon tooltip will be disable after 2.5 seconds xD
        timer.setRepeats(false);
        timer.start();
    }

   private boolean textOK(String text) {
        if (Handler.isNullOrEmpty(text)) {
            return true;
        }
        if (text.equals(""))
            return true;
        if (text.matches(this.FILTER)) {
            return true;
        }
        return false;
   }

   private class DocFilter extends DocumentFilter {
        private int limit;
        public DocFilter(int limit) {
            if (limit <= 0) {
                throw new IllegalArgumentException("Limit can not be <= 0");
            }
            this.limit = limit;
        }
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (textOK(string)) {
                super.insertString(fb, offset, string, attr);
                if (errorWindow != null && errorWindow.isVisible()) {
                    errorWindow.setVisible(false);
                }
            } else {
                showErrorWin();
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            
            if (textOK(text)) {
                if (Handler.isNullOrEmpty(text)) {
                    text = "";
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    int currentLength = fb.getDocument().getLength();
                    int overLimit = (currentLength + text.length()) - limit - length;
                    if (overLimit > 0) {
                        text = text.substring(0, text.length() - overLimit);
                        showTooLongErrorWin();
                    } else if (tooLongErrorWindow != null && tooLongErrorWindow.isVisible()) {
                        tooLongErrorWindow.setVisible(false);
                    }
                    if (text.length() > 0) {
                        //super.replace(fb, offset, length, text, attrs);
                    }
                    super.replace(fb, offset, length, text, attrs);
                    if (errorWindow != null && errorWindow.isVisible()) {
                        errorWindow.setVisible(false);
                    }
                }
            } else {
                showErrorWin();
            }
            
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
            if (errorWindow != null && errorWindow.isVisible()) {
                errorWindow.setVisible(false);
            }
        }
    }

}