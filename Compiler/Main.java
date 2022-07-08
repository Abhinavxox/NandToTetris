import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        String filename = "Main.jack";
        CodeWriter.writeCode(Parser.parse(Parser.readFile(filename)), "Main");
    }
    
}
