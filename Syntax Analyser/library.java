import java.util.ArrayList;
import java.util.Stack;

public class library {

    //stack implementation
    Stack paranthesis = new Stack<Character>();
    Stack pointer = new Stack<String>();
    

    //hashmap for statements

    

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

    //for symbols
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
}
