package com.hluther.interpreter.AST;
import java.util.LinkedList;

/**
 * Clase que ejecuta las acciones de una instrucción si...entonces y que implementa
 * la interfaz de instrucción
 * @author Alexander Luther
 */
public class LexerCreator implements Instruction{

    private final LinkedList<Instruction> instructionsList;
    
    /**
     * Primer constructor de la clase, este se utiliza cuando la instrucción no 
     * tiene clausula ELSE.
     * @param condition Condición del si..entonces
     * @param instructionsList Lista de instrucciones que deberían ejecutarse si la condición se cumple
     */
    public LexerCreator(LinkedList<Instruction> instructionsList) {
        this.instructionsList = instructionsList;
    }
    
    /**
     * Método que ejecuta la instrucción si..entonces, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable tabla de símbolos del ámbito padre de la sentencia.
     * @return Estra instrucción retorna nulo porque no produce ningún valor en 
     * su ejecución
     */
    @Override
    public Object execute(SymbolTable symbolTable) {
        for(Instruction inst: instructionsList){
            inst.execute(symbolTable);
        }
        
        
         /*   if(instructionsList != null){
                SymbolTable localTable = new SymbolTable();
                localTable.addAll(symbolTable);
                for(int i = instructionsList.size() -1; i >= 0; i--){
                    instructionsList.get(i).execute(localTable);
                }
                
            }*/
        
        return null;
    }
}
