package Tokenizer;

import java.util.ArrayList;
import java.util.Arrays;

class Library{

    //for keyword
    static ArrayList<String> keyword = new ArrayList<>(Arrays.asList(
        "class",    
        "constructor",
        "int",
        "void",
        "boolean",
        "char",
        "do",
        "else",
        "false",
        "field",
        "function",
        "if",
        "let",
        "method",
        "null",
        "return",
        "static",
        "this",
        "true",
        "var",
        "while"));

    static ArrayList<String> symbols = new ArrayList<>(Arrays.asList(
            "{",
            "}",
            "(",
            ")",
            "[",
            "]",
            ",",
            ".",
            ";",
            "+",
            "-",
            "*",
            "/",
            "&",
            "^",
            "|",
            "<",
            ">",
            "=",
            "~"
    ));

    public static String[] tokenParse(String token){
        //this method returns the token and its xml keyword as string
        String[] arr1 = new String[2];
        //if it is a digit
        if(token.matches("[0-9]+")){
            String arr2[] = {token,"integerConstant"};
            arr1 = arr2;
        }
        //if it is a symbol
        else if(symbols.contains(token)){
            if(token.equals("<")){
                String arr2[] = {"&lt;","symbol"};
                arr1 = arr2;
            }
            else if(token.equals(">")){
                String arr2[] = {"&gt;","symbol"};
                arr1 = arr2;
            }
            else if(token.equals("&")){
                String arr2[] = {"&amp;","symbol"};
                arr1 = arr2;
            }
            else{
                String arr2[] = {token,"symbol"};
                arr1 = arr2;
            }
            
        }
        //if it is a keyword 
        else if(keyword.contains(token)){
            String arr2[] = {token,"keyword"};
            arr1 = arr2;
        }
        //if it is an identifier
        else if(token.matches("[\\w_]+")){
            String arr2[] = {token,"identifier"};
            arr1 = arr2;
        }   
        //if it is a string 
        else{
            String arr2[] = {token,"stringConstant"};
            arr1 = arr2;
        }  
        return arr1;
    }   

    static ArrayList<String[]> toBeSeparated(String line){
        //to be sent for main library
        ArrayList<String> splitted = new ArrayList<>();
        String temp = "";
        boolean stringHasStarted = false;

        for(int i=0; i<line.length(); i++){
            char c = line.charAt(i);
            if(c=='\t'){
                continue;
            }
            //if string has started
            if(c=='"'){
                //if string is now ending
                if(stringHasStarted){
                    stringHasStarted = false;
                    splitted.add(temp);
                    temp="";
                    continue;
                }
                    stringHasStarted = true;
                    continue;

            }
            else if(stringHasStarted){
                temp+=Character.toString(c);
                continue;
            }
            //if it sees a symbol it will split
            else if(symbols.contains(Character.toString(c))){
                //add whatever was left before
                if(temp!="") splitted.add(temp);
                splitted.add(Character.toString(c));
                temp="";  
            }
            //if ' ' then skip
            else if(c==' '){
                if(temp!="") splitted.add(temp);
                temp="";
            }
            else{
                if(temp != " "){
                    temp += Character.toString(c);
                }
                
            }
        }

        /*
         * Now every element is splitted next get its keyword from tokenParse
         * And send it in arraylist of arrays
         */
        ArrayList<String[]> alist = new ArrayList<>();
        for(String x:splitted){
            alist.add(tokenParse(x));
        }
        return alist;

    }

}