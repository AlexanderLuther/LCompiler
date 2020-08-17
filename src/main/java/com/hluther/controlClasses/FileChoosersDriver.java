package com.hluther.controlClasses;

import com.hluther.controlClasses.FilesDriver;
import com.hluther.entityClasses.Tab;
import com.hluther.gui.LCompilerFrame;
import javax.swing.JFileChooser;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author helmuth
 */
public class FileChoosersDriver {
    
    private FilesDriver filesDriver = new FilesDriver();
    private JFileChooser fileChooser;
    
    /*
    * Metodo encargado de abrir un JFileChooser y obtener el archivo seleccionado.
    * Crea y retorna una nueva intancia de la clase Tab enviando como parametos
    * la data contenida dentro del archivo, el nombre y la extension.
    */
    public Tab openFile(LCompilerFrame frame, ArrayList<Tab> tabs){
        String data = "";
        String name = "";
        String extension = "";
        String path = "";
        fileChooser = new JFileChooser();
        fileChooser.setApproveButtonText("Abrir");
        fileChooser.setDialogTitle("Abrir Archivo");  
        int selection = fileChooser.showOpenDialog(frame);      
        if(selection == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            data = filesDriver.readFile(file.toString());
            name = file.getName();
            extension = FilenameUtils.getExtension(name);
            path = file.getPath();
            if(isUnique(path, tabs)){
                return new Tab(name, extension, data, path);
            }
            else{
                return null;
            } 
        }
        return null;
    }
    
    /*
    * Metodo encargado de verificar si el path que se recibe como parametro
    * es igual al path de alguno de los tabs recibidos como parametro. Si
    * el path es unico devuelve true, caso contrario devuelve false.
    */
    private boolean isUnique(String path, ArrayList<Tab> tabs){
        for(Tab tab : tabs){
            if(tab.getPath().equals(path)){
                return false;
            }
        }
        return true;
    }
    
}
