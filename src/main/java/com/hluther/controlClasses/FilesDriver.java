package com.hluther.controlClasses;

import com.hluther.entityClasses.Language;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
    
    public void createDirectory(String path){
        File file = new File(path);
        file.mkdir();
    }
    
    public boolean deleteLanguage(String path){
        System.out.println(path);
        File file = new File("Lenguajes/" + path);
        return file.delete();
    }
    
    public void saveLanguage(Language language){
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("Lenguajes/" +language.getName() + ".l"));
            objectOutputStream.writeObject(language);
            objectOutputStream.close();  
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
        
    public ArrayList<String> getLanguages(){
        ArrayList<String> languages = new ArrayList<>();
        ObjectInputStream objectInputStream = null;
        Language language;
        try {
            File file = new File("Lenguajes");
            for(var path : file.list()){
                objectInputStream = new ObjectInputStream(new FileInputStream("Lenguajes/" + path));
                language = (Language)(objectInputStream.readObject());
                languages.add(language.getName());
                objectInputStream.close();
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error archivo no encontrado");
        } catch (IOException ex) {
            System.out.println("Error en la lectura del archivo");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error la clase no existe");
        } 
        return languages;
    }  
    
     public Language getLanguage(String path){
        ObjectInputStream objectInputStream = null;
        Language language = null;
        try {
            File file = new File("Lenguajes");
            objectInputStream = new ObjectInputStream(new FileInputStream("Lenguajes/" + path));
            language = (Language)(objectInputStream.readObject());
            objectInputStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error archivo no encontrado");
        } catch (IOException ex) {
            System.out.println("Error en la lectura del archivo");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error la clase no existe");
        } 
        return language;
    }  
    
}