import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CodeWriter{

    public static void writeCode(ArrayList<String[]> alist, String filename) throws IOException{
        //write to a file
        File file = new File(filename+".xml");
		FileWriter fr = new FileWriter(file, false);
		BufferedWriter br = new BufferedWriter(fr);
		br.write("<tokens>\n");
        for(String[] x: alist){
            // if it is null
            if(x[0]==null || x[1]==null){
                continue;
            }
            br.write("<"+x[1]+"> "+x[0]+" </"+x[1]+">\n");
        }
		br.write("</tokens>");

		br.close();
		fr.close();
    }
    
}
