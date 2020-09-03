package com.hluther.interpreter.AST;

import com.hluther.controlClasses.TreeMethodDriver;
import com.hluther.entityClasses.DeterministicFiniteAutomaton;
import com.hluther.entityClasses.Node;
import com.hluther.gui.LCompilerFrame;
import com.hluther.interpreter.AST.Symbol.Type;
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
     * @param ignore
     */
    public RegularExpresion(String id, Node regularExpresion, boolean ignore){
        this.id = id;
        this.regularExpresion = regularExpresion;
        this.ignore = ignore;
        this.treeMethod = new TreeMethodDriver();
    }
    
    private DeterministicFiniteAutomaton getFDA(){
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
      
    /**
     * Metodo encargado de llamar a la ejecucion del algoritmo del metodo del arbol.
     * @param symbolTable
     * @param lCompilerFrame
     * @return 
     */
    @Override
    public Object execute(SymbolTable symbolTable, LCompilerFrame lCompilerFrame) { 
        if(ignore == true){
            return getFDA();
        }
        else{
            if(symbolTable.add(new Symbol(id, Type.REGULAR_EXPRESION))){
                return getFDA();
            }
            else{
                lCompilerFrame.printMessage("Error Semantico en seccion de expresiones regulares: [" +id+ "] no puede ser declarado porque ya existe en este ambito. La expresion regular se descarto\n"); 
            return null;
            }
        }
    }   
  
}
