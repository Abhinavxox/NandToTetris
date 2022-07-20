package Parser;

import java.io.IOException;

public class Main{

    public static void main(String[] args) throws IOException {
        CompilationEngine.writer(passFilename());
        CompilationEngine.compileClass();
    }

    public static String passFilename(){
        String fileName = "./TokenizedFiles/SquareT.xml";
        return fileName;
    }


}
