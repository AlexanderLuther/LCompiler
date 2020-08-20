package com.hluther.entityClasses;
/**
 *
 * @author helmuth
 */
public class Language {
    
    private String name;
    private String version;
    private String author;
    private int release;
    private String extension;
    private String sourceCode;
    
    public Language() {
        
    }

    public Language(String name, String version, String author, int release, String extension) {
        this.name = name;
        this.version = version;
        this.author = author;
        this.release = release;
        this.extension = extension;
        this.sourceCode = "";
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
    
    
}
