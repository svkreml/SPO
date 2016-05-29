public class Token {
    public TokenType type;
    public int number;
    public char c;
    public String id;
    public int line;
    public int pos;
    public Token() {
        this.type = TokenType.END;
    }

    public Token(int number,int line, int pos) {
        this.type = TokenType.NUMBER;
        this.number = number;
        this.line=line;
        this.pos=pos;
    }

    public Token(char c,int line, int pos) {
        this.type = TokenType.SYMBOL;
        this.c = c;
        this.line=line;
        this.pos=pos;
    }

    public Token(String s,int line, int pos) {
        this.pos=pos;
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
                this.pos=pos-s.length();
        }
        this.line=line;

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
