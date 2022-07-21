package Tokenizer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String f = "./JackFiles/SquareExpressionless.jack";
        String arr[] = f.split("/");
        String filename = arr[arr.length-1].split("\\.")[0];
        CodeWriter.writeCode(Parser.parse(Parser.readFile(f)), filename);
    }
    
}
