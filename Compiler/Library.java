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
            "~",
            """
                \
                """
    ));

    public static ArrayList<String[]> tokenParse(String token){

        ArrayList<String[]> alist = new ArrayList<>();

        if(token.equals(" ")){
            return null;
        }

        //if it is a digit
        if(token.matches("[0-9]+")){
            String arr[] = {token,"integerConstant"};
            alist.add(arr);
        }
        //if it is a symbol
        else if(symbols.contains(token)){
            String arr[] = {token,"symbol"};
            alist.add(arr);
        }
        //if it is a string
        else if(token.matches("\"(\\.|[^\"])*\"")){
            String temp = token.substring(1, token.length()-1);
            String arr[] = {token,temp};
            alist.add(arr);
        }
        //if it is a keyword 
        else if(keyword.contains(token)){
            String arr[] = {token,"keyword"};
            alist.add(arr);
        }
        //if it is an identifier
        else if(token.matches("[\\w_]+")){
            String arr[] = {token,"identifier"};
            alist.add(arr);
        }
        //if it has to be separated first
        else{
            toBeSeparated(token);
        }
        return alist;

    }   

    static ArrayList<String[]> toBeSeparated(String token){
        ArrayList<String> splitted = new ArrayList<>();
        String temp = "";
        for(int i = 0; i<token.length(); i++){
            char c = token.charAt(i);
            //if it finds a symbol split and next
            if(symbols.contains(Character.toString(c))){
                splitted.add(temp);
               splitted.add(Character.toString(c));
               temp = "";
            }
            //else
            temp += Character.toString(c);
        }

        ArrayList<String[]> alist = new ArrayList<>();
        for(String x:splitted){
            if(tokenParse(x)!=null){
                // String arr[] = tokenParse(x).get(0);
                String[] arr = tokenParse(x).get(0);
                alist.add(arr);
            }
            
        }
        return alist;
    }

}