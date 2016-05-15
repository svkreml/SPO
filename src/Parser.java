import java.util.ArrayList;

public class Parser {

    private ArrayList<Token> tokens;
    private int pos = 0;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    // true, если больше лексем нет
    private boolean end() {
        return pos >= tokens.size();
    }

    // “екуща€ лексема
    private Token current() {
        if (end()) {
            return new Token();
        } else {
            return tokens.get(pos);
        }
    }

    // ѕереход к следующей лексеме
    private void next() {
        if (!end()) {
            pos++;
        }
    }

    // true, если текуща€ лексема - число
    private boolean isNumber() {
        return current().type == TokenType.NUMBER;
    }

    // true, если текуща€ лексема - искомый символ
    private boolean isSymbol(char c) {
        return current().type == TokenType.SYMBOL && current().c == c;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // √рамматический разбор
    /////////////////////////////////////////////////////////////////////////////////////////

    private void error(String message) {
        System.out.println(message);
        System.exit(1);
    }

    // todo: добавить умножение и деление

    private ExprNode sum() {
        if (isNumber()) {
            int n = current().number;
            next();
            return new ExprNode(n);
        } else if (isSymbol('(')) {
            next();
            ExprNode e = expression();
            if (!isSymbol(')')) {
                error("Expected ) but " + current() + " found");
            }
            next();
            return e;
        } else {
            error("Unexpected token " + current());
            return null;
        }
    }

    public ExprNode expression() {
        ExprNode left = sum();
        while (!end()) {
            if (isSymbol('+') || isSymbol('-')) {
                char c = current().c;
                next();
                ExprNode right = sum();
                left = new ExprNode(left, c, right);
            } else {
                break;
            }
        }
        return left;
    }

    public ExprNode onlyExpression() {
        ExprNode expr = expression();
        if (!end()) {
            error("Extra token " + current());
        }
        return expr;
    }
}
