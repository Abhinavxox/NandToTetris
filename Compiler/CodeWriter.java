import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CodeWriter extends Parser{

    public static ArrayList<String> readFile(String filename) throws IOException{
        ArrayList<String> fileData = new ArrayList<String>();
        String[] arr;
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        // Reading everyline
        String line;
        while ((line = br.readLine()) != null) {
            // Ignores empty lines
            if (!(line.isEmpty())) {
                // Ignores comments
                int i = 0;
                while (line.charAt(i) == ' ') {
                    i++;
                }
                if (line.charAt(i) != '/') {
                    arr = line.split("//");
                    fileData.add(arr[0]);
                }
            }
        }

        br.close();
        fr.close();
        return fileData;
    }

    public void writeCode(ArrayList<String[]> alist, String filename) throws IOException{
        //write to a file
        File file = new File(filename+".xml");
		FileWriter fr = new FileWriter(file, true);
		BufferedWriter br = new BufferedWriter(fr);
		br.write("<tokens>\n");
        for(String[] x: alist){
            //if it is null
            if(x[0]==null || x[1]==null){
                continue;
            }
            br.write("<"+x[1]+"> "+x[0]+" <"+x[1]+">\n");
        }
		br.write("</tokens>\n");

		br.close();
		fr.close();
    }
    
}
