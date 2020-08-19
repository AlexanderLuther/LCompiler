package com.hluther.controlClasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author helmuth
 */
public class FilesDriver {
    
    private String data;
    private String text;

    /*
    LEER ARCHIVO
    Metodo que recibe como parametro un path, el cual utiliza para la apertura de 
    un archivo y su posterior lectura. Devuelve un String con todos los datos 
    contenidos dentro del archivo.
    */
    public String readFile(String path){
        text = "";
        BufferedReader bufferReader = null;
	try {
            bufferReader = new BufferedReader(new FileReader(path));
            while ((data = bufferReader.readLine()) != null){    
                text = text  +data+ "\n";
            }
            if(text.length()!=0){
                text = text.substring(0, text.length()-1);
            }
	}
        catch (EOFException ex) {
            System.out.println("ERROR: Lectura finalizada");
	}
        catch (IOException ex) {
            System.out.println("ERROR: No se puede leer archivo");
	}
        finally{
            try {
		bufferReader.close();
            } 
            catch (IOException ex) {
		System.out.println("ERROR: No se pudo cerrar el archivo");
            }
	}
        return text;
    }
    
    /*
    Metodo encargado de crear archivos dentro la ruta que se especifica como parametro.
    */
    public boolean writeFile(String path, String data){
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.close();
	} catch (IOException ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    /*
    * Metodo encargado de crear un archivo en blanco. Recibe como parametro el path
    * del archivo que se desea crear.
    */
    public File createFile(String path) throws IOException{
        File file = new File(path);
        if (!file.exists()){
            file.createNewFile();
        }
        return file;
    }
    
}