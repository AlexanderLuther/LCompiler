package com.hluther.entityClasses;

import java.util.LinkedList;
/**
 *
 * @author helmuth
 */
public class LLexer {
    
    private LinkedList<DeterministicFiniteAutomaton> automata = new LinkedList<>();
    private LinkedList<Token> tokens;
    private int selectedAutomaton = 0;
    private final char EOF = 03;
    private int row = 1;
    private int column = 0;
    
    public LLexer(LinkedList<DeterministicFiniteAutomaton> automata){
        this.automata = automata;
    }
    
    /**
     * Metodo encargado de realizar el analisis lexico.
     * Recorre la cadena de entrada y la analiza con cada uno de los automatas
     * disponibles, si algun automata devuelve un token es un lexema reconocido,
     * de lo contrario se crea un token Error.
     * @param data El texto que se desea analizar lexicamente.
     * @return 
     */
    public LinkedList<Token> getTokens(String data){
        row = 1;
        column = 0;
        data = data + EOF;
        tokens = new LinkedList<>();
        int index = 0;
        Token token;
        DeterministicFiniteAutomaton currentAuatomaton = automata.get(selectedAutomaton);
        currentAuatomaton.initializeAFD();
         //Recorrer la cadena caracter por caracter.
        while(index < data.length()){
            try{
                //Analizar el caracter actual con el automata actual.
                token = currentAuatomaton.analize(data.charAt(index));
                //Seguir en el automata actual y cambiar estados.
                if(token == null){
                    setRow(data.charAt(index));
                    column++;
                    index++;
                }
                //Lexema reconocido, agregar un nuevo token y reestablecer el automata a su estado inicial.
                else{
                    //Si el automata actual no corresponde a IGNORE se agrega el nuevo token.
                    if(!currentAuatomaton.isIgnore()){
                        token.setColumn(column - token.getLexeme().length());
                        token.setRow(row);
                        tokens.add(token);
                    }
                    //Regresar al automate inicial.
                    selectedAutomaton = 0;
                    currentAuatomaton = automata.get(selectedAutomaton);
                    currentAuatomaton.initializeAFD();
                    //Si el caracter actual es EOF avanzar en la cadena.
                    if(data.charAt(index) == EOF){
                        setRow(data.charAt(index));
                        column++;
                        index++;
                    }
                }
             //Cambiar de automata.
            } catch(LexemeException e){
                selectedAutomaton++;
                //Si aun no se ha probado con todos los automatas
                if(selectedAutomaton < automata.size()){
                    currentAuatomaton = automata.get(selectedAutomaton);
                    currentAuatomaton.initializeAFD();
                    lessRow(column-e.getValue(), column, data);
                    index  = index - e.getValue();
                    column = column - e.getValue();
                }
                //Si ya se probo con todos los automatas agregar Token error y reestablecer.
                else{
                    //Si el simbolo no es el de fin de cadena
                    if(data.charAt(index) != EOF){
                        tokens.add(new Token("Error", String.valueOf(data.charAt(index)), row, column));
                        setRow(data.charAt(index));
                        column++;
                        index++;
                        selectedAutomaton = 0;
                        currentAuatomaton = automata.get(selectedAutomaton);
                        currentAuatomaton.initializeAFD();
                    }
                    else{
                        setRow(data.charAt(index));
                        column++;
                        index++;
                        selectedAutomaton = 0;
                    }
                }
            }
        }
        
        
        for(int i = 0; i < tokens.size(); i++){
            System.out.println("Token: [" +tokens.get(i).getTokenId()+ "] Lexema: [" +tokens.get(i).getLexeme()+ "] Fila: " +tokens.get(i).getRow()+ " Columna: " +tokens.get(i).getColumn());
        }
        
        return null;
    }
    
    
    private void setRow(int symbol){
        if(symbol == 10){
            row++;
        }
    }
    
    private void lessRow(int start, int end, String data){
        String text = data.substring(start, end);
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) == '\n'){
                row--;
            }
        }
    }
    
   
}
