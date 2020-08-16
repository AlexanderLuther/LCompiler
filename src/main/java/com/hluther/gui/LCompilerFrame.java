package com.hluther.gui;

import com.hluther.controlClasses.AnalysisDriver;
import com.hluther.controlClasses.PrincipalFrameDriver;
import javax.swing.JTextArea;
/**
 *
 * @author helmuth
 */
public class LCompilerFrame extends javax.swing.JFrame {
    
    private final String WHITESPACE = " ";
    private boolean error;
    
    private PrincipalFrameDriver guiDriver = new PrincipalFrameDriver();
    private AnalysisDriver analysisDriver = new AnalysisDriver();
    private JTextArea textArea;
   
    public LCompilerFrame() {
        initComponents();
        textArea = guiDriver.addLineNumber(textAreaPanel);
        guiDriver.addTextAreaEvent(textArea, positionLabel);
        this.setLocationRelativeTo(null);
    }
        
    public void printProcess(String msg){
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
        openFileMenu = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        doAnalysisMenu = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        positionLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        textAreaPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openFileMenuBar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenuBar = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        doAnalysisMenuBar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Polinomios");
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

        openFileMenu.setBackground(new java.awt.Color(60, 63, 68));
        openFileMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder.png"))); // NOI18N
        openFileMenu.setBorderPainted(false);
        openFileMenu.setFocusable(false);
        openFileMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openFileMenu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/arrow.png"))); // NOI18N
        openFileMenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openFileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileMenuActionPerformed(evt);
            }
        });
        jToolBar1.add(openFileMenu);
        jToolBar1.add(jSeparator2);

        doAnalysisMenu.setBackground(new java.awt.Color(60, 63, 68));
        doAnalysisMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paly.png"))); // NOI18N
        doAnalysisMenu.setBorderPainted(false);
        doAnalysisMenu.setFocusable(false);
        doAnalysisMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        doAnalysisMenu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/play1.png"))); // NOI18N
        doAnalysisMenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        doAnalysisMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doAnalysisMenuActionPerformed(evt);
            }
        });
        jToolBar1.add(doAnalysisMenu);

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

        positionLabel.setForeground(new java.awt.Color(38, 169, 94));
        positionLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        positionLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        positionLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel5.add(positionLabel, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_END);

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

        textAreaPanel.setBackground(new java.awt.Color(48, 50, 55));
        textAreaPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 153)));
        textAreaPanel.setLayout(new javax.swing.BoxLayout(textAreaPanel, javax.swing.BoxLayout.LINE_AXIS));
        jPanel6.add(textAreaPanel, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel6, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1);

        jMenuBar1.setBackground(new java.awt.Color(48, 50, 55));

        fileMenu.setForeground(new java.awt.Color(255, 255, 255));
        fileMenu.setText("Archivo");
        fileMenu.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 13)); // NOI18N

        openFileMenuBar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        openFileMenuBar.setBackground(new java.awt.Color(48, 50, 55));
        openFileMenuBar.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 12)); // NOI18N
        openFileMenuBar.setForeground(new java.awt.Color(255, 255, 255));
        openFileMenuBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder.png"))); // NOI18N
        openFileMenuBar.setText("Abrir Archivo...");
        openFileMenuBar.setOpaque(true);
        openFileMenuBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileMenuBarActionPerformed(evt);
            }
        });
        fileMenu.add(openFileMenuBar);
        fileMenu.add(jSeparator1);

        exitMenuBar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        exitMenuBar.setBackground(new java.awt.Color(48, 50, 55));
        exitMenuBar.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 12)); // NOI18N
        exitMenuBar.setForeground(new java.awt.Color(255, 255, 255));
        exitMenuBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logout.png"))); // NOI18N
        exitMenuBar.setText("Salir");
        exitMenuBar.setOpaque(true);
        exitMenuBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuBarActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuBar);

        jMenuBar1.add(fileMenu);

        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Analisis");
        jMenu1.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 13)); // NOI18N

        doAnalysisMenuBar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        doAnalysisMenuBar.setBackground(new java.awt.Color(48, 50, 55));
        doAnalysisMenuBar.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 12)); // NOI18N
        doAnalysisMenuBar.setForeground(new java.awt.Color(255, 255, 255));
        doAnalysisMenuBar.setText("Analizar");
        doAnalysisMenuBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doAnalysisMenuBarActionPerformed(evt);
            }
        });
        jMenu1.add(doAnalysisMenuBar);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuBarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuBarActionPerformed

    private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertButtonActionPerformed
      //  messageArea.setText("");
        error = false;
    }//GEN-LAST:event_convertButtonActionPerformed

    private void openFileMenuBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileMenuBarActionPerformed
        guiDriver.openFile(textArea, this);
    }//GEN-LAST:event_openFileMenuBarActionPerformed

    private void openFileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileMenuActionPerformed
        guiDriver.openFile(textArea, this);
    }//GEN-LAST:event_openFileMenuActionPerformed

    private void doAnalysisMenuBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doAnalysisMenuBarActionPerformed
        messageArea.setText("");
        analysisDriver.doAnalysis(textArea.getText(), this);
    }//GEN-LAST:event_doAnalysisMenuBarActionPerformed

    private void doAnalysisMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doAnalysisMenuActionPerformed
        messageArea.setText("");
        analysisDriver.doAnalysis(textArea.getText(), this);
    }//GEN-LAST:event_doAnalysisMenuActionPerformed

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
    private javax.swing.JButton doAnalysisMenu;
    private javax.swing.JMenuItem doAnalysisMenuBar;
    private javax.swing.JMenuItem exitMenuBar;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
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
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextPane messageArea;
    private javax.swing.JButton openFileMenu;
    private javax.swing.JMenuItem openFileMenuBar;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JPanel textAreaPanel;
    // End of variables declaration//GEN-END:variables
}
