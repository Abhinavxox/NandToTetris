import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser extends Library{

    static ArrayList<String[]> forCoderWriter = new ArrayList<>();

    public static ArrayList<String> readFile(String filename) throws IOException{
        ArrayList<String> fileData = new ArrayList<String>();
        String[] arr;
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        // Reading everyline
        String line;
        while ((line = br.readLine()) != null) {
            // Ignores empty lines
            if (!line.isEmpty()) {
                // Ignores comments
                if(line.charAt(0)!='/'){
                    arr = line.split("//");
                    fileData.add(arr[0]);
                }
            }
        }

        br.close();
        fr.close();
        return fileData;
    }

    public static ArrayList<String[]> parse(ArrayList<String> alist){
        for (String line : alist) {
                ArrayList<String[]> alist2 = toBeSeparated(line);
                for(String y[]: alist2){
                    forCoderWriter.add(y);
                }
            }   

        return forCoderWriter;
    }
      
}
