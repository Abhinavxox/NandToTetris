package Assembler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Assembler {
	
	public static void main(String[] args) {
		nospaces();
		initial_table();
		labels();
		variables();
		removelabel();
		changeAinst();
		final_convert();
//		deleteCreatedFiles();
	}
	
	public static void nospaces(){
		//removes whitespaces, comments, inline comments and blank spaces anywhere found
		try {
			File f = new File("C:/Users/abhin/Desktop/AMRITA/S2/Assembler/INPUT.asm");
			Scanner file = new Scanner(f);
			PrintWriter pw = new PrintWriter("C:/Users/abhin/Desktop/AMRITA/S2/Assembler/_no_whitespace.asm");
			while(file.hasNext()) {
				String line = file.nextLine();
				//whitespaces
				if(!line.isEmpty()) {
					//comment
					if(line.charAt(0) != '/') {
						//inline comment
	                    String[] split_line = line.split("/");
	                    String tmp = split_line[0];
	                    StringBuilder new_line = new StringBuilder();
	                    for(int i = 0; i < tmp.length(); i++) {
	                    	//blank spaces or tab used 
	                        if(tmp.charAt(i) != ' ' && tmp.charAt(i) != '\t') {
	                            new_line.append(tmp.charAt(i));
	                        }
	                    }
	                    pw.write(new_line.toString());
						pw.write("\n");
					}

				}
			}
			file.close();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	//symbol table
	static HashMap<String, Integer> symbol_table = new HashMap<>();
	
	public static void initial_table() {
		//add pre-defined symbols to the table
        String [] pre_defined_symbol = new String[] {"R0=0","R1=1","R2=2","R3=3","R4=4","R5=5","R6=6","R7=7","R8=8",
        "R9=9","R10=10","R11=11","R12=12","R13=13","R14=14","R15=15","SCREEN=16384","KBD=24576","SP=0","LCL=1","ARG=2","THIS=3",
        "THAT=4"};

        //adding this array into the hashmap
        for (int i=0;i < pre_defined_symbol.length;i++) {
            String x = pre_defined_symbol[i].split("=")[0];
            int y =  Integer.parseInt(pre_defined_symbol[i].split("=")[1]);
            symbol_table.put(x, y);
        }

            
    }
	
	public static void labels() {
		//first adding labels to the table
		try {
			File f = new File("C:/Users/abhin/Desktop/AMRITA/S2/Assembler/_no_whitespace.asm");
			Scanner file = new Scanner(f);
			//since label value is its line number
			int line_number = 0;
			
			while(file.hasNext()) {
				String line = file.nextLine();
				if(line.charAt(0)=='(') {
					String sub = line.substring(1,line.length()-1);
					symbol_table.put(sub, line_number);
					line_number = line_number -1;
				}
				line_number++;
			}
			file.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public static void variables() {
		//adding variables to the symbol table starting from 16 value
		try {
			int variable_start = 16;
			File f = new File("C:/Users/abhin/Desktop/AMRITA/S2/Assembler/_no_whitespace.asm");
			Scanner file = new Scanner(f);
			int line_number = 0;
			
			while(file.hasNext()) {
				String line = file.nextLine();
				if(line.charAt(0)=='@') {
					String sub = line.substring(1);
					//if the sub is integer or not
					if(!Character.isDigit(sub.charAt(0))) {
						//if does not contain
						if(!symbol_table.containsKey(sub)) {
							symbol_table.put(sub, variable_start);
							variable_start++;
						}
					}
					
				}
				line_number++;
			}
			file.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public static void removelabel() { 
		//now labels have been added to the table
		//removing the labels from the file
		try {
			File f = new File("C:/Users/abhin/Desktop/AMRITA/S2/Assembler/_no_whitespace.asm");
			Scanner file = new Scanner(f);
			PrintWriter pw = new PrintWriter("C:/Users/abhin/Desktop/AMRITA/S2/Assembler/_no_whitespace1.asm");
			while(file.hasNext()) {
				String line = file.nextLine();
				if(line.charAt(0)!='(') {
					pw.write(line);
					pw.write("\n");
				}
			}
			file.close();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void changeAinst() {
		try {
			File f = new File("C:/Users/abhin/Desktop/AMRITA/S2/Assembler/_no_whitespace1.asm");
			Scanner file = new Scanner(f);
			PrintWriter pw = new PrintWriter("C:/Users/abhin/Desktop/AMRITA/S2/Assembler/_no_whitespace2.asm");
			while(file.hasNext()) {
				String line = file.nextLine();
				StringBuilder s = new StringBuilder();
				s.append("@");
				if(line.charAt(0)=='@') {
					String sub =  line.substring(1);
					if(!Character.isDigit(sub.charAt(0))) {
						int value = symbol_table.get(sub);
						s.append(value);
						pw.write(s.toString());
						pw.write("\n");
					}
					else {
						s.append(sub);
						pw.write(s.toString());
						pw.write("\n");
					}

				}else {
					pw.write(line);
					pw.write("\n");
				}
			}
			file.close();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void final_convert(){
		try {
			File f = new File("C:/Users/abhin/Desktop/AMRITA/S2/Assembler/_no_whitespace2.asm");
			Scanner file = new Scanner(f);
			PrintWriter pw = new PrintWriter("C:/Users/abhin/Desktop/AMRITA/S2/Assembler/OUTPUT.hack");
			int min_length = 16;
			
			while(file.hasNext()) {
				String line = file.nextLine();
				
				if(line.charAt(0) == '@') {
					String sub = line.substring(1);
					//error
					String binary_value = Integer.toBinaryString(Integer.parseInt(sub));
					if(binary_value.length()<min_length) {
						while(binary_value.length()!=min_length) {
							binary_value = "0"+binary_value;
						}
						pw.write(binary_value);
						pw.write("\n");
					}
				}
				//c instruction pass
				else {
					HashMap<String, String> d = new HashMap<String, String>();
					d.put("null", "000");
					d.put("M", "001");
					d.put("D", "010");
					d.put("MD", "011");
					d.put("A", "100");
					d.put("AM", "101");
					d.put("AD", "110");
					d.put("AMD", "111");
					
					HashMap<String, String> c = new HashMap<String, String>();
					c.put("null", "0000000");
					c.put("0", "0101010");
					c.put("1", "0111111");
					c.put("-1", "0111010");
					c.put("D", "0001100");
					c.put("A", "0110000");
					c.put("!D", "0001101");
					c.put("!A", "0110011");
					c.put("D+1", "0011111");
					c.put("A+1", "0110111");
					c.put("D-1", "0001110");
					c.put("A-1", "0110010");
					c.put("D+A", "0000010");
					c.put("D-A", "0010011");
					c.put("A-D", "0000111");
					c.put("D&A", "0000000");
					c.put("D|A", "1000000");
					c.put("M", "1110000");
					c.put("!M", "1110011");
					c.put("M+1", "1110111");
					c.put("M-1", "1110010");
					c.put("D+M", "1000010");
					c.put("D-M", "1010011");
					c.put("M-D", "1000111");
					c.put("D&M", "1000000");
					c.put("D|M", "1010101");
					c.put("-D", "0001111");
					c.put("-A", "0110011");
					c.put("-M", "1110011");
					
					HashMap<String, String> j = new HashMap<String, String>();
					j.put("null", "000");
					j.put("JGT", "001");
					j.put("JEQ", "010");
					j.put("JGE", "011");
					j.put("JLT", "100");
					j.put("JNE", "101");
					j.put("JLE", "110");
					j.put("JMP", "111");
					
					//filter the c instruction
					boolean flag1 = false, flag2 = false;
					for(int i=0; i<line.length(); i++) {
						if(line.charAt(i)=='=') {
							flag1=true;
						}
						if(line.charAt(i)==';') {
							flag2=true;
						}
					}
					
					//main string
					StringBuilder c_inst = new StringBuilder();
					c_inst.append("111");
					
					//if it only has=
					if(flag1==true && flag2==false) {
						String div1[] = line.split("=",2);
						c_inst.append(c.get(div1[1]));
						c_inst.append(d.get(div1[0]));
						c_inst.append(j.get("null"));
					}
					//if it only has;
					else if(flag2==true && flag1==false){
						String div1[] = line.split(";",2);
						c_inst.append(c.get(div1[0]));
						c_inst.append(d.get("null"));
						c_inst.append(j.get(div1[1]));

					}
					//has both
					else {
						String div1[] = line.split("=",2);
						String div2[] = div1[1].split(";", 2);
						c_inst.append(c.get(div2[0]));
						c_inst.append(d.get(div1[0]));
						c_inst.append(j.get(div2[1]));
					}
					
					
					pw.write(c_inst.toString());
					pw.write("\n");
				}

			}
			file.close();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void deleteCreatedFiles() {
		try {
			File a = new File("C:/Users/abhin/Desktop/AMRITA/S2/Assembler/_no_whitespace.asm");
			File b = new File("C:/Users/abhin/Desktop/AMRITA/S2/Assembler/_no_whitespace1.asm");
			a. delete();
			b.delete();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
