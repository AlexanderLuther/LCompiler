package com.hluther.entityClasses;
import java.io.Serializable;
import java.util.LinkedList;
/**
 *
 * @author helmuth
 */
public class LLexer implements Serializable{
    
    private LinkedList<DeterministicFiniteAutomaton> automata = new LinkedList<>();
    private final char EOF = 03;
    private int column = 0;
    private int row = 1;
    
    public LLexer(LinkedList<DeterministicFiniteAutomaton> automata){
        this.automata = automata;
    }
  
    /**
     * Metodo encargado de: 
     * 1. Agregar el simbolo EOF al string data que se recibe como parametro e inicializar
     *    los automatas y lista de tokens.
     * 2. Recorrer el texto de entrada caracter por caracter.
     * 3. Analizar el caracter actual con cada uno de los automatas disponibles. Si el 
     *    caracter actual es EOF se establece en false el valor del atributo valid de 
     *    todos los automatas. De lo contrario si el atributo valid del automata actual
     *    es true se analiza el caracter con este automata.
     * 4. Si todos los automatas se encuentran con un valor valid = false se realiza el
     *    proceso de creacion de un nuevo token. De lo contrario se incrementa en uno el
     *    valor de index.
     * 5. Si el lexema reconocido es valido continua con el proceso de creacion de un 
     *    nuevo token. De lo contrario se crea un nuevo token Error.
     * 6. Se recorren todos los automatas y se buscan aquellos que se encuentren en un 
     *    estado final y no sean IGNORE, se crea un nuevo token del tipo que reconoce 
     *    el automata y si otro automata reconocio un lexema con una longitud mas grande
     *    que el lexema del token creado se crea un nuevo token con los parametros del 
     *    automata en turno.
     * 7. Si Token no es null(IGNORE) se agrega a la lista tokens y se inicializan todos
     *    los automatas.
     * @param data Cadena de texto a analizar.
     * @return Lista enlazada de tokens resultantes.
     */
    public LinkedList<Token> getTokens(String data){
        //--------------------  1   --------------------//
        data = data + EOF;
        column = 0;
        row = 1;
        int index = 0;
        LinkedList<Token> tokens = new LinkedList<>();
        initializaeAutomata();
        //--------------------  2   --------------------//
        while(index < data.length()){
        //--------------------  3   --------------------//    
            for(int i = 0; i < automata.size(); i++){
                if((int)data.charAt(index) != EOF){
                    if(automata.get(i).isActive()){
                        automata.get(i).consume((int)data.charAt(index));
                    }
                }
                else{
                    automata.get(i).setActive(false);
                }
            }
        //--------------------  4   --------------------//
            if(allAutamataInactive()){
        //--------------------  5   --------------------//        
                if(isValidLexeme()){
        //--------------------  6   --------------------//    
                    Token token = null;
                    int currentLength = 0;
                    int errorLength = 0;
                    for(DeterministicFiniteAutomaton dfa : automata){
                        if(dfa.isValidLexeme()){
                            if(!dfa.isIgnore()){
                                if(dfa.getLexeme().length() > currentLength){
                                    int columnValue;
                                    if(currentLength >= errorLength){ 
                                        columnValue = column - (dfa.getLexeme().length() + errorLength);
                                    }
                                    else{
                                        columnValue = column -(dfa.getLexeme().length() + errorLength - 1);
                                    }
                                    token = new Token(dfa.getId(), dfa.getLexeme(), row, columnValue);
                                    currentLength = dfa.getLexeme().length();
                                }
                            }
                        }
                        else{
                            if(dfa.getLexeme().length() > errorLength){
                                errorLength = dfa.getLexeme().length();
                            }
                        }
                    }
                    if(errorLength > currentLength){
                        index = index - (errorLength - currentLength);
                        column = index;
                    }
        //--------------------  7   --------------------// 
                    if(token != null) tokens.add(token);
                    if((int)data.charAt(index) == EOF) index++;
                    initializaeAutomata();
                }
                else{
                    if((int)data.charAt(index) != EOF) tokens.add(new Token("Error",  String.valueOf((char)data.charAt(index)), row, column));
                    setRow((int)data.charAt(index));
                    index++;
                    column++;
                    initializaeAutomata();
                } 
            }
            else{
                setRow((int)data.charAt(index));
                index++;
                column++;
            }
        }  
        return tokens;
    }
    
    /**
     * Metodo que inicializa cada uno de los automatas dentro del arrayList automata.
     */
    private void initializaeAutomata(){
        automata.forEach(dfa -> {
            dfa.initializeAFD();
        });
    }
    
    /**
     * Metodo que recorre cada uno de los automatas dentro de la lista automata.
     * @return True si todos los automatas estan inactivos, false si alguno esta activo.
     */
    private boolean allAutamataInactive(){
        return automata.stream().noneMatch(dfa -> (dfa.isActive()));
    }
    
    /**
     * Metodo que valida si algun automata quedo en estado de aceptacion.
     * @return True si se encuentra algun estado de aceptacion de lo contrario false.
     */ 
    private boolean isValidLexeme(){
        return automata.stream().anyMatch(dfa -> (dfa.isValidLexeme()));
    }
    
    /**
     * Metodo que agrega uno al valor de la variable row si el synbolo recibido es un salto de linea.
     * @param symbol Symbolo a analizar. 
     */
    private void setRow(int symbol){
        if(symbol == 10){
            row++;
            column = -1;
        }
    }
}
