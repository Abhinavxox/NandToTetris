package Parser;
import java.io.FileWriter;
import java.io.IOException;

public class CompilationEngine extends Analyser {
    static FileWriter fw;

    public static void writer(String fileName) throws IOException {
        String filename = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.lastIndexOf("T"));
        fw = new FileWriter("./Output/" + filename + ".xml");
    }

    
    static int pointer = 1;
    public static void compileClass() throws IOException {
        String line = readLine(pointer);
        if(getTokenType(line) == "keyword" && getKeywordType(line) == "class"){
            fw.append("<class>\n");
            fw.append("<keyword> class </keyword>\n");
            pointer++;
            line = readLine(pointer);
            fw.append(line+"\n");
            pointer++;
        }
        line = readLine(pointer);
        if(checkSymbolType(line, "{")){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected {");
        }
        compileClassVarDec();
        compileSubroutine();
        line = readLine(pointer);
        if(checkSymbolType(line, "}")){
            fw.append(line+"\n");
        }
        else{
            System.out.println("Error: Expected }");
        }
        fw.append("</class>\n");
        fw.close();
    }

    public static void compileClassVarDec() throws IOException{
        String line = readLine(pointer);
        if(getTokenType(line) == "symbol" && checkSymbolType(line, "}")){
            pointer--;
            return;
        }
        if(getTokenType(line) == "keyword"&& (getKeywordType(line) == "function" || getKeywordType(line) == "constructor" || getKeywordType(line) == "method")){
            return;
        }
        if(getTokenType(line) != "keyword"){
            System.out.println("Error: Expected keyword");
        }
        fw.append("<classVarDec>\n");

        if((getKeywordType(line) != "static") && (getKeywordType(line) != "field")){
            System.out.println("Error: Expected static or field");
            return;
        }
        fw.append(line+"\n");
        pointer++;

        compileType();
        while(true){
            line = readLine(pointer);
            if(getTokenType(line) != "identifier"){
                System.out.println("Error: Expected identifier");
            }
            fw.append(line+"\n");
            pointer++;
            line = readLine(pointer);
            if(checkSymbolType(line, ",")){
                fw.append(line+"\n");
                pointer++;
            }
            else if(checkSymbolType(line, ";")){
                fw.append(line+"\n");
                pointer++;
                break;
            }
            else{
                System.out.println("Error: Expected , or ;");
            }
        }
        fw.append("</classVarDec>\n");
        compileClassVarDec();
    }

    public static void compileType() throws IOException{
        String line = readLine(pointer);
        if(getTokenType(line) == "keyword" && (getKeywordType(line) == "int" || getKeywordType(line) == "char" || getKeywordType(line) == "boolean" )){
            fw.append(line+"\n");
            pointer++;
            return;
        }
        else if(getTokenType(line) == "identifier"){
            fw.append(line+"\n");
            pointer++;
            return;
        }
        else{
            System.out.println("Error: Expected int, char, boolean, or class");
            
        }
    }
    
    public static void compileSubroutine() throws IOException{
        String line = readLine(pointer);
        if(getTokenType(line) == "symbol" && checkSymbolType(line, "}")){
            pointer--;
            return;
        }
        if(getTokenType(line) == "keyword" && (getKeywordType(line) == "function" || getKeywordType(line) == "constructor" || getKeywordType(line) == "method")){
            fw.append("<subroutineDec>\n");
            fw.append(line+"\n");
            pointer++;
            line = readLine(pointer);
            if(getTokenType(line) == "keyword" && getKeywordType(line) == "void"){
                fw.append(line+"\n");
                pointer++;
            }
            else{
                compileType();
            }
            line = readLine(pointer);
            if(getTokenType(line) != "identifier"){
                System.out.println("Error: Expected identifier");
            }else{
                fw.append(line+"\n");
                pointer++;
            }
            line = readLine(pointer);
            if(checkSymbolType(line, "(")){
                fw.append(line+"\n");
                pointer++;
                fw.append("<parameterList>\n");
            }
            else{
                System.out.println("Error: Expected (");
            }
            compileParameterList();
            line = readLine(pointer);
            if(getTokenType(line) == "symbol" && checkSymbolType(line, ")")){
                fw.append("</parameterList>\n");
                fw.append(line+"\n");
                pointer++;
            }
            else{
                System.out.println("Error: Expected )");
            }
        
            compileSubroutineBody();
           
            fw.append("</subroutineDec>\n");
            
        }
        compileSubroutine();
    }

    public static void compileParameterList() throws IOException{
        String line = readLine(pointer);
        if(getTokenType(line) == "symbol" && checkSymbolType(line, ")")){
            return;
        }
        while(true){
            compileType();
            line = readLine(pointer);
            if(getTokenType(line) == "identifier"){
                fw.append(line+"\n");
                pointer++;
            }
            else{
                System.out.println("Error: Expected identifier");
            }
            line = readLine(pointer);
            if(checkSymbolType(line, ",")){
                fw.append(line+"\n");
                pointer++;
            }
            else if(checkSymbolType(line, ")")){
                break;
            }
            else{
                System.out.println("Error: Expected , or )");
            }
            
        }
        compileParameterList();
    }

    public static void compileSubroutineBody() throws IOException{
        String line = readLine(pointer);
        fw.append("<subroutineBody>\n");
        if(checkSymbolType(line, "{")){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected {");
        }
        compileVarDec();
        fw.append("<statements>\n");
         compileStatements();
        fw.append("</statements>\n");
        line = readLine(pointer);
        if(checkSymbolType(line, "}")){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected }");
        }
        fw.append("</subroutineBody>\n");
    }

    public static void compileVarDec() throws IOException{
        String line = readLine(pointer);
        if(getTokenType(line) != "keyword" || getKeywordType(line) != "var"){
            return;
        }
            fw.append("<varDec>\n");
            fw.append(line+"\n");
            pointer++;
            compileType();
            while(true){
                line = readLine(pointer);
                if(getTokenType(line) != "identifier"){
                    System.out.println("Error: Expected identifier");
                }
                fw.append(line+"\n");
                pointer++;
                line = readLine(pointer);
                if(checkSymbolType(line, ",")){
                    fw.append(line+"\n");
                    pointer++;
                }
                else if(checkSymbolType(line, ";")){
                    fw.append(line+"\n");
                    pointer++;
                    break;
                }
                else{
                    System.out.println("Error: Expected , or ;");
                }
            }
            fw.append("</varDec>\n");
            compileVarDec();
        
    }


    public static void compileExpression() throws IOException{
        String line = readLine(pointer);
        if(getTokenType(line)!="symbol"){
            fw.append("<expression>\n");
            fw.append("<term>\n");
            compileTerm();
            fw.append("</term>\n");
            compileExpressionTail();
            fw.append("</expression>\n");
            //for ;
            line = readLine(pointer);
            if(getTokenType(line)=="symbol" && (getSymbolType(line)==";" || getSymbolType(line)=="]")){
                fw.append(line+"\n");
                pointer++;
            }
        }
    }

    public static void compileExpressionTail() throws IOException{
        String line = readLine(pointer);
        if(getTokenType(line)=="symbol" && getSymbolType(line)==";"){
            return;
        }
        if(getTokenType(line)=="symbol" && getSymbolType(line)!=")" && getSymbolType(line)!="]"){
            fw.append(line+"\n");
            pointer++;
            //check if op term is there
            line = readLine(pointer);
            if(getSymbolType(line)!=";"){
                fw.append("<term>\n");
                compileTerm();
                fw.append("</term>\n");
                compileExpressionTail();
            }
            else{
                pointer--;
            }
        }

        }

    public static void compileTerm() throws IOException{
        String line = readLine(pointer);
        if(getTokenType(line)=="integerConstant"){
            fw.append(line+"\n");
            pointer++;
            compileTerm();
        }
        else if(getTokenType(line)=="stringConstant"){
            fw.append(line+"\n");
            pointer++;
            compileTerm();
        }
        else if(getTokenType(line)=="keyword" && (getKeywordType(line)=="true" || getKeywordType(line)=="false" || getKeywordType(line)=="null" || getKeywordType(line)=="this")){
            fw.append(line+"\n");
            pointer++;
        }
        else if(getTokenType(line)=="identifier"){
            fw.append(line+"\n");
            pointer++;
            compileTerm();
        }

        else if(getTokenType(line) == "symbol" && getSymbolType(line)=="["){
            fw.append(line+"\n");
            pointer++;
            compileExpression();
        }
        //for expressionList like (expressionlist)
        else if(getTokenType(line) == "symbol" && getSymbolType(line) == "("){
            //for (
            fw.append(line+"\n");
            pointer++;
            //for content of expressionList
            compileExpressionList();
            line = readLine(pointer);
            //for )
            fw.append(line+"\n");
            pointer++;
        }
        //to complete the whole term
        if(getTokenType(line) == "symbol" && getSymbolType(line) == "."){
            fw.append(line+"\n");
            pointer++;
            compileTerm();
        }
        return;
    }

    public static void compileExpressionList() throws IOException{
        fw.append("<expressionList>\n");
        compileExpression();
        compileExpressionListTail();
        fw.append("</expressionList>\n");
    }

    public static void compileExpressionListTail() throws IOException{
        String line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == ","){
            fw.append(line+"\n");
            pointer++;
            compileExpression();
            compileExpressionListTail();
        }
        else{
            return;
        }
    }

    public static void compileLet() throws IOException{
        fw.append("<letStatement>\n");
        String line = readLine(pointer);
        if(getTokenType(line) == "keyword" && getKeywordType(line) == "let"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected let");
        }
        line = readLine(pointer);
        if(getTokenType(line) == "identifier"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected identifier");
        }
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == "["){
            fw.append(line+"\n");
            pointer++;
            compileExpression();
        }
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == "="){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected =");
        }   
        compileExpression();
        fw.append("</letStatement>\n");
    }

    public static void compileStatements() throws IOException{
        
        String line = readLine(pointer);
        if(getTokenType(line) == "keyword" && getKeywordType(line) == "let"){
            compileLet();
        }
        else if(getTokenType(line) == "keyword" && getKeywordType(line) == "if"){
            compileIf();
        }
        else if(getTokenType(line) == "keyword" && getKeywordType(line) == "while"){
            compileWhile();
        }
        else if(getTokenType(line) == "keyword" && getKeywordType(line) == "do"){
            compileDo();
        }
        else if(getTokenType(line) == "keyword" && getKeywordType(line) == "return"){
            compileReturn();
        }
        else{
            return;
        }
        compileStatements();
    }

    public static void compileIf() throws IOException{
        fw.append("<ifStatement>\n");
        String line = readLine(pointer);
        if(getTokenType(line) == "keyword" && getKeywordType(line) == "if"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected if");
            return;
        }
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == "("){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected (");
        }
        compileExpression();
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == ")"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected )");
        }
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == "{"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected {");
        }
        fw.append("<statements>\n");
        compileStatements();
        fw.append("</statements>\n");
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == "}"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected }");
        }
        line = readLine(pointer);
        if(getTokenType(line) == "keyword" && getKeywordType(line) == "else"){
            fw.append(line+"\n");
            pointer++;
            line = readLine(pointer);
            if(getTokenType(line) == "symbol" && getSymbolType(line) == "{"){
                fw.append(line+"\n");
                pointer++;
            }
            else{
                System.out.println("Error: Expected {");
            }
            compileStatements();
            line = readLine(pointer);
            if(getTokenType(line) == "symbol" && getSymbolType(line) == "}"){
                fw.append(line+"\n");
                pointer++;
            }
            else{
                System.out.println("Error: Expected }");
            }
        }
        fw.append("</ifStatement>\n");
    }

    public static void compileWhile() throws IOException{
        fw.append("<whileStatement>\n");
        String line = readLine(pointer);
        if(getTokenType(line) == "keyword" && getKeywordType(line) == "while"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected while");
            return;
        }
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == "("){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected (");
        }
        compileExpression();
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == ")"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected )");
        }
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == "{"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected {");
        }
        fw.append("<statements>\n");
        compileStatements();
        fw.append("</statements>\n");
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == "}"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected }");
        }
        fw.append("</whileStatement>\n");
    }

    public static void compileDo() throws IOException{
        fw.append("<doStatement>\n");
        String line = readLine(pointer);
        if(getTokenType(line) == "keyword" && getKeywordType(line) == "do"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected do");
            return;
        }
        line = readLine(pointer);
        if(getTokenType(line) == "identifier"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected identifier");
        }
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == "."){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected .");
        }
        line = readLine(pointer);
        if(getTokenType(line) == "identifier"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected identifier");
        }
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == "("){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected (");
        }
        compileExpressionList();
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == ")"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected )");
        }
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == ";"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected ;");
        }
        fw.append("</doStatement>\n");
    }

    public static void compileReturn() throws IOException{
        fw.append("<returnStatement>\n");
        String line = readLine(pointer);
        if(getTokenType(line) == "keyword" && getKeywordType(line) == "return"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            System.out.println("Error: Expected return");
            return;
        }
        line = readLine(pointer);
        if(getTokenType(line) == "symbol" && getSymbolType(line) == ";"){
            fw.append(line+"\n");
            pointer++;
        }
        else{
            compileExpression();
            line = readLine(pointer);
            if(getTokenType(line) == "symbol" && getSymbolType(line) == ";"){
                fw.append(line+"\n");
                pointer++;
            }
            else{
                System.out.println("Error: Expected ;");
            }
        }
        fw.append("</returnStatement>\n");
    }
}
    

