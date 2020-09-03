package com.hluther.interpreter.AST;
import com.hluther.entityClasses.DeterministicFiniteAutomaton;
import com.hluther.entityClasses.LLexer;
import com.hluther.gui.LCompilerFrame;
import java.util.LinkedList;

/**
 * Clase que ejecuta las acciones de una instrucción si...entonces y que implementa
 * la interfaz de instrucción
 * @author Alexander Luther
 */
public class LexerCreator implements Instruction{

    private final LinkedList<Instruction> regularExpresions;
    private final LinkedList<DeterministicFiniteAutomaton> automata = new LinkedList<>();
    
    /**
     * Constructor de la clase.
     * @param regularExpresions Lista de expresiones regulares.
     */
    public LexerCreator(LinkedList<Instruction> regularExpresions) {
        this.regularExpresions = regularExpresions;
    }
    
    /**
     * Método que ejecuta la instruccion de cada expresion regular recibida y obtiene
     * el AFD resultante.Guarda en un ArrayList los AFD's y crea una nueva instancia 
     * de la clase Lexer.
     * @param symbolTable tabla de símbolos del ámbito padre de la sentencia.
     * @param lCompilerFrame
     * @return Estra instrucción retorna el LLexer creado.
     */
    @Override
    public Object execute(SymbolTable symbolTable, LCompilerFrame lCompilerFrame) {
        for(int i = regularExpresions.size() - 1; i >= 0; i--){
            DeterministicFiniteAutomaton dfa = (DeterministicFiniteAutomaton)regularExpresions.get(i).execute(symbolTable, lCompilerFrame);
            if(dfa != null){
                automata.addLast(dfa);
            }
        }
        return new LLexer(automata);
    }
}
