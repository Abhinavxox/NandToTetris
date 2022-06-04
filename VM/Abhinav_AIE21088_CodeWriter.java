
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Abhinav_AIE21088_CodeWriter {
	
	public void codeWriter_method(ArrayList<String[]> alist, String filename) throws IOException {
		//arraylist is given as an argument by parser
		//we need to loop through the list and write the asm for each component
        
        String sub = filename.substring(0, filename.indexOf("."));
		FileWriter fw =new FileWriter(sub+".asm");
		//obj to access the methods
		Abhinav_AIE21088_CodeWriter cw = new Abhinav_AIE21088_CodeWriter();
		for(String line[]: alist) {
			
			//check if it is logical
			if(line[0].equals("add")||line[0].equals("sub")) {
				fw.write(cw.add_sub_method(line));
			}
			//if is for segments
			else {
				switch (line[1]) {
				case "local","argument","this","that","temp": 
					fw.write(cw.local_arg_method(line));
					break;
				case "pointer":
					fw.write(cw.pointer_method(line));
					break;
				case "static":
					fw.write(cw.static_method(line, filename));
					break;
				case "constant":
					fw.write(cw.constant_method(line));
					break;
				}
			}	
		}
        fw.close();
	}
	
    //for local argument this that temp
    public String local_arg_method(String arr[]){

        StringBuilder sb = new StringBuilder();

        //for push
        //addr = segement(arr[1]) + i(arr[2])
        //*SP = *addr
        //SP++
        sb.append("@"+arr[2]+"\n");
        sb.append("D=A\n");

        if(arr[1].equals("local")){
            sb.append("@LCL\n"); 
        	sb.append("D=D+M\n");
        }
        else if(arr[1].equals("argument")){
            sb.append("@ARG\n");
        	sb.append("D=D+M\n");
        }
        else if(arr[1].equals("this")){
            sb.append("@THIS\n");
        	sb.append("D=D+M\n");
        }
        else if(arr[1].equals("that")){
            sb.append("@THAT\n");
        	sb.append("D=D+M\n");
        }
        else if(arr[1].equals("temp")){
            sb.append("@5\n");
        	sb.append("D=D+A\n");
        }
        if(arr[0].equals("push")){
            sb.append("A=D\n");
            sb.append("D=M\n");
            sb.append("@SP\n");
            sb.append("A=M\n");
            sb.append("M=D\n");
            sb.append("@SP\n");
            sb.append("M=M+1\n");

        }
        //for push
        //addr = segement(arr[1]) + i(arr[2])
        //SP++
        //*addr=*SP  
        else if(arr[0].equals("pop")){
            sb.append("@R13\n");
            sb.append("M=D\n");
            sb.append("@SP\n");
            sb.append("AM=M-1\n");
            sb.append("D=M\n");
            sb.append("@R13\n");
            sb.append("A=M\n");
            sb.append("M=D\n");
        }
        return sb.toString();

    }

    //for constant
    public String constant_method(String arr[]){
        StringBuilder sb = new StringBuilder();
        //*sp=i
        //sp++
        String i = arr[2];
        sb.append("@"+i+"\n");
        sb.append("D=A\n");
        sb.append("@SP\n");
        sb.append("A=M\n");
        sb.append("M=D\n");
        sb.append("@SP\n");
        sb.append("M=M+1\n");
        return sb.toString();
        
    }

    //for pointer
    public String pointer_method(String arr[]){
        StringBuilder sb = new StringBuilder();
        if(arr[0].equals("push")){
            //*SP = *THIS
            //SP++
            if(arr[2].equals("0")){
                sb.append("@THIS\n");
            }else{
                sb.append("@THAT\n");
            }
            sb.append("D=M\n");
            sb.append("@SP\n");
            sb.append("A=M\n");
            sb.append("M=D\n");
            sb.append("@SP\n");
            sb.append("M=M+1\n");
        }
        if(arr[0].equals("pop")){
            //SP--
            //*THAT= *SP
            sb.append("@SP\n");
            sb.append("AM=M-1\n");
            sb.append("D+M\n");
            if(arr[2].equals("0")){
                sb.append("@THIS\n");
            }else{
                sb.append("@THAT\n");
            }
            sb.append("M=D\n");
        }
        return sb.toString();
    }

    //for static variables
    public String static_method(String arr[],String filename){
        StringBuilder sb = new StringBuilder();
        if(arr[0].equals("push")){
            sb.append("@"+filename+"."+arr[2]+"\n");
            sb.append("D=M\n");
            sb.append("@SP\n");
            sb.append("A=M\n");
            sb.append("M=D\n");
            sb.append("@SP\n");
            sb.append("M=M+1\n");
        }
        else if(arr[0].equals("pop")){
            sb.append("@SP\n");
            sb.append("AM=M-1\n");
            sb.append("D=M\n");
            sb.append("@"+filename+"."+arr[2]+"\n");
            sb.append("M=D\n");
        }
        return sb.toString();
    }

    //for add and sub
    public String add_sub_method(String arr[]){

        StringBuilder sb = new StringBuilder();
        sb.append("@SP\n");
        sb.append("AM=M-1\n");
        sb.append("D=M\n");
        sb.append("@SP\n");
        sb.append("AM=M-1\n");
        if(arr[0].equals("add")){
            sb.append("M=M+D\n");
        }
        else{
            sb.append("M=M-D\n");
        }
        sb.append("@SP\n");
        sb.append("M=M+1\n");
        return sb.toString();
    }

}
