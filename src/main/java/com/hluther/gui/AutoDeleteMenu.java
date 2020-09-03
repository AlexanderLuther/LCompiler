package com.hluther.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author helmuth
 */
public class AutoDeleteMenu extends JMenuItem implements ActionListener {
    
    private String languageName;
    private LCompilerFrame lCompilerFrame;
    private boolean state;    
    
    public AutoDeleteMenu(String text, LCompilerFrame lCompilerFrame) {
        super(text);
        this.languageName = text;
        this.lCompilerFrame = lCompilerFrame;
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(48,50,55));
        this.setForeground(Color.WHITE);
        this.setIcon(new ImageIcon(this.getClass().getResource("/language.png")));
        this.setFont(new Font("Bitstream Vera Serif", 0, 13));
        this.addActionListener(this); 
        state = this.isSelected();
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(JOptionPane.showConfirmDialog(lCompilerFrame, "<html><font color = teal><center>Desea eliminar el lenguaje " +languageName+ "?</center></font></html>", "Eliminar Lenguaje", 2, 2) == JOptionPane.OK_OPTION){
            lCompilerFrame.deleteLenguage(languageName);
        }
    }
}
