import ast.*;

import java.util.ArrayList;

public class Parser {

    private ArrayList<Token> tokens;
    private ArrayList<String> values = new ArrayList<String>();
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

    private Token consume() {
        Token current = current();
        next();
        return current;
    }

    // true, ���� ������� ������� - �����
    private boolean isNumber() {
        return current().type == TokenType.NUMBER;
    }

    // true, ���� ������� ������� - ������� ������
    private boolean isSymbol(char c) {
        return current().type == TokenType.SYMBOL && current().c == c;
    }

    private boolean isId() {
        return current().type == TokenType.ID;
    }

    private boolean isLet() { return current().type == TokenType.LET; }
    private boolean isPrint() { return current().type == TokenType.PRINT; }
    private boolean isScan() { return current().type == TokenType.SCAN; }
    private boolean isAssign() { return current().type == TokenType.ASSIGN; }
    private boolean isEof() { return current().type == TokenType.END; }


    private void error(String message) {
        System.out.print(message);
        System.out.println(" in line "+tokens.get(pos).line+", pos "+tokens.get(pos).pos+".");
        System.exit(1);
    }

    private void error_expected(TokenType expected) {
        error("Expected " + expected.toString() + " but " + current() + " found");
    }

    private void error_expected(char c) {
        error("Expected '" + c + "' but " +"'"+ current()+"'" + " found");
    }

    public Program program() {
        ArrayList<Stmt> stmts = new ArrayList<>();
        Stmt stmt = null;
        while ((stmt = stmt()) != null) {
            stmts.add(stmt);
        }
        return new Program(stmts);
    }

    private Stmt stmt() {
        Stmt stmt;
        if ((stmt = let()) != null ||
            (stmt = printStmt()) != null ||
            (stmt = scan()) != null ||
            (stmt = assign()) != null)
        {
            if (!isSymbol(';')) {
                error_expected(';');
            }
            consume();
            return stmt;
        }
        return null;
    }

    private Let let() {
        if (!isLet()) {
            return null;
        }
        consume();
        if (!isId()) {
            error_expected(TokenType.ID);
        }
        String id_name = consume().id;
        if (!isSymbol('=')) {
            error_expected('=');
        }
        consume();
        Expr expr = expression();
        // !!!
        if(values.contains(id_name)) {
            error("value '"+id_name+ "' already used in let");
        }
        values.add(id_name);
        return new Let(new Identifier(id_name), expr);
    }

    private PrintStmt printStmt() {
        if (!isPrint()) {
            return null;
        }
        consume();
        Expr expr = expression();
        return new PrintStmt(expr);
    }

    private Scan scan() {
        if (!isScan()) {
            return null;
        }
        consume();
        if (!isId()) {
            error_expected(TokenType.ID);
        }
        String id_name = consume().id;
        // !!!
        if(!values.contains(id_name)) {
            error("value '"+id_name+ "' not LETed yet in scan");
        }
        return new Scan(new Identifier(id_name));
    }

    private Assign assign() {
        boolean had_assign = false;
        if ((had_assign = isAssign())) {
            consume();
        }
        if (!isId()) {
            if (had_assign) {
                error_expected(TokenType.ID);
            } else {
                return null;
            }
        }
        String id_name = consume().id;
        if (!isSymbol('=')) {
            error_expected('=');
        }
        consume();
        Expr expr = expression();
        // !!!
        if(!values.contains(id_name)) {
            error("value '"+id_name+ "' not LETed yet in assign");
        }
        return new Assign(new Identifier(id_name), expr);
    }

    private Expr expression() {
        Expr left = muldiv();
        while (true) {
            if (isSymbol('+') || isSymbol('-')) {
                char c = consume().c;
                Expr right = muldiv();
                left = new BinaryOp(left, right, c);
            } else {
                break;
            }
        }
        return left;
    }

    private Expr muldiv() {
        Expr left = primary();
        while (true) {
            if (isSymbol('*') || isSymbol('/')) {
                char c = consume().c;
                Expr right = primary();
                left = new BinaryOp(left, right, c);
            } else {
                break;
            }
        }
        return left;
    }

    private Expr primary() {
        if (isSymbol('-')) {
            consume();
            Expr expr = primary();
            return new UnaryOp(expr, '-');
        }
        if (isSymbol('(')) {
            consume();
            Expr expr = expression();
            if (!isSymbol(')')) {
                error_expected(')');
            }
            consume();
            return expr;
        }
        if (isId()) {
            String id_name = consume().id;
            if(!values.contains(id_name)) {
                error("value '"+id_name+ "' not LETed yet in primary");
            }
            return new Identifier(id_name);
        }
        if (isNumber()) {
            int value = consume().number;
            return new Int(value,tokens.get(pos).line);
        }

        error("Expr exsepted");
        return null;
    }
}
