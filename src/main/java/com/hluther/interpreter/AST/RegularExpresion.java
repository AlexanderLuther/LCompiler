package com.hluther.interpreter.AST;

import com.hluther.controlClasses.TreeMethodDriver;
import com.hluther.entityClasses.Node;
import java.util.ArrayList;
/**
 *
 * @author helmuth
 */
public class RegularExpresion implements Instruction{
        
    private String id;
    private Node regularExpresion;    
    private TreeMethodDriver treeMethod;
    private Node root;
    
    /**
     * Constructor de la clase
     * @param id Identificador de la expresion regular declarada.
     * @param regularExpresion arbol binario que contiene la estructura de la expresion regular.
     */
    public RegularExpresion(String id, Node regularExpresion){
        this.id = id;
        this.regularExpresion = regularExpresion;
        this.treeMethod = new TreeMethodDriver();
    }
      
    /**
     * Metodo encargado de llamar a la ejecucion del algoritmo del metodo del arbol.
     * @param symbolTable
     * @return 
     */
    @Override
    public Object execute(SymbolTable symbolTable) {
        //Expandir la expresion
        root = treeMethod.expandTree(regularExpresion);
        //Calcular anulable, primeraPos, ultimaPos y siguientePos
        treeMethod.calculateLeafNodes(root);
        treeMethod.calculateBranchNodes(root);      
        //Obtener el AFD resultante.
        System.out.println("\nArbol: " +id);
        treeMethod.getAFD(root);      
        return null;
    }   
  
}
