package com.hluther.interpreter.AST;
/**
 *
 * @author helmuth
 */
public interface Instruction {
      
    public Object execute(SymbolTable symbolTable);
    
}
