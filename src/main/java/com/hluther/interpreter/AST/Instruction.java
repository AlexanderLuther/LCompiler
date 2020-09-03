package com.hluther.interpreter.AST;
import com.hluther.gui.LCompilerFrame;
/**
 *
 * @author helmuth
 */
public interface Instruction {
      
    public Object execute(SymbolTable symbolTable, LCompilerFrame lCompilerFrame);
    
}
