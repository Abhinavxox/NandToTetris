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
            String arr2[] = {token,"symbol"};
            arr1 = arr2;
        }
        //if it is a string
        else if(token.matches("\"(\\.|[^\"])*\"")){
            String temp = token.substring(1, token.length()-1);
            String arr2[] = {token,temp};
            arr1 = arr2;
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
        return arr1;
    }   

    static ArrayList<String[]> toBeSeparated(String line){
        System.out.println(line);
        //to be sent for main library
        ArrayList<String> splitted = new ArrayList<>();
        String temp = "";
        boolean stringHasStarted = false;

        for(int i=0; i<line.length(); i++){
            char c = line.charAt(i);
            //if string has started
            if(c=='"'){
                //if string is now ending
                if(stringHasStarted){
                    stringHasStarted = false;
                }
                else{
                    stringHasStarted = true;
                    splitted.add(temp);
                    temp+=Character.toString(c);
                    continue;
                }
            }
            else if(stringHasStarted){
                temp+=Character.toString(c);
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


        // ArrayList<String> splitted = new ArrayList<>();
        // String temp = "";
        // boolean flag = false;
        // for(int i = 0; i<token.length(); i++){
        //     char c = token.charAt(i);

        //     if(c=='"'){
        //         if(flag){
        //             flag =false;
        //             splitted.add(temp);
        //             temp = "";
        //             continue;
        //         }
        //         else{
        //             flag = true;
        //             if(temp!="") splitted.add(temp);
        //             temp += Character.toString(c);
        //             continue;
        //         }
        //     }
        //     else if(flag){
        //         temp += Character.toString(c);
        //         continue;
        //     }
        //     //if it finds a symbol split and next
        //     else if(symbols.contains(Character.toString(c))){
        //         if(temp!="") splitted.add(temp);
        //        splitted.add(Character.toString(c));
        //        temp = "";
        //        continue;
        //     }else if(c==' '){
        //         if(temp!="") splitted.add(temp);
        //         temp = "";
        //         continue;
        //     }
        //     //else
        //         if(temp!="")temp += Character.toString(c);
        // }

        // ArrayList<String[]> alist = new ArrayList<>();
        // for(String x:splitted){
        //         // String arr[] = tokenParse(x).get(0);
        //         String[] arr = tokenParse(x).get(0);
        //         alist.add(arr);
            
        // }
        // return alist;
    }

}