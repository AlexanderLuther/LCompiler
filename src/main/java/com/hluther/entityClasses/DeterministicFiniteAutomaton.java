package com.hluther.entityClasses;

import java.util.ArrayList;
/**
 *
 * @author helmuth
 */
public class DeterministicFiniteAutomaton {
    
    private ArrayList<State[]> transitionsTable;
    private ArrayList<Integer> symbols;
    private boolean ignore;
    private String id;
    private State initialState;
    private int row;
    private int column;
    private State currentState;
    private State previousState;
    private String lexeme; 
    private final char EOF = 03;

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
    
    public void initializeAFD(){
        currentState = initialState;
        lexeme = "";
    }
    
    /**
     * Metodo que en base al symbolo recibido y el estado actual establece el movimiento
     * hacia un nuevo estado.
     * @param symbol
     * @return -Null:
     *          Se realizo un movimiento hacia un nuevo estado, la cadena es valida y el automata
     *          seguira reconociendo los siguientes caracteres.
     *         -Token:
     *          Al momento de detectar un simbolo que no es parte de los simbolos del automata
     *          si el estado actual es un estado de aceptacion se devuelve un nuevo token cuyo 
     *          lexema esta conformado con todos lo caracteres reconocidos en los estados anteriores.
     * @throws  LexemeException:
     *          Si el symbolo analizado no forma parte del automata o si se ha llegado a un estado de error.
     */
    public Token analize(int symbol) throws LexemeException{
        switch(symbol){
            //Si se ha llegado al final de la cadena.
            case EOF:
                return isValidToken(currentState);
            
            //Cualquier otro caracter que no sea fin de cadena.
            default:
                //El automata no se encuentra en un estado de error.
                if(currentState.getId() != -1){
                    column = getColumn(symbol);
                    row = currentState.getId();
                    //Si el simbolo forma parte de los simbolos del automata.
                    if(column != -1){
                        previousState = currentState;
                        currentState = transitionsTable.get(row)[column];
                        //Si el nuevo estado no es un estado de error
                        if(currentState.getId() != -1){
                            lexeme = lexeme + (char)symbol;
                        }
                        //Si el nuevo estado es un estado de error.
                        else{
                            return isValidToken(previousState);
                        }   
                    }
                    //Si el simbolo no forma parte de los simbolos del automata.
                    else{
                        return isValidToken(currentState);
                    }
                }
                //El automata se encuentra en un estado de error.
                else{
                    return isValidToken(previousState);
                }
            break;
        }
        return null;
    }
    
    /**
     * Metodo que valida si el automata se encuentra en un estado de aceptacion.
     * @param currentState Ultimo estado de no error donde estuvo el automata.
     * @return Un nuevo token si el estado es de aceptacion.
     * @throws Exception  Si el estado no es de aceptacion lanza una excepcion.
     */
    private Token isValidToken(State currentState) throws LexemeException{
        if(currentState != null){
            if(currentState.isFinalState()){
                return new Token(id, lexeme, 0, 0);
            } else{
                throw new LexemeException(lexeme.length());
            }
        } else{
            throw new LexemeException(lexeme.length());
        }
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