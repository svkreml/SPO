public class Token {

    public TokenType type;
    public int number;
    public char c;

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

    public String toString() {
        switch (type) {
        case NUMBER: return String.valueOf(number);
        case SYMBOL: return String.valueOf(c);
        }
        return "<EOF>";
    }
}
