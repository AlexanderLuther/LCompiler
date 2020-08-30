package com.hluther.controlClasses;

import com.hluther.entityClasses.Node;
import com.hluther.entityClasses.State;
import com.hluther.entityClasses.Transition;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * @author helmuth
 * Metodo del arbol para generar un AFD.
 * 1. Expandir la expresion usando el caracter ascii #254 ■
 * 2. El arbol sintatico de la expresion regular se encuentra construido. La raiz es el nodo root.
 * 3. Enumerar todos los nodos hoja que no son lambda y marcar su anulable como false.   
 * 4. Calcular anulable de cada nodo.
 * 5. Calcular PrimeraPos de cada nodo.
 * 6. Calcular UltimaPos de cada nodo.
 * 7. Calcular SiguientePos de cada nodo numerado.     
*/
public class TreeMethodDriver {
        
    private Hashtable<Integer, Node> leafNodes = new Hashtable<>();
    private ArrayList<String> symbols = new ArrayList<>();
    private final char SPECIAL_SYMBOL = 254;
    private int leafNodesCounter = 0;
    private ArrayList[] nextPos;
    private int statesCounter = 0;
    
    /**
     * Merodo que expande la expresion usando el caracter ascii #254 ■
     * @param regularExpresion arbol de sintaxis abstracta de la expresion regular.
     * @return root raiz del arbol de sintexis abstracta. 
     */
    public Node expandTree(Node regularExpresion){
        Node rightNode = new Node(String.valueOf(SPECIAL_SYMBOL), true);
        Node root = new Node(".", regularExpresion, rightNode, false);
        return root;
    }
    
    /**
     * Metodo que recorre el arbol en pre-orden y se encarga de enumerar, establecer a false 
     * el anulable, establecer el valor de primeraPos y ultimaPos de cada uno de los nodos hoja.
     * Por ultimo llama al metodo initializeNextPos().
     * @param root Nodo raiz del arbol que se recorre. 
     */
    public void calculateLeafNodes(Node root){
        if(root != null){
            if(root.isLeaf()){
                root.setNullable(false);
                root.setId(leafNodesCounter);
                root.addFirstPos(leafNodesCounter);
                root.addLastPos(leafNodesCounter);
                addSymbol(root.getValue());
                leafNodes.put(leafNodesCounter, root);
                leafNodesCounter++;
            }
            calculateLeafNodes(root.getLeft());
            calculateLeafNodes(root.getRight());
        }
        initializeNextPos();
    }
    
    /**
     * Metodo que recorre el arbol en post-orden y se encarga de establecer el valor del anulable,
     * primeraPos, ultimaPos de cada uno de los nodos rama. Tambien establece el valor de siguientePos
     * de cada uno de los nodos hoja.
     * @param root Nodo raiz del arbol que se recorre. 
     */
    public void calculateBranchNodes(Node root){
        if(root != null){
           calculateBranchNodes(root.getLeft());
           calculateBranchNodes(root.getRight()); 
            if(!root.isLeaf()){ 
                switch(root.getValue()){
                    case "|": 
                        root.setNullable(root.getLeft().isNullable() || root.getRight().isNullable());
                        root.setFirstPos((ArrayList)Stream.concat(root.getLeft().getFirstPos().stream(), root.getRight().getFirstPos().stream()).collect(Collectors.toList()));
                        root.setLastPos((ArrayList)Stream.concat(root.getLeft().getLastPos().stream(), root.getRight().getLastPos().stream()).collect(Collectors.toList()));
                        break;
                        
                    case ".": 
                        root.setNullable(root.getLeft().isNullable() && root.getRight().isNullable());
                        if(root.getLeft().isNullable()){ 
                            root.setFirstPos((ArrayList)Stream.concat(root.getLeft().getFirstPos().stream(), root.getRight().getFirstPos().stream()).collect(Collectors.toList()));
                        } else{
                            root.setFirstPos(root.getLeft().getFirstPos());
                        } 
                        if(root.getRight().isNullable()){
                            root.setLastPos((ArrayList)Stream.concat(root.getLeft().getLastPos().stream(), root.getRight().getLastPos().stream()).collect(Collectors.toList()));
                        } else{
                            root.setLastPos(root.getRight().getLastPos());
                        }
                        calculateNextPos(root.getRight().getFirstPos(), root.getLeft().getLastPos());
                        break;
                    
                    case "*": 
                        root.setNullable(true); 
                        root.setFirstPos(root.getLeft().getFirstPos());
                        root.setLastPos(root.getLeft().getLastPos());
                        calculateNextPos(root.getFirstPos(), root.getLastPos());
                        break;
                        
                    case "?": 
                        root.setNullable(true); 
                        root.setFirstPos(root.getLeft().getFirstPos());
                        root.setLastPos(root.getLeft().getLastPos());
                        break;
                    
                    case "+": 
                        root.setNullable(root.getLeft().isNullable()); 
                        root.setFirstPos(root.getLeft().getFirstPos());
                        root.setLastPos(root.getLeft().getLastPos());
                        calculateNextPos(root.getFirstPos(), root.getLastPos());
                    break;
                }
            }
        }
        setNextPos();
    }
    
    /**
     * Metodo que inicializa el vector nextPos con una longitud igual al valor de la 
     * varaible nodeId. Crea nuevas instancias de ArrayList dentro de cada uno de sus indices.
     */
    public void initializeNextPos(){
        nextPos = new ArrayList[leafNodesCounter];
        for(int i = 0; i < nextPos.length; i++){
            nextPos[i] = new ArrayList<>();
        }
    }
        
    /**
     * Metodo que calcula el valor de siguientePos de cada uno de los nodos hoja. Todos los valores 
     * dentro de primeraPos se agregan a siguientePos de cada elemento que hay en ultimaPos.
     * @param firstPos ArrayList que contiene el conjunto primeraPos.
     * @param lastPos ArrayList que contiene el conjunto ultimaPos.
     */
    public void calculateNextPos(ArrayList<Integer> firstPos, ArrayList<Integer> lastPos){
        for(int i = 0; i < firstPos.size(); i++){   
            for(int j = 0; j < lastPos.size(); j++){
                if(!nextPos[lastPos.get(j)].contains(firstPos.get(i))){
                    nextPos[lastPos.get(j)].add(firstPos.get(i));
                }
            }   
        }
    }
    
    /**
     * Metodo que asigna valor al atributo nextPos de cada uno de los nodos hoja.
     */
    private void setNextPos(){
        for(int i = 0; i < nextPos.length; i++){
            leafNodes.get(i).setNextPos(nextPos[i]);
        }
    }
    
    /**
     * Algoritmo para el calculo del AFD
     * @param root raiz del arbol de sintaxis abstracta.
     */
    public void getAFD(Node root){
        //ArrayLists y State usados en el algoritmo
        ArrayList<Integer> containedNodes = new ArrayList<>();
        ArrayList<Transition> transitions = new ArrayList<>();
        State currentState;
        State tempState;
        //Crear el conjunto vacio estadosD = {}
        ArrayList<State> statesD = new ArrayList<>();
        //Agregar el estado inicial 0 que es primeraPos de la raiz del arbol a estadosD
        statesD.add(new State(statesCounter, root.getFirstPos(), false, true, false));
        //Mientras exista un conjunto de estado currentState sin marcar en estadosD hacer
        while((currentState = getUnmarkedState(statesD)) != null){
            //Marcar currentState
            currentState.setMarkedState(true);
            //Por cada simbolo de entrada hacer
            for(int i = 0; i < symbols.size(); i++){
                //Nodos en currentState que corresponde al simbolo symbols.get(i) en el arbol.
                containedNodes = getNodes(symbols.get(i), currentState.getNodes());
                //Union de siguientePos de cada nodo en containedNodes
                tempState = createState(statesD, getUnion(containedNodes));
                //Validar que el nuevo estado no sea nulo.(Que se encuentre vacio)
                if(tempState != null){
                    //Agregar el nuevo estado
                    if(isNewState(statesD, tempState.getNodes())){
                        statesD.add(tempState);
                    }
                    //Agregar una transicion del estado actual al estado contenido en tempState.
                    transitions.add(new Transition(currentState.getId(), tempState.getId(), symbols.get(i)));
                }
                //Agregar una transicion no definida.      
                else{
                    transitions.add(new Transition(currentState.getId(), Integer.MAX_VALUE, symbols.get(i)));
                }
            }   
        }
        //Establecer estados finales.
        statesD = setFinalStates(statesD);
     
        System.out.println("Estados: " +statesD.size());
        for(State state : statesD){
            System.out.println(state.getId() +" "+ state.isFinalState());
        }
        System.out.println("Transiciones: " +transitions.size());
        for(int i = 0; i < transitions.size(); i++){
            System.out.println("D(" +transitions.get(i).getInitialState() +","+ transitions.get(i).getSymbol() +") = "+ transitions.get(i).getFinalState());
        }
    }
    
    /**
     * Metodo que varifica si existe algun estado sin marcar. (Que el valor marked sea false).
     * @param statesD Conjunto de estados a verificar.
     * @return El estado encontrado, de lo contrario null.
     */
    private State getUnmarkedState(ArrayList<State> statesD){
        for(State state : statesD){
            if(!state.isMarkedState()){
                return state;
            }
        }
        return null;
    }

    /**
     * Metodo que agrega al ArrayList symbols un nuevo simbolo usado en la expreion regular.
     * @param symbol Simbolo que se desea agregar.
     */
    private void addSymbol(String symbol){
        if(symbol.charAt(0) != (SPECIAL_SYMBOL) && !symbols.contains(symbol)){
            symbols.add(symbol);
        }
    }
    
    /**
     * Metodo que busca los nodos que corresponde a un simbolo dentro del conjunto de nodos
     * establecido por el parametro nodes.
     * @param symbol Simbolo para el cual se busca correspondencia en un nodo.
     * @param nodes Conjunto de nodos donde se debe de buscar.
     * @return containedNodes Conjunto de nodos encontrados.
     */
    private ArrayList<Integer> getNodes(String symbol, ArrayList<Integer> nodes){
        ArrayList<Integer> containedNodes = new ArrayList<>();
        for(Integer nodeId : nodes){ 
            if(leafNodes.get(nodeId).getValue().equals(symbol)){
                containedNodes.add(nodeId);
            }
        }
        return containedNodes;
    }
    
    /**
     * Metodo que une cada uno de los elementos contenidos dentro de siguientePos de cada
     * uno de los nodos recibidos como parametros dentro de un nuevo conjunto o estado. 
     * @param nodes Nodos sobre los cuales se desea obtener siguientePos.
     * @return El conjunto union de siguientePos de cada uno de los nodos.
     */
    private ArrayList<Integer> getUnion(ArrayList<Integer> nodes){
        ArrayList<Integer> state = new ArrayList<>();
        nodes.forEach(nodeId -> {
            leafNodes.get(nodeId).getNextPos().stream().filter(value -> (!state.contains(value))).forEachOrdered(value -> {
                state.add(value);
            });
        });
        return state;
    }
    
    /**
     * Metodo encargado de verificar si el estado que se recibe como parametro se encuentra
     * dentro del conjunto estadosD.
     * @param statesD Conjunto de estados donde se desea verificar.
     * @param newState Estado que se desea comparar.
     * @return false si el estado si existe dentro de estadosD, de lo contrario true.
     */
    private boolean isNewState(ArrayList<State> statesD, ArrayList<Integer> newState){
        return statesD.stream().noneMatch(state -> (state.getNodes().equals(newState)));
    }
    
    /**
     * Metodo que verifica  si algun estado esta conformado por los id de los nodos que se 
     * reciben como parametro. Tambien verifica que el ArrayList nodes no se encuentre vacio.
     * @param statesD estados que conforman el AFD.
     * @param nodes id's de los nodos que conformaran un estado.
     * @return Si el ArrayList nodes esta vacion se retorna null. Caso contrario si se 
     * encuentra algun estado se retorna. Si no se retorna un nuevo 
     * estado cuyos nodos son los que se recibieron como parametro.
     */
    private State createState(ArrayList<State> statesD, ArrayList<Integer> nodes){
        if(!nodes.isEmpty()){
            for(State state : statesD){       
                if(state.getNodes().equals(nodes)){
                    return state;
                }
            }
            statesCounter++;
            return new State(statesCounter, nodes, false, false, false);
        }
        else{
            return null;
        }
    }
    
    /**
     * Metodo que marca como estado final a todos aquellos estados que entre los id's
     * de sus nodos contengan en id del nodo que contiene el caracter ascii de expansion.
     * @param statesD Estados del AFD
     * @return statesD Estados del AFD ya marcados.
     */
    private ArrayList<State> setFinalStates(ArrayList<State> statesD){
    /*    int nodeId = 0;
        for (Node node : leafNodes.values()){
            if(node.getValue().equals(SPECIAL_SYMBOL)){
                nodeId = node.getId();
            }
        }
      */  
        for(State state : statesD){
            if(state.getNodes().contains(leafNodesCounter-1)){
                state.setFinalState(true);
            }
        }
        
        
        return statesD;
    }
}
