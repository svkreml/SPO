import java.util.ArrayList;

public class Compiler {

    public static void main(String[] args) {
        String str = "1000-(5-3)";

        Lexer lexer = new Lexer(str);
        ArrayList<Token> tokens = lexer.parseTokens();

        Parser parser = new Parser(tokens);
        ExprNode expr = parser.onlyExpression();

        CodeGen codeGen = new CodeGen(expr);
        codeGen.generateCode();
    }
}
