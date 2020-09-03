package com.hluther.gui;

import com.hluther.controlClasses.EventsDriver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
/**
 *
 * @author helmuth
 */
public class PanelsCreator {
    
    private LineNumber lineNumber;
    private JScrollPane scrollPane;
    private JPanel panel;
    private JLabel label;
    private JButton button;
    private int tabId = 0;
        
    /*
    * Metodo encargado de agregar un nuevo panel al JTabbedPane.
    * Dentro del panel se agrega un JTextArea que tendra una barra lateral que 
    * marca el numero de linea.
    * Agrega dos eventos sobre en nuevo panel para visualizar la linea y columna
    * donde se encuentra el cursos y para cerrar el panel actual.
    */
    public Tab addPanel(LCompilerFrame frame, Tab tab, int index){
        tab.setLineWrap(true);
        lineNumber= new LineNumber(tab);
        scrollPane = new JScrollPane(tab);
        scrollPane.setRowHeaderView(lineNumber);
        panel = new JPanel(new GridLayout());
        panel.add(scrollPane);
        frame.getTabbedPane().addTab(String.valueOf(tabId), panel);
        addCloseButton(index, tab, frame.getTabbedPane());
        EventsDriver.addTextAreaEvent(tab, frame.getPositionLabel());
        EventsDriver.addButtonEvent(String.valueOf(tabId), button, frame);
        tabId++;
        return tab;
    }
    
    /*
    * Metodo encargado de agregar un nuevo panel que contendra el nombre del
    * archivo abierto y un boton para el cerrado de la pestana.
    */
    private void addCloseButton(int index, Tab tab, JTabbedPane tabbedPane){
        panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        label = new JLabel();
        label.setForeground(new Color(69,73,74));
        tab.setTitleLabel(label);
        tab.setTitle(tab.getName());
        button = new JButton(new ImageIcon(getClass().getResource("/close.png")));
        button.setPreferredSize(new Dimension(10, 10));
        button.setBorderPainted(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        panel.add(label, gbc);
        gbc.gridx++;
        gbc.weightx = 0;
        panel.add(button, gbc);
        tabbedPane.setTabComponentAt(index, panel);
    }
    
}
