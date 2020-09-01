package com.hluther.interpreter.AST;
import com.hluther.entityClasses.DeterministicFiniteAutomaton;
import com.hluther.entityClasses.LLexer;
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
     * el AFD resultante. Guarda en un ArrayList los AFD's y crea una nueva instancia 
     * de la clase Lexer.
     * @param symbolTable tabla de símbolos del ámbito padre de la sentencia.
     * @return Estra instrucción retorna el LLexer creado.
     */
    @Override
    public Object execute(SymbolTable symbolTable) {
        regularExpresions.forEach(inst -> {
            automata.addFirst((DeterministicFiniteAutomaton)inst.execute(symbolTable));
        });
        return new LLexer(automata);
    }
}
