package com.hluther.entityClasses;

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
    
    public Tab(String name, String extension, String data) {
        this.name = name;
        this.extension = extension;
        this.data = data;
        this.setText(this.data);
    }

    public Tab(String name, String extension, String data, String path) {
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
    
    
}
