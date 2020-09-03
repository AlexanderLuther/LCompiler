package com.hluther.gui;

import com.hluther.entityClasses.Language;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
/**
 *
 * @author helmuth
 */
public class LanguageMenuItem extends JCheckBoxMenuItem implements ActionListener{
    
    private Language language;
    private LCompilerFrame lCompilerFrame;
    private String name;
    private JMenu menu;
    private boolean state;
    
    public LanguageMenuItem(String text, LCompilerFrame lCompilerFrame, JMenu menu) {
        super(text);
        this.name = text;
        this.lCompilerFrame = lCompilerFrame;
        this.menu = menu;
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(48,50,55));
        this.setForeground(Color.WHITE);
        this.setIcon(new ImageIcon(this.getClass().getResource("/language.png")));
        this.setFont(new Font("Bitstream Vera Serif", 0, 13));
        this.addActionListener(this); 
        state = this.isSelected();
    }

    public Language getLenguage() {
        return language;
    }

    public void setLenguage(Language lenguage) {
        this.language = lenguage;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(state == false){
            for(var component : menu.getMenuComponents()){
                LanguageMenuItem currentMenu = (LanguageMenuItem)component;
                currentMenu.setSelected(false);
                currentMenu.setState(false);
            }
            state = true;
            lCompilerFrame.setCurrentLanguage(name);
            this.setSelected(true);
        }
        else{
            state = false;
            lCompilerFrame.reestoreCompileValues();
        }
    }
}
