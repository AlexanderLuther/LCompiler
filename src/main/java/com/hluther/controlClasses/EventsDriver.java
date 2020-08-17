package com.hluther.controlClasses;

import com.hluther.gui.LCompilerFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.text.BadLocationException;

/**
 *
 * @author helmuth
 */
public class EventsDriver {
    
    /*
    * Metodo encargado de agregar un evento sobre un JTextArea para obtener
    * el numero de fila y de columna sobre el cual se encuentra el cursor e 
    * imprirlos dentro de un JLabel.
    */
    public static void addTextAreaEvent(JTextArea textArea, JLabel positionLabel){
        textArea.addCaretListener((CaretEvent e) -> {
            try {
                int position = textArea.getCaretPosition();
                int line = textArea.getLineOfOffset(position);
                int column = position - textArea.getLineStartOffset(line);
                line += 1;
                positionLabel.setText("Linea: " + line + "           Columna: " + column);
            }
            catch(BadLocationException ex){
                System.out.println("Error al capturar el evento");
            }
        }); 
    }
    
    /*
    * Metodo encargado de agregar un evento sobre un JButton para remover el
    * panel dentro del que se encuentra del JTabbedPane.
    */
    public static void addButtonEvent(String tabId, JButton button, LCompilerFrame frame){
        button.addActionListener((e) -> {
            int index = frame.getTabbedPane().indexOfTab(tabId);
            if (index >= 0) {
                frame.removePanel(index);
            }
        });
    }
    
}
