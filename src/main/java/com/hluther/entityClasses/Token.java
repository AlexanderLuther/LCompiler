package com.hluther.entityClasses;
/**
 *
 * @author helmuth
 */
public class Token {
    
    private String tokenId;
    private String lexeme;
    private int row;
    private int column;

    public Token(String tokenId, String lexeme, int row, int column) {
        this.tokenId = tokenId;
        this.lexeme = lexeme;
        this.row = row;
        this.column = column;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
            
}
