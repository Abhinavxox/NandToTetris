import java.util.ArrayList;

public class Parser extends Library{

    static ArrayList<String[]> forCoderWriter = new ArrayList<>();

    public static ArrayList<String[]> parse(ArrayList<String> alist){
        for (String line : alist) {
            String arr[] = line.split(" ");
            for(String x : arr){
                ArrayList<String[]> alist2 = tokenParse(x);
                for(String y[]: alist2){
                    forCoderWriter.add(y);
                }
            }   
        }
        return forCoderWriter;
    }
    
}
