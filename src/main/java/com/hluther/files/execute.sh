cd "/home/helmuth/Documentos/Programas Desarrollados/NetBeansProjects/Java/Organizacion de Lenguajes y Compiladores 2/LCompiler/src/main/java/com/hluther/files"
java -jar java-cup-11b.jar -parser Parser Parser.cup
java -jar jflex-full-1.7.0.jar Lexer.lex
mv Lexer.java "/home/helmuth/Documentos/Programas Desarrollados/NetBeansProjects/Java/Organizacion de Lenguajes y Compiladores 2/LCompiler/src/main/java/com/hluther/interpreter/lexer/Lexer.java" 
mv Parser.java "/home/helmuth/Documentos/Programas Desarrollados/NetBeansProjects/Java/Organizacion de Lenguajes y Compiladores 2/LCompiler/src/main/java/com/hluther/interpreter/parser/Parser.java"
mv sym.java "/home/helmuth/Documentos/Programas Desarrollados/NetBeansProjects/Java/Organizacion de Lenguajes y Compiladores 2/LCompiler/src/main/java/com/hluther/interpreter/parser/sym.java"
