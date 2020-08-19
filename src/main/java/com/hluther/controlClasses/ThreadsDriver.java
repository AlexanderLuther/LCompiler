package com.hluther.controlClasses;
import javax.swing.JLabel;
/**
 *
 * @author helmuthluther
 */
public class ThreadsDriver {
    
    /*
    Metodo encargado de dar una pausa de 2.5 segundos y posteriormente limpiar el contenido de la etiqueta
    que recibe como parametro.
    */
    public void clearLabel(JLabel label){
        Thread hilo = new Thread(){
        @Override 
        public  void run(){
            try {
                Thread.sleep(3500);
                label.setText("");
            }    
            catch (Exception e) {
                System.out.println(e);
            }
        }};
        hilo.start();
    }

    
}
