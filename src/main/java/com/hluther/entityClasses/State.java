package com.hluther.entityClasses;

import java.util.ArrayList;
/**
 *
 * @author helmuth
 */
public class State {
    
    private int id;
    private ArrayList<Integer> nodes;
    private boolean markedState;
    private boolean initialState;
    private boolean finalState;

    public State(int id, ArrayList<Integer> nodes, boolean markedState, boolean initialState, boolean finalState) {
        this.id = id;
        this.nodes = nodes;
        this.markedState = markedState;
        this.initialState = initialState;
        this.finalState = finalState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Integer> nodes) {
        this.nodes = nodes;
    }

    public boolean isMarkedState() {
        return markedState;
    }

    public void setMarkedState(boolean markedState) {
        this.markedState = markedState;
    }

    public boolean isInitialState() {
        return initialState;
    }

    public void setInitialState(boolean initialState) {
        this.initialState = initialState;
    }

    public boolean isFinalState() {
        return finalState;
    }

    public void setFinalState(boolean finalState) {
        this.finalState = finalState;
    }

}