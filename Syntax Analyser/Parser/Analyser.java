package Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Analyser {

    public static String getTokenType(String line){
        /*
         * gets lines from tokenizer output from compilation engine
         * retuns token type
         */
        String[] arr = line.split(" ");
        String token = arr[0];
        String tokenType = "";
        if(token.equals("<keyword>")){
            tokenType = "keyword";
        }
        else if(token.equals("<symbol>")){
            tokenType = "symbol";
        }
        else if(token.equals("<identifier>")){
            tokenType = "identifier";
        }
        else if(token.equals("<integerConstant>")){
            tokenType = "integerConstant";
        }
        else if(token.equals("<stringConstant>")){
            tokenType = "stringConstant";
        }
        return tokenType;
    }

    public static String getKeywordType(String line){
        /*
         * gets lines from tokenizer output from compilation engine
         * retuns keyword type
         */
        String[] arr = line.split(" ");
        String token = arr[1];
        String keywordType = "";
        if(token.equals("class")){
            keywordType = "class";
        }
        else if(token.equals("constructor")){
            keywordType = "constructor";
        }
        else if(token.equals("function")){
            keywordType = "function";
        }
        else if(token.equals("method")){
            keywordType = "method";
        }
        else if(token.equals("field")){
            keywordType = "field";
        }
        else if(token.equals("static")){
            keywordType = "static";
        }
        else if(token.equals("var")){
            keywordType = "var";
        }
        else if(token.equals("int")){
            keywordType = "int";
        }
        else if(token.equals("char")){
            keywordType = "char";
        }
        else if(token.equals("boolean")){
            keywordType = "boolean";
        }
        else if(token.equals("void")){
            keywordType = "void";
        }
        else if(token.equals("true")){
            keywordType = "true";
        }
        else if(token.equals("false")){
            keywordType = "false";
        }
        else if(token.equals("null")){
            keywordType = "null";
        }
        else if(token.equals("this")){
            keywordType = "this";
        }
        else if(token.equals("let")){
            keywordType = "let";
        }
        else if(token.equals("do")){
            keywordType = "do";
        }
        else if(token.equals("if")){
            keywordType = "if";
        }
        else if(token.equals("else")){
            keywordType = "else";
        }
        else if(token.equals("while")){
            keywordType = "while";
        }
        else if(token.equals("return")){
            keywordType = "return";
        }
        return keywordType;
        
    }

    public static boolean checkSymbolType(String line, String symbol){
        /*
         * gets lines from tokenizer output from compilation engine
         * retuns symbol type
         */
        String[] arr = line.split(" ");
        String token = arr[1];
        if(token.equals(symbol)){
            return true;
        }
        return false;
    }

    public static String getSymbolType(String line){
        /*
         * gets lines from tokenizer output from compilation engine
         * retuns symbol type
         */
        String[] arr = line.split(" ");
        String token = arr[1];
        String symbolType = "";
        if(token.equals("(")){
            symbolType = "(";
        }
        else if(token.equals(")")){
            symbolType = ")";
        }
        else if(token.equals("[")){
            symbolType = "[";
        }
        else if(token.equals("]")){
            symbolType = "]";
        }
        else if(token.equals("{")){
            symbolType = "{";
        }
        else if(token.equals("}")){
            symbolType = "}";
        }
        else if(token.equals(".")){
            symbolType = ".";
        }
        else if(token.equals(",")){
            symbolType = ",";
        }
        else if(token.equals(";")){
            symbolType = ";";
        }
        else if(token.equals("+")){
            symbolType = "+";
        }
        else if(token.equals("-")){
            symbolType = "-";
        }
        else if(token.equals("*")){
            symbolType = "*";
        }
        else if(token.equals("/")){
            symbolType = "/";
        }
        else if(token.equals("&")){
            symbolType = "&";
        }
        else if(token.equals("|")){
            symbolType = "|";
        }
        else if(token.equals("<")){
            symbolType = "<";
        }
        else if(token.equals(">")){
            symbolType = ">";
        }
        else if(token.equals("=")){
            symbolType = "=";
        }
        else if(token.equals("~")){
            symbolType = "~";
        }
        else{
            return null;
        }
        return symbolType;
    }

    public static String readLine(int count) throws IOException{
        String fileName = Main.passFilename();
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        ArrayList<String> lines = new ArrayList<String>();
        while(sc.hasNext()){
            lines.add(sc.nextLine());
        }
        return lines.get(count);
    }

   

}
