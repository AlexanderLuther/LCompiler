package com.hluther.interpreter.lexer;
import com.hluther.gui.LCompilerFrame;
import com.hluther.interpreter.parser.sym;
import java_cup.runtime.*;
%%//

/* 
----------------------------------- Opciones y declaraciones de JFlex -----------------------------------
*/
%class Lexer
%cup
%cupdebug
%line 
%column
%public
%states CODE, REGULAR_EXPRESIONS_DECLARATION, REGULAR_EXPRESIONS_DEFINITION, SYMBOLS, GRAMMAR, GRAMMAR_CODE, ID

Letter = [a-zA-Z]
Number = [0-9]
InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

/* 
----------------------------------- Codigo Java -----------------------------------
*/
%{
    private LCompilerFrame lCompilerFrame;
    private String errorLexeme; 
    private int line;
    private int column;

    public Lexer(java.io.Reader in, LCompilerFrame lCompilerFrame) {
        this.lCompilerFrame = lCompilerFrame;    
        this.zzReader = in;
        this.errorLexeme = "";
        line = -1;
        column = -1;
    }
        
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn, value);
    }

    private void printToken(String token){
        System.out.println(token);
    }

    private void createErrorLexeme(String lexeme, int line, int column){
        if(this.line == -1 && this.column == -1){
            this.line = line;
            this.column = column;
        }
        errorLexeme = errorLexeme + lexeme;
    }

    private void printError(){
        if(line != -1 && column != -1){
            lCompilerFrame.printMessage("Error Lexico: Lexema ["+errorLexeme+"] no reconocido en esta seccion del archivo. Linea: "+line+" Columna: "+column + "\n");
            errorLexeme = "";
            line = -1;
            column = -1;
        }
    }

%}
%%//

/* 
----------------------------------- Reglas Lexicas ----------------------------------- 
*/
<YYINITIAL>{
    "%%"                                            { yybegin(CODE); printToken("SEPARATOR"); printError(); return symbol(sym.SEPARATOR, yytext()); }
    "//"{InputCharacter}* {LineTerminator}?         { printToken("LINECOMMENT"); printError(); } //Ignore
    ("/*" [^*/]* "*/") | ("/*" [^/]~ "*/")          { printToken("BLOCKCOMMENT"); printError(); } //Ignore
    ":"                                             { printToken("COLON"); printError(); return symbol(sym.COLON, yytext()); }
    ";"                                             { printToken("SEMICOLON"); printError(); return symbol(sym.SEMICOLON, yytext()); }
    "."                                             { printToken("FULLSTOP"); printError(); return symbol(sym.FULLSTOP, yytext()); }
    "nombre"                                        { printToken("NAME"); printError(); return symbol(sym.NAME, yytext()); }
    "version"                                       { printToken("VERSION"); printError(); return symbol(sym.VERSION, yytext()); }
    "autor"                                         { printToken("AUTHOR"); printError(); return symbol(sym.AUTHOR, yytext()); }
    "lanzamiento"                                   { printToken("RELEASE"); printError(); return symbol(sym.RELEASE, yytext()); }
    "extension"                                     { printToken("EXTENSION"); printError(); return symbol(sym.EXTENSION, yytext()); }
    {Number}+                                       { printToken("INTEGERNUM"); printError(); return symbol(sym.INTEGERNUM, new Integer(yytext())); }
    {Letter} ({Letter} | {Number})*                 { printToken("ID"); printError(); return symbol(sym.ID, yytext()); }
    {WhiteSpace}                                    { printError(); } //Ignore
    [^]                                             { printToken("ERROR"); createErrorLexeme(yytext(), (yyline+1), yycolumn); } 
}

<CODE>{
    "%%"                                            { yybegin(REGULAR_EXPRESIONS_DECLARATION); printToken("SEPARATOR"); return symbol(sym.SEPARATOR, yytext()); }
    {WhiteSpace}                                    { printToken("SPECIALCHARACTER"); return symbol(sym.SPECIALCHARACTER, yytext()); }
    [^]                                             { printToken("CODE"); return symbol(sym.CODE, yytext()); }
}

<REGULAR_EXPRESIONS_DECLARATION>{
    "%%"                                            { yybegin(SYMBOLS); printToken("SEPARATOR"); printError(); return symbol(sym.SEPARATOR, yytext()); }
    "="                                             { yybegin(REGULAR_EXPRESIONS_DEFINITION); printToken("EQUALS"); printError(); return symbol(sym.EQUALS, yytext()); }
    "&"                                             { printToken("IGNORE"); return symbol(sym.IGNORE, yytext()); }
    "//"{InputCharacter}* {LineTerminator}?         { printToken("LINECOMMENT"); printError(); } //Ignore
    ("/*" [^*/]* "*/") | ("/*" [^/]~ "*/")          { printToken("BLOCKCOMMENT"); printError(); } //Ignore
    {Letter} ({Letter} | {Number})*                 { printToken("ID"); printError(); return symbol(sym.ID, yytext()); }
    {WhiteSpace}                                    { printError(); } //Ignore
    [^]                                             { printToken("ERROR"); createErrorLexeme(yytext(), (yyline+1), yycolumn); }
}

<REGULAR_EXPRESIONS_DEFINITION>{
    ";"                                             { yybegin(REGULAR_EXPRESIONS_DECLARATION); printToken("SEMICOLON"); return symbol(sym.SEMICOLON, yytext()); }
    "//"{InputCharacter}* {LineTerminator}?         { printToken("LINECOMMENT"); } //Ignore
    ("/*" [^*/]* "*/") | ("/*" [^/]~ "*/")          { printToken("BLOCKCOMMENT"); } //Ignore
    "?"                                             { printToken("QUESTIONMARK"); return symbol(sym.QUESTIONMARK, yytext()); }
    "*"                                             { printToken("ASTERISK"); return symbol(sym.ASTERISK, yytext()); }
    "+"                                             { printToken("PLUS"); return symbol(sym.PLUS, yytext()); }
    "|"                                             { printToken("VERTICALBAR"); return symbol(sym.VERTICALBAR, yytext()); }
    "["{Number}"-"{Number}"]"                       { printToken("MACRONUMBERS"); return symbol(sym.MACRONUMBERS, yytext().replace("[", "").replace("]", "").replaceAll(" ", "")); }
    "["[A-Z]"-"[A-Z]"]"                             { printToken("MACROLETTERSMAY"); return symbol(sym.MACROLETTERSMAY, yytext().replace("[", "").replace("]", "").replaceAll(" ", "")); }
    "["[a-z]-[a-z]"]"                               { printToken("MACROLETTERSMIN"); return symbol(sym.MACROLETTERSMIN, yytext().replace("[", "").replace("]", "").replaceAll(" ", "")); }
    "\\n"                                           { printToken("LINEBREAK"); return symbol(sym.LINEBREAK, yytext()); }
    "\\t"                                           { printToken("TAB"); return symbol(sym.TAB, yytext()); }
    "\\b"                                           { printToken("BLANKSPACE"); return symbol(sym.BLANKSPACE, yytext()); }
    "\""                                            { printToken("DOUBLEQUOTES"); return symbol(sym.DOUBLEQUOTES, yytext()); } 
    "("                                             { printToken("PARENTHESESO"); return symbol(sym.PARENTHESESO, yytext()); }
    ")"                                             { printToken("PARENTHESESC"); return symbol(sym.PARENTHESESC, yytext()); }
    "["                                             { printToken("SQUAREBRACKETO"); return symbol(sym.SQUAREBRACKETO, yytext()); }
    "]"                                             { printToken("SQUAREBRACKETC"); return symbol(sym.SQUAREBRACKETC, yytext()); } 
    {WhiteSpace}                                    { } //Ignore
    [^]                                             { printToken("CHARACTER"); return symbol(sym.CHARACTER, yytext()); }
}

<SYMBOLS>{
    "%%"                                            { yybegin(GRAMMAR); printToken("SEPARATOR"); printError(); return symbol(sym.SEPARATOR, yytext()); }
    "//"{InputCharacter}* {LineTerminator}?         { printToken("LINECOMMENT"); printError(); } //Ignore
    ("/*" [^*/]* "*/") | ("/*" [^/]~ "*/")          { printToken("BLOCKCOMMENT"); printError(); } //Ignore
    ","                                             { printToken("COMMA"); printError(); return symbol(sym.COMMA, yytext()); }
    ";"                                             { printToken("SEMICOLON"); printError(); return symbol(sym.SEMICOLON, yytext()); }
    "terminal"                                      { printToken("TERMINAL"); printError(); return symbol(sym.TERMINAL, yytext()); }
    "no"                                            { printToken("NOT"); printError(); return symbol(sym.NOT, yytext()); }
    "entero"                                        { printToken("INTEGER"); printError(); return symbol(sym.INTEGER, yytext()); }
    "real"                                          { printToken("REAL"); printError(); return symbol(sym.REAL, yytext()); }
    "cadena"                                        { printToken("STRING"); printError(); return symbol(sym.STRING, yytext()); }
    [A-Z]+                                          { printToken("NONTERMINALSYM"); printError(); return symbol(sym.NONTERMINALSYM, yytext()); }
    [a-z]+                                          { printToken("TERMINALSYM"); printError(); return symbol(sym.TERMINALSYM, yytext()); }
    {WhiteSpace}                                    { printError(); } //Ignore
    [^]                                             { printToken("ERROR"); createErrorLexeme(yytext(), (yyline+1), yycolumn); }
}

<GRAMMAR>{
    ":"                                             { yybegin(ID); printToken("COLON"); printError(); return symbol(sym.COLON, yytext()); }
    "::"                                            { printToken("DOBLECOLON"); printError(); return symbol(sym.DOBLECOLON, yytext()); }    
    "{"                                             { yybegin(GRAMMAR_CODE); printToken("CURLYBRACKETO"); return symbol(sym.CURLYBRACKETO, yytext()); }
    ";"                                             { printToken("SEMICOLON"); return symbol(sym.SEMICOLON, yytext()); }
    "//"{InputCharacter}* {LineTerminator}?         { printToken("LINECOMMENT"); printError(); } //Ignore
    ("/*" [^*/]* "*/") | ("/*" [^/]~ "*/")          { printToken("BLOCKCOMMENT"); printError(); } //Ignore
    [A-Z]+                                          { printToken("NONTERMINALSYM"); printError(); return symbol(sym.NONTERMINALSYM, yytext()); }
    [a-z]+                                          { printToken("TERMINALSYM"); printError(); return symbol(sym.TERMINALSYM, yytext()); }
    {WhiteSpace}                                    { printError(); } //Ignore
    [^]                                             { printToken("ERROR"); createErrorLexeme(yytext(), (yyline+1), yycolumn); }
}

<ID>{
    "//"{InputCharacter}* {LineTerminator}?         { printToken("LINECOMMENT"); printError(); } //Ignore
    ("/*" [^*/]* "*/") | ("/*" [^/]~ "*/")          { printToken("BLOCKCOMMENT"); printError(); } //Ignore
    {Letter} ({Letter} | {Number})*                 { yybegin(GRAMMAR); printToken("ID"); printError(); return symbol(sym.ID, yytext()); }
    {WhiteSpace}                                    { printError(); } //Ignore
    [^]                                             { yybegin(GRAMMAR); printToken("ERROR"); createErrorLexeme(yytext(), (yyline+1), yycolumn); }
}

<GRAMMAR_CODE>{
    "}"                                             { yybegin(GRAMMAR); printToken("CURLYBRACKETC"); return symbol(sym.CURLYBRACKETC, yytext()); } 
    {WhiteSpace}                                    { printToken("SPECIALCHARACTER"); return symbol(sym.SPECIALCHARACTER, yytext()); }
    [^]                                             { printToken("CODE"); return symbol(sym.CODE, yytext()); }
}

