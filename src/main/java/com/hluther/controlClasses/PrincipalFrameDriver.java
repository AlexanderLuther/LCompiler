package com.hluther.controlClasses;

import com.hluther.gui.LineNumber;
import com.hluther.gui.LCompilerFrame;
import java.awt.GridLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.text.BadLocationException;
/**
 *
 * @author helmuth
 */
public class PrincipalFrameDriver {
    
    private FilesDriver filesDriver = new FilesDriver();
    private LineNumber lineNumber;
    private JFileChooser fileChooser;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    
    public void openFile(JTextArea textArea, LCompilerFrame frame){
        fileChooser = new JFileChooser();
        fileChooser.setApproveButtonText("Abrir");
        fileChooser.setDialogTitle("Abrir Archivo");  
        int selection = fileChooser.showOpenDialog(frame);      
        if(selection == JFileChooser.APPROVE_OPTION){
            String data = this.filesDriver.readFile(fileChooser.getSelectedFile().toString());
            textArea.setText(data);
        }
    }
    
    public void addTextAreaEvent(JTextArea textArea, JLabel positionLabel){
        textArea.addCaretListener((CaretEvent e) -> {
            try {
                int actualPosition = textArea.getCaretPosition();
                int line= textArea.getLineOfOffset(actualPosition);
                int column = actualPosition - textArea.getLineStartOffset(line);
                line += 1;
                positionLabel.setText("Linea: " + line + "           Columna: " + column);
            }
            catch(BadLocationException ex){
                System.out.println("Error al capturar el evento");
            }
        }); 
    }
    
    public JTextArea addLineNumber(JPanel textAreaPanel){
        textArea = new JTextArea();
        lineNumber= new LineNumber(textArea);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setRowHeaderView(lineNumber);
        textAreaPanel.setLayout(new GridLayout());
        textAreaPanel.add(scrollPane);
        return textArea;
    }
}
