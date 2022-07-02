import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String filename = "Main.jack";
        CodeWriter cw = new CodeWriter();
        Parser p = new Parser();
        cw.writeCode(p.parse(cw.readFile(filename)), filename.split(".")[0]);
    }
    
}
