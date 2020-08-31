package com.hluther.entityClasses;
/**
 *
 * @author helmuth
 */
public class LexemeException extends Exception{
    
    private int value;

    public LexemeException(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
