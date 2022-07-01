import java.util.ArrayList;
import java.util.Arrays;

class Library{

    public static String token(String token){

        //for keyword
        ArrayList<String> keyword = new ArrayList<>(Arrays.asList(
        "class",    
        "constructor",
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

        //if it is a digit
        if(token.matches("[0-9]+")){
            return "integerConstant"; 
        }
        //if it is a symbol
        else if(token.matches("[\\&\\*\\+\\(\\)\\.\\/\\,\\-\\]\\;\\~\\}\\|\\{\\>\\=\\[\\<]")){
            return "symbol"; 
        }
        //if it is a string
        else if(token.matches("\"[^\"\n]*\"")){
            return "stringConstant";
        }
        //if it is a keyword 
        else if(keyword.contains(token)){
            return "keyword";
        }
        //if it is an identifier
        else if(token.matches("[\\w_]+")){
            return "identifier";
        }
        return null;
    }   

}