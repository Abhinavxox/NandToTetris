import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class parser{

    public static void main(String[] args) throws IOException {
        readFile("Main.jack");
    }

    public static ArrayList<String> readFile(String filename) throws IOException{
        ArrayList<String> data = new ArrayList<String>();
        File f =  new File(filename);
        Scanner sc = new Scanner(f);
        String arr[];
        while(sc.hasNext()){
            String line = sc.next();
            //remove white spaces
            if(!line.isEmpty()){
                //remove comments
                if(line.charAt(0)!='/'){
                    arr = line.split("//");
                    data.add(arr[0]);
                }
            }
        }
        return data;
    }

}