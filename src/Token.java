public class Token {
    public TokenType type;
    public int number;
    public char c;
    public String id;

    public Token() {
        this.type = TokenType.END;
    }

    public Token(int number) {
        this.type = TokenType.NUMBER;
        this.number = number;
    }

    public Token(char c) {
        this.type = TokenType.SYMBOL;
        this.c = c;
    }

    public Token(String s) {
        switch (s) {
            case "let":
                this.type = TokenType.LET;
                break;
            case "print":
                this.type = TokenType.PRINT;
                break;
            case "scan":
                this.type = TokenType.SCAN;
                break;
            case "assign":
                this.type = TokenType.ASSIGN;
                break;
            default:
                this.type = TokenType.ID;
                this.id = s;
        }
    }

    public String toString() {
        switch (type) {
            case NUMBER: return String.valueOf(number);
            case SYMBOL: return String.valueOf(c);
            case ID: return id;
            case LET: return "LET";
            case PRINT: return "PRINT";
            case SCAN: return "SCAN";
            case ASSIGN: return "ASSIGN";
        }
        return "<EOF>";
    }
}
