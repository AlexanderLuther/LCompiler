package com.hluther.interpreter.AST;

import com.hluther.gui.LCompilerFrame;
import com.hluther.interpreter.AST.Symbol.Type;

/**
 * Clase que ejecuta las acciones de una instrucción de declaración y que implementa
 * la interfaz de instrucción
 * @author Alexander Luther
 */
public class Declaration implements Instruction{
    
    private final String id;
    private final Type type;
    /**
     * Constructor de la clase
     * @param symbol
     * @param type Tipo de la clase que será declarada
     */
    public Declaration(String id, Type type) {
        this.id = id;
        this.type = type;
    }
    
    /**
     * Método que ejecuta la accion de declarar una variable, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable Tabla de símbolos del ámbito padre.
     * @return No retorna nada porque no es una sentencia que genere un valor.
     */
    @Override
    public Object execute(SymbolTable symbolTable, LCompilerFrame lCompilerFrame) {
        if(!symbolTable.add(new Symbol(id, type))){
            lCompilerFrame.printMessage("Error Semantico en seccion de Simbolos: [" +id+ "] no puede ser declarado porque ya existe en este ambito.\n");  
        }
        return null;
    }
}
