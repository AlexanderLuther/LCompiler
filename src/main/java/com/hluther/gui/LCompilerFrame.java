package com.hluther.gui;

import com.hluther.controlClasses.FileChoosersDriver;
import com.hluther.controlClasses.AnalysisDriver;
import com.hluther.controlClasses.FilesDriver;
import com.hluther.controlClasses.ThreadsDriver;
import com.hluther.controlClasses.NodesDriver;
import com.hluther.entityClasses.Tab;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.text.Document;
/**
 *
 * @author helmuth
 */
public class LCompilerFrame extends javax.swing.JFrame {
  
    private PanelsCreator panelsCreator = new PanelsCreator();
    private FilesDriver filesDriver = new FilesDriver();
    private FileChoosersDriver fileChoosersCreator = new FileChoosersDriver(filesDriver);
    private AnalysisDriver analysisDriver = new AnalysisDriver();
    private ThreadsDriver threadsDriver = new ThreadsDriver();
    private NodesDriver treeDriver = new NodesDriver();
    private ArrayList<Tab> tabs = new ArrayList<>();
    private final String DEFAULTNAME = "Untitle";
    private Tab tab;
    private int counter = 1;
    private int selectedPane = -1;
   
    public LCompilerFrame() {
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        initComponents();
        enableComponents();
    }

    //Getter
    public JLabel getPositionLabel() {
        return positionLabel;
    }

    //Getter
    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
      
    /*
    * Remover un panel del JTabbedPane
    * Abre un JOptionPane para indicar si se desean guardar los cambios hechos
    * al archivo. Si el usuario presiona la opcion OK se realiza el proceso de
    * guardado llamando al metodo saveFile(). Si el usuario presiona NO solo se
    * remueve el panel actual y si el usuario presiona cancelar no se realiza 
    * ninguna accion.
    */
    public void removePanel(int index){
        int option = JOptionPane.showInternalConfirmDialog(null, "Desea guardar los cambios realizados?", "Guardar Archivo", 1);
        switch(option){
            case JOptionPane.OK_OPTION:
                saveFile();
                tabbedPane.remove(index);
                tabs.remove(index);
            break;
            case JOptionPane.NO_OPTION:
                tabbedPane.remove(index);
                tabs.remove(index);
            break;
            default:
            break;
        }
    }
    
    //Activar o desactivar componentes de la GUI.
    private void enableComponents(){
        if(selectedPane == -1){
            saveFileButton.setEnabled(false);
            saveFileMenu.setEnabled(false);
            saveAsButton.setEnabled(false);
            saveAsMenu.setEnabled(false);
            compileButton.setEnabled(false);
            compileMenu.setEnabled(false);
        }
        else{
            saveFileButton.setEnabled(true);
            saveFileMenu.setEnabled(true);
            saveAsButton.setEnabled(true);
            saveAsMenu.setEnabled(true);
            compileButton.setEnabled(true);
            compileMenu.setEnabled(true);
        }
    }
    
    /*
    * Metodo encargado del proceso de guardado.
    * Obtiene la pestana actual y valida si tiene algun path asociado o no. Si 
    * tiene un path toda la informacion en el area de texto se guarda en el
    * archivo correspondiente, de lo contario se llama al metodo saveAs() para 
    * la creacion y escritura de un nuevo archivo. Por ultimo se muestra un 
    * mensaje indicando la respuesta del sistema. 
    */
    private void saveFile(){
        tab = tabs.get(selectedPane);
        if(tab.getPath().isEmpty()){
           saveAs();
        }
        else{
            if(filesDriver.writeFile(tab.getPath(), tab.getData())) informationLabel.setText("Guardado en: " + tab.getPath());
            else informationLabel.setText("Error al guardar el archivo.");
        }
        threadsDriver.clearLabel(informationLabel);
        
        
        Document document = tab.getDocument();
        
    }
    
    /*
    * Metodo encargado del proceso de guardado como.
    * Obtiene la pestana actual y  manda a la creacion y escritura de un nuevo
    * archivo. Por ultimo se muestra un mensaje indicando la respuesta del sistema. 
    */
    private void saveAs(){
        tab = tabs.get(selectedPane);
        try {
            if(fileChoosersCreator.saveFile(this, tab)) informationLabel.setText("Guardado en: " + tab.getPath());
            else informationLabel.setText("Guardado cancelado.");
        } catch (IOException ex) {
            informationLabel.setText("Error al guardar: " + ex.getMessage());
        }
        threadsDriver.clearLabel(informationLabel);
    }
    
    //Agregar un nuevo JPanel al JTabbedPane
    private void newFile(){
        tabs.add(panelsCreator.addPanel(this, new Tab(DEFAULTNAME+counter, "", ""), tabs.size()));
        counter++;
    }
   
    //Agregar un JPanel con informacion de un archivo seleccionado.
    private void openFile(){
        tab = fileChoosersCreator.openFile(this, tabs);
        if(tab != null) tabs.add(panelsCreator.addPanel(this, tab, tabs.size()));
    }
    
    private void uploadLanguage(){
        
    }
    
    public void printMessage(String msg){
        messageArea.setText(messageArea.getText() + msg);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator13 = new javax.swing.JToolBar.Separator();
        newFileButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        openFileButton = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        saveFileButton = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        saveAsButton = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        compileButton = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JToolBar.Separator();
        uploadButton = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        deleteButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        positionLabel = new javax.swing.JLabel();
        informationLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newFileMenu = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        openFileMenu = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        saveFileMenu = new javax.swing.JMenuItem();
        saveAsMenu = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        exitMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        compileMenu = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        uploadMenu = new javax.swing.JMenuItem();
        deleteMenu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        tableMenu = new javax.swing.JMenuItem();
        stackMenu = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        aboutMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LCompiler");
        setMinimumSize(new java.awt.Dimension(600, 400));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(69, 73, 74));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setMaximumSize(new java.awt.Dimension(10, 32767));
        jPanel2.setMinimumSize(new java.awt.Dimension(10, 100));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(10, 180));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setMaximumSize(new java.awt.Dimension(10, 32767));
        jPanel3.setMinimumSize(new java.awt.Dimension(10, 100));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(10, 180));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.LINE_END);

        jPanel4.setMaximumSize(new java.awt.Dimension(32767, 10));
        jPanel4.setMinimumSize(new java.awt.Dimension(100, 10));
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(641, 40));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jToolBar1.setBackground(new java.awt.Color(60, 63, 68));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(10, 25));
        jToolBar1.setMinimumSize(new java.awt.Dimension(10, 25));
        jToolBar1.add(jSeparator13);

        newFileButton.setBackground(new java.awt.Color(60, 63, 68));
        newFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newFile.png"))); // NOI18N
        newFileButton.setToolTipText("Nuevo Archivo... (Ctrl+N)");
        newFileButton.setBorderPainted(false);
        newFileButton.setFocusable(false);
        newFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newFileButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/newFileBW.png"))); // NOI18N
        newFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(newFileButton);
        jToolBar1.add(jSeparator2);

        openFileButton.setBackground(new java.awt.Color(60, 63, 68));
        openFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/open.png"))); // NOI18N
        openFileButton.setToolTipText("Abrir Archivo... (Ctrl+O)");
        openFileButton.setBorderPainted(false);
        openFileButton.setFocusable(false);
        openFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openFileButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/openBW.png"))); // NOI18N
        openFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(openFileButton);
        jToolBar1.add(jSeparator7);

        saveFileButton.setBackground(new java.awt.Color(60, 63, 68));
        saveFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        saveFileButton.setToolTipText("Guardar Archivo... (Ctrl+S)");
        saveFileButton.setBorderPainted(false);
        saveFileButton.setFocusable(false);
        saveFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveFileButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/saveBW.png"))); // NOI18N
        saveFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(saveFileButton);
        jToolBar1.add(jSeparator6);

        saveAsButton.setBackground(new java.awt.Color(60, 63, 68));
        saveAsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saveAs.png"))); // NOI18N
        saveAsButton.setToolTipText("Guardar como... (Ctrl+Shift+S)");
        saveAsButton.setBorderPainted(false);
        saveAsButton.setFocusable(false);
        saveAsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveAsButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/saveAsBW.png"))); // NOI18N
        saveAsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveAsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(saveAsButton);
        jToolBar1.add(jSeparator8);

        compileButton.setBackground(new java.awt.Color(60, 63, 68));
        compileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compile.png"))); // NOI18N
        compileButton.setToolTipText("Compilar... (Ctrl+E)");
        compileButton.setBorderPainted(false);
        compileButton.setFocusable(false);
        compileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        compileButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/compileBW.png"))); // NOI18N
        compileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        compileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(compileButton);
        jToolBar1.add(jSeparator12);

        uploadButton.setBackground(new java.awt.Color(60, 63, 68));
        uploadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/load.png"))); // NOI18N
        uploadButton.setBorderPainted(false);
        uploadButton.setFocusable(false);
        uploadButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        uploadButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/loadBW.png"))); // NOI18N
        uploadButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(uploadButton);
        jToolBar1.add(jSeparator9);

        deleteButton.setBackground(new java.awt.Color(60, 63, 68));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png"))); // NOI18N
        deleteButton.setToolTipText("Borrar Lenguaje... (Ctrl+D)");
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusable(false);
        deleteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/deleteBW.png"))); // NOI18N
        deleteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(deleteButton);

        jPanel4.add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel5.setMaximumSize(new java.awt.Dimension(32767, 25));
        jPanel5.setMinimumSize(new java.awt.Dimension(100, 25));
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(641, 25));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel10.setMaximumSize(new java.awt.Dimension(10, 32767));
        jPanel10.setMinimumSize(new java.awt.Dimension(10, 100));
        jPanel10.setOpaque(false);
        jPanel10.setPreferredSize(new java.awt.Dimension(10, 25));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel10, java.awt.BorderLayout.LINE_START);

        jPanel11.setMaximumSize(new java.awt.Dimension(10, 32767));
        jPanel11.setMinimumSize(new java.awt.Dimension(10, 100));
        jPanel11.setOpaque(false);
        jPanel11.setPreferredSize(new java.awt.Dimension(10, 25));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel11, java.awt.BorderLayout.LINE_END);

        jPanel8.setLayout(new java.awt.BorderLayout());

        positionLabel.setBackground(new java.awt.Color(69, 73, 74));
        positionLabel.setFont(new java.awt.Font("Bitstream Vera Serif", 1, 13)); // NOI18N
        positionLabel.setForeground(new java.awt.Color(38, 169, 94));
        positionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        positionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        positionLabel.setMaximumSize(new java.awt.Dimension(200, 0));
        positionLabel.setMinimumSize(new java.awt.Dimension(200, 0));
        positionLabel.setOpaque(true);
        positionLabel.setPreferredSize(new java.awt.Dimension(200, 0));
        positionLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel8.add(positionLabel, java.awt.BorderLayout.LINE_END);

        informationLabel.setBackground(new java.awt.Color(69, 73, 74));
        informationLabel.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 13)); // NOI18N
        informationLabel.setForeground(new java.awt.Color(255, 255, 255));
        informationLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        informationLabel.setOpaque(true);
        informationLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel8.add(informationLabel, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanel6.setBackground(new java.awt.Color(48, 50, 55));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new java.awt.BorderLayout());

        messageArea.setEditable(false);
        messageArea.setBackground(new java.awt.Color(48, 50, 55));
        messageArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 169, 94)));
        messageArea.setForeground(new java.awt.Color(255, 255, 255));
        messageArea.setMaximumSize(new java.awt.Dimension(2147483647, 150));
        messageArea.setMinimumSize(new java.awt.Dimension(62, 100));
        messageArea.setPreferredSize(new java.awt.Dimension(62, 100));
        jScrollPane2.setViewportView(messageArea);

        jPanel7.add(jScrollPane2, java.awt.BorderLayout.PAGE_END);

        jLabel1.setBackground(new java.awt.Color(69, 73, 74));
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(0, 10));
        jPanel7.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel9.setBackground(new java.awt.Color(69, 73, 74));
        jPanel9.setMaximumSize(new java.awt.Dimension(32767, 20));
        jPanel9.setMinimumSize(new java.awt.Dimension(0, 20));
        jPanel9.setPreferredSize(new java.awt.Dimension(601, 20));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 621, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel9, java.awt.BorderLayout.PAGE_START);

        jPanel6.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        tabbedPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 153)));
        tabbedPane.setForeground(new java.awt.Color(153, 0, 153));
        tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneStateChanged(evt);
            }
        });
        jPanel6.add(tabbedPane, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel6, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1);

        jMenuBar1.setBackground(new java.awt.Color(48, 50, 55));

        fileMenu.setForeground(new java.awt.Color(255, 255, 255));
        fileMenu.setText("Archivo");
        fileMenu.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 13)); // NOI18N

        newFileMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        newFileMenu.setBackground(new java.awt.Color(48, 50, 55));
        newFileMenu.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 12)); // NOI18N
        newFileMenu.setForeground(new java.awt.Color(255, 255, 255));
        newFileMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newFile.png"))); // NOI18N
        newFileMenu.setText("Nuevo");
        newFileMenu.setOpaque(true);
        newFileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileMenuActionPerformed(evt);
            }
        });
        fileMenu.add(newFileMenu);
        fileMenu.add(jSeparator3);

        openFileMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        openFileMenu.setBackground(new java.awt.Color(48, 50, 55));
        openFileMenu.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 12)); // NOI18N
        openFileMenu.setForeground(new java.awt.Color(255, 255, 255));
        openFileMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/open.png"))); // NOI18N
        openFileMenu.setText("Abrir");
        openFileMenu.setOpaque(true);
        openFileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileMenuActionPerformed(evt);
            }
        });
        fileMenu.add(openFileMenu);
        fileMenu.add(jSeparator4);

        saveFileMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveFileMenu.setBackground(new java.awt.Color(48, 50, 55));
        saveFileMenu.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 12)); // NOI18N
        saveFileMenu.setForeground(new java.awt.Color(255, 255, 255));
        saveFileMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        saveFileMenu.setText("Guardar");
        saveFileMenu.setOpaque(true);
        saveFileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileMenuActionPerformed(evt);
            }
        });
        fileMenu.add(saveFileMenu);

        saveAsMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveAsMenu.setBackground(new java.awt.Color(48, 50, 55));
        saveAsMenu.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 12)); // NOI18N
        saveAsMenu.setForeground(new java.awt.Color(255, 255, 255));
        saveAsMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saveAs.png"))); // NOI18N
        saveAsMenu.setText("Guardar Como");
        saveAsMenu.setOpaque(true);
        saveAsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenu);
        fileMenu.add(jSeparator5);

        exitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        exitMenu.setBackground(new java.awt.Color(48, 50, 55));
        exitMenu.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 12)); // NOI18N
        exitMenu.setForeground(new java.awt.Color(255, 255, 255));
        exitMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exit.png"))); // NOI18N
        exitMenu.setText("Salir");
        exitMenu.setOpaque(true);
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenu);

        jMenuBar1.add(fileMenu);

        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("Lenguajes");
        jMenu2.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 13)); // NOI18N
        jMenuBar1.add(jMenu2);

        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Ejecutar");
        jMenu1.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 13)); // NOI18N

        compileMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        compileMenu.setBackground(new java.awt.Color(48, 50, 55));
        compileMenu.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 12)); // NOI18N
        compileMenu.setForeground(new java.awt.Color(255, 255, 255));
        compileMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compile.png"))); // NOI18N
        compileMenu.setText("Compilar");
        jMenu1.add(compileMenu);
        jMenu1.add(jSeparator1);

        uploadMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        uploadMenu.setBackground(new java.awt.Color(48, 50, 55));
        uploadMenu.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 13)); // NOI18N
        uploadMenu.setForeground(new java.awt.Color(255, 255, 255));
        uploadMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/load.png"))); // NOI18N
        uploadMenu.setText("Cargar Lenguaje");
        uploadMenu.setOpaque(true);
        uploadMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadMenuActionPerformed(evt);
            }
        });
        jMenu1.add(uploadMenu);

        deleteMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        deleteMenu.setBackground(new java.awt.Color(48, 50, 55));
        deleteMenu.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 13)); // NOI18N
        deleteMenu.setForeground(new java.awt.Color(255, 255, 255));
        deleteMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png"))); // NOI18N
        deleteMenu.setText("Borrar Lenguaje");
        deleteMenu.setOpaque(true);
        jMenu1.add(deleteMenu);

        jMenuBar1.add(jMenu1);

        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("Ver");
        jMenu3.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 13)); // NOI18N

        tableMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        tableMenu.setBackground(new java.awt.Color(48, 50, 55));
        tableMenu.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 13)); // NOI18N
        tableMenu.setForeground(new java.awt.Color(255, 255, 255));
        tableMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table.png"))); // NOI18N
        tableMenu.setText("Tabla LALR");
        jMenu3.add(tableMenu);

        stackMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        stackMenu.setBackground(new java.awt.Color(48, 50, 55));
        stackMenu.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 13)); // NOI18N
        stackMenu.setForeground(new java.awt.Color(255, 255, 255));
        stackMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stack.png"))); // NOI18N
        stackMenu.setText("Pila");
        jMenu3.add(stackMenu);

        jMenuBar1.add(jMenu3);

        jMenu4.setForeground(new java.awt.Color(255, 255, 255));
        jMenu4.setText("Ayuda");
        jMenu4.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 13)); // NOI18N

        aboutMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        aboutMenu.setBackground(new java.awt.Color(48, 50, 55));
        aboutMenu.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 13)); // NOI18N
        aboutMenu.setForeground(new java.awt.Color(255, 255, 255));
        aboutMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/about.png"))); // NOI18N
        aboutMenu.setText("Acerca de...");
        aboutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuActionPerformed(evt);
            }
        });
        jMenu4.add(aboutMenu);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuActionPerformed

    private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertButtonActionPerformed
    }//GEN-LAST:event_convertButtonActionPerformed

    private void openFileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileMenuActionPerformed
        openFile();
    }//GEN-LAST:event_openFileMenuActionPerformed
    
    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
        openFile();
    }//GEN-LAST:event_openFileButtonActionPerformed
 
    private void newFileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFileMenuActionPerformed
        newFile();
    }//GEN-LAST:event_newFileMenuActionPerformed

    private void newFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFileButtonActionPerformed
        newFile();
    }//GEN-LAST:event_newFileButtonActionPerformed

    private void tabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneStateChanged
        positionLabel.setText("");
        selectedPane = tabbedPane.getSelectedIndex();
        enableComponents();
    }//GEN-LAST:event_tabbedPaneStateChanged

    private void aboutMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuActionPerformed
        JOptionPane.showMessageDialog(this, "<html><font color = teal><center>Desarrollado por:<br>Helmuth Alexander Luther Montejo<br>201730457</center></font></html>", "Aceca De", 3);
    }//GEN-LAST:event_aboutMenuActionPerformed

    private void saveFileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileMenuActionPerformed
        saveFile();
    }//GEN-LAST:event_saveFileMenuActionPerformed

    private void saveFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileButtonActionPerformed
        saveFile();
    }//GEN-LAST:event_saveFileButtonActionPerformed

    private void saveAsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuActionPerformed
        saveAs();
    }//GEN-LAST:event_saveAsMenuActionPerformed

    private void saveAsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsButtonActionPerformed
        saveAs();
    }//GEN-LAST:event_saveAsButtonActionPerformed

    private void uploadMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadMenuActionPerformed
        try{
            analysisDriver.doAnalysis(filesDriver.readFile(fileChoosersCreator.getPath(this)), this, treeDriver);
        } catch(NullPointerException e){
            informationLabel.setText("Carga de lenguaje cancelada.");
            threadsDriver.clearLabel(informationLabel);
        }
    }//GEN-LAST:event_uploadMenuActionPerformed

    private void compileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compileButtonActionPerformed

    }//GEN-LAST:event_compileButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LCompilerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LCompilerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LCompilerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LCompilerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LCompilerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenu;
    private javax.swing.JButton compileButton;
    private javax.swing.JMenuItem compileMenu;
    private javax.swing.JButton deleteButton;
    private javax.swing.JMenuItem deleteMenu;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel informationLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator13;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextPane messageArea;
    private javax.swing.JButton newFileButton;
    private javax.swing.JMenuItem newFileMenu;
    private javax.swing.JButton openFileButton;
    private javax.swing.JMenuItem openFileMenu;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JButton saveAsButton;
    private javax.swing.JMenuItem saveAsMenu;
    private javax.swing.JButton saveFileButton;
    private javax.swing.JMenuItem saveFileMenu;
    private javax.swing.JMenuItem stackMenu;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JMenuItem tableMenu;
    private javax.swing.JButton uploadButton;
    private javax.swing.JMenuItem uploadMenu;
    // End of variables declaration//GEN-END:variables
}
