package com.hluther.controlClasses;

import com.hluther.controlClasses.FilesDriver;
import com.hluther.entityClasses.Tab;
import com.hluther.gui.LCompilerFrame;
import javax.swing.JFileChooser;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author helmuth
 */
public class FileChoosersDriver {
    
    private FilesDriver filesDriver;
    private JFileChooser fileChooser;
    private final FileNameExtensionFilter LEN_FILTER = new FileNameExtensionFilter("LEN","len");
    
    public FileChoosersDriver(FilesDriver filesDriver){
        this.filesDriver = filesDriver;
    }
    
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
    * Metodo encargado de abrir un JFileChooser para elegir la ruta donde se 
    * guardara un nuevo archivo. Se obtiene la ruta y se llama a la creacion 
    * de un nuevo archivo vacio con la ruta obtenida. Se establecen los 
    * atributos name, extension, path y tittle de la instancia de la clase tab
    * recibida como parametro en base al archivo creado. Por ultimo se llama al
    * metodo writeFile para llenar con informacion el archivo.
    */
    public boolean saveFile(LCompilerFrame frame, Tab tab) throws IOException{
        String path = "";
        fileChooser = new JFileChooser();
        fileChooser.setApproveButtonText("Guardar");
        fileChooser.setDialogTitle("Guardar Archivo");
        fileChooser.setSelectedFile(new File(tab.getName()));
        int selection = fileChooser.showOpenDialog(frame);   
        if(selection == JFileChooser.APPROVE_OPTION){
            //Crear archivo en blanco.
            path = fileChooser.getSelectedFile().getAbsolutePath();
            File file = filesDriver.createFile(path);
            //Establecer los atributos de la instancia tab.
            tab.setName(file.getName());
            tab.setExtension(FilenameUtils.getExtension(tab.getName()));
            tab.setPath(file.getPath());
            tab.setTitle(tab.getName());
            //Escribir dentro del archivo creado.
            filesDriver.writeFile(tab.getPath(), tab.getData());
            return true;
        }
        return false;
    }
    
    /*
    * Metodo encargado de abrir un JFileChooser para la seleccion de un 
    * archivo con extension .len. Devuelve el path del archivo seleccionado.
    */
    public String getPath(LCompilerFrame frame){
        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(LEN_FILTER);
        fileChooser.setApproveButtonText("Cargar");
        fileChooser.setDialogTitle("Cargar Lenguaje");  
        int selection = fileChooser.showOpenDialog(frame);      
        if(selection == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile().getPath();
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
