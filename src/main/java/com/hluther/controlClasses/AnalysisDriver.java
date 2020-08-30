package com.hluther.controlClasses;

import com.hluther.gui.LCompilerFrame;
import com.hluther.controlClasses.NodesDriver;
import com.hluther.interpreter.lexer.Lexer;
import com.hluther.interpreter.parser.Parser;
import java.io.StringReader;
/**
 *
 * @author helmuth
 */
public class AnalysisDriver {
    
    public void doAnalysis(String data, LCompilerFrame lCompilerFrame, NodesDriver treeDriver){
        try {
            Parser parser = new Parser(new Lexer(new StringReader(data), lCompilerFrame), lCompilerFrame, treeDriver);
            parser.parse();
        } catch (Exception ex) {
            System.out.println("Error al analizar: " +ex.getMessage());
        }
    }
}
