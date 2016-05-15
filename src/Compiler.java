import ast.CodegenState;
import ast.Program;

import java.util.ArrayList;

public class Compiler {

    public static void main(String[] args) {
        String str = "let a = 0; scan a; print a; assign a = a + 10; print -a + 2; print a*a*(a+1);";

        Lexer lexer = new Lexer(str);
        ArrayList<Token> tokens = lexer.parseTokens();

        Parser parser = new Parser(tokens);
        Program program = parser.program();
        program.print(0);

        Program opt_program = program.optimize();
        opt_program.print(0);

        CodegenState state = new CodegenState();
        opt_program.codegen(state);
        System.out.println(state.code());
    }
}
