import ast.CodegenState;
import ast.Program;

import java.util.ArrayList;

public class Compiler {
    public static void main(String[] args){

        FilesTXT file = new FilesTXT();
        //file.Create();
        String str = file.readFileAsString("input.txt");

        System.out.println("\n---Input---\n");
        System.out.println(str);


        Lexer lexer = new Lexer(str);
        ArrayList<Token> tokens = lexer.parseTokens();
        Parser parser = new Parser(tokens);
        Program program = parser.program();
        System.out.println("\n---Tree---\n");
        program.print(0);
        Program opt_program = program.optimize();
        System.out.println("\n---Opt-Tree---\n");
        opt_program.print(0);
        CodegenState state = new CodegenState();
        System.out.println("\n---Code---\n");
        opt_program.codegen(state);
        System.out.println(state.code());
        file.writeFileAsString(state.code());
    }
}
