package com.hluther.interpreter.AST;

import com.hluther.entityClasses.LParser;
import com.hluther.gui.LCompilerFrame;
import java.util.LinkedList;
/**
 *
 * @author helmuth
 */
public class ParserCreator implements Instruction{

    private LinkedList<LinkedList<String>> productionRules;

    public ParserCreator(){
        productionRules = new LinkedList<>();
    }
    
    public void addRule(LinkedList<String> rule){
        productionRules.addFirst(rule);
    }
    
    @Override
    public Object execute(SymbolTable symbolTable, LCompilerFrame lCompilerFrame) {
        productionRules.forEach(list -> {
            System.out.println(list.toString());
        });
        
        return new LParser();
    }
    
}
