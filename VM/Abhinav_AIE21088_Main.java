import java.io.IOException;

public class Abhinav_AIE21088_Main {
	
	public static void main(String[] args) throws IOException {
		String filename = "SimpleAdd.vm";
		Abhinav_AIE21088_Parser parser = new Abhinav_AIE21088_Parser();
		Abhinav_AIE21088_CodeWriter cw = new Abhinav_AIE21088_CodeWriter();
		cw .codeWriter_method(parser.parser_method(filename), filename);
	}

}


