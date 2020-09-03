package com.hluther.interpreter.AST;

import com.hluther.controlClasses.TreeMethodDriver;
import com.hluther.entityClasses.DeterministicFiniteAutomaton;
import com.hluther.entityClasses.Node;
import com.hluther.gui.LCompilerFrame;
/**
 *
 * @author helmuth
 */
public class RegularExpresion implements Instruction{
        
    private String id;
    private Node regularExpresion;    
    private boolean ignore;
    private TreeMethodDriver treeMethod;
    private Node root;
    private DeterministicFiniteAutomaton dfa;
    
    /**
     * Constructor de la clase
     * @param id Identificador de la expresion regular declarada.
     * @param regularExpresion arbol binario que contiene la estructura de la expresion regular.
     */
    public RegularExpresion(String id, Node regularExpresion, boolean ignore){
        this.id = id;
        this.regularExpresion = regularExpresion;
        this.ignore = ignore;
        this.treeMethod = new TreeMethodDriver();
    }
      
    /**
     * Metodo encargado de llamar a la ejecucion del algoritmo del metodo del arbol.
     * @param symbolTable
     * @return 
     */
    @Override
    public Object execute(SymbolTable symbolTable, LCompilerFrame lCompilerFrame) {
        //Expandir la expresion
        root = treeMethod.expandTree(regularExpresion);
        //Calcular anulable, primeraPos, ultimaPos y siguientePos
        treeMethod.calculateLeafNodes(root);
        treeMethod.calculateBranchNodes(root);      
        //Obtener y retornar el AFD resultante.
        dfa = treeMethod.getAFD(root);
        dfa.setId(id);
        dfa.setIgnore(ignore);
        //Liberar memoria
        System.gc();
        return dfa;
    }   
  
}
