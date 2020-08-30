package com.hluther.entityClasses;
/**
 *
 * @author helmuth
 */
public class Transition {
    
    private int initialState;
    private int finalState;
    private String symbol;

    public Transition(int initialState, int finalState, String symbol) {
        this.initialState = initialState;
        this.finalState = finalState;
        this.symbol = symbol;
    }

    public int getInitialState() {
        return initialState;
    }

    public void setInitialState(int initialState) {
        this.initialState = initialState;
    }

    public int getFinalState() {
        return finalState;
    }

    public void setFinalState(int finalState) {
        this.finalState = finalState;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    
    
    
}
