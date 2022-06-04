import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Abhinav_AIE21088_Parser {
	
	public ArrayList<String[]> parser_method(String filename) throws IOException {
	        ArrayList<String[]> fileData = new ArrayList<String[]>();
			String[] arr;
			try {
				File f = new File(filename);
				Scanner file = new Scanner(f);
				while(file.hasNext()) {
					String line = file.nextLine();
					//whitespaces
					if(!line.isEmpty()) {
						//comment
						if(line.charAt(0) != '/') {
							arr = line.split(" ");
							fileData.add(arr);
						}
	
					}
				}
				file.close();
			} catch (Exception e) {
			}

	        return fileData;

	}

}
