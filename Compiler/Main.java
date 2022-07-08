import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        String f = "Square/Main.jack";
        String[] filename = f.split("\\.");
        CodeWriter.writeCode(Parser.parse(Parser.readFile(f)), filename[0]);
    }
    
}
