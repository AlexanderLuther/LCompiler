package com.hluther.entityClasses;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author helmuth
 */
public class DeterministicFiniteAutomaton implements Serializable{
    
    private String id;
    private int row;
    private int column;
    private String lexeme;
    private boolean ignore;
    private boolean active;
    private State initialState;
    private State currentState;
    private ArrayList<Integer> symbols;
    private ArrayList<State[]> transitionsTable;
    
    /**
     * Constructor de la clase
     * @param transitionsTable Tabla de transiciones.
     * @param symbols Simbolos aceptados por el automata.
     * @param initialState Estado inicial del automata.
     */
    public DeterministicFiniteAutomaton(ArrayList<State[]> transitionsTable, ArrayList<Integer> symbols, State initialState){
       this.transitionsTable = transitionsTable; 
       this.symbols = symbols;
       this.initialState= initialState; 
    }

    public String getId() {
        return id;
    }

    public void setId(String token) {
        this.id = token;
    }

    public boolean isIgnore() {
        return ignore;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isValidLexeme(){
        return currentState.isFinalState();
    }
    
    public String getLexeme(){
        return lexeme;
    }
    
    public void initializeAFD(){
        active = true;
        currentState = initialState;
        lexeme = "";
    }
    
    public void consume(int symbol){
        State previousState;
        //Si el simbolo es un simbolo reconocido por el automata
        if(isValidSymbol(symbol)){
            column = getColumn(symbol);
            row = currentState.getId();
            previousState = currentState;
            currentState = transitionsTable.get(row)[column];
            //Si el estado actual no es un estado de error.
            if(currentState.getId() != -1){
                lexeme = lexeme + (char)symbol;
            }
            //Si es un estado de error.
            else{
                currentState = previousState;
                active = false;
            }
        }
        else{
            active = false;
        }
    }
    
    /**
     * Metodo que valida si el simbolo recibido es un simbolo valido dentro del automata
     * @param symbol Simbolo que se desea validar
     * @return True si el simbolo es valido, false de lo contrario.
     */
    private boolean isValidSymbol(int symbol){
        return symbols.contains(symbol); 
    }
    
    /**
     * Metodo que obtiene el indice de columna que tiene dentro de la tabla de transiciones
     * el simbolo que se recibe como parametro.
     * @param symbol Simbolo al cual se desea buscar el indice de columna.
     * @return Indice de columna del simbolo.
     */
    private int getColumn(int symbol){
        return symbols.indexOf(symbol);
    }
   
}