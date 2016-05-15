import java.util.ArrayList;

public class Parser {

    private ArrayList<Token> tokens;
    private int pos = 0;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    // true, ���� ������ ������ ���
    private boolean end() {
        return pos >= tokens.size();
    }

    // ������� �������
    private Token current() {
        if (end()) {
            return new Token();
        } else {
            return tokens.get(pos);
        }
    }

    // ������� � ��������� �������
    private void next() {
        if (!end()) {
            pos++;
        }
    }

    // true, ���� ������� ������� - �����
    private boolean isNumber() {
        return current().type == TokenType.NUMBER;
    }

    // true, ���� ������� ������� - ������� ������
    private boolean isSymbol(char c) {
        return current().type == TokenType.SYMBOL && current().c == c;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // �������������� ������
    /////////////////////////////////////////////////////////////////////////////////////////

    private void error(String message) {
        System.out.println(message);
        System.exit(1);
    }

    // todo: �������� ��������� � �������

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
