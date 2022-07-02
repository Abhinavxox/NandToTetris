import java.util.ArrayList;

public class Parser extends Library{

    static ArrayList<String[]> forCoderWriter = new ArrayList<>();

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
