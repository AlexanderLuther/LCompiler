package com.hluther.gui;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
/**
 *
 * @author helmuth
 */
public class Tab extends JTextArea{

    private String name;
    private String extension;
    private String data;
    private String path;
    private JLabel titleLabel;
    private final String BLANKSPACE = "   ";
    
    public Tab(String name, String extension, String data) {
        this.setFont(new Font("Source Code Pro", 0, 14));
        this.name = name;
        this.extension = extension;
        this.data = data;
        this.path = "";
        this.setText(this.data);
    }

    public Tab(String name, String extension, String data, String path) {
        this.setFont(new Font("Source Code Pro", 0, 13));
        this.name = name;
        this.extension = extension;
        this.data = data;
        this.path = path;
        this.setText(this.data);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getData() {
        return this.getText();
    }

    public void setData(String data) {
        this.data = data;
        this.setText(data);
    }   

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel tittleLabel) {
        this.titleLabel = tittleLabel;
    }

    public void setTitle(String title){
        titleLabel.setText(title + BLANKSPACE);
    }
    
}
