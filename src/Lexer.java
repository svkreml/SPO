import java.util.ArrayList;

public class Lexer {

    private String str;
    private int spos = 0;

    public Lexer(String str) {
        this.str = str;
    }

    private void error(String message) {
        System.out.println(message);
        System.exit(1);
    }

    private void skipSpaces() {
        while (spos < str.length()) {
            char ch = str.charAt(spos);
            if (ch > ' ')
                break;
            spos++;
        }
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public ArrayList<Token> parseTokens() {
        ArrayList<Token> tokens = new ArrayList<Token>();
        while (true) {
            skipSpaces();
            if (spos >= str.length())
                break;
            char ch = str.charAt(spos);
            if (isDigit(ch)) {
                int p0 = spos;
                // todo: нужно пропустить все цифры, чтобы указатель spos оказался после последней цифры
                String numStr = str.substring(p0, spos);
                int value = Integer.parseInt(numStr);
                tokens.add(new Token(value));
            } else if ("+-*/()".indexOf(ch) >= 0) {
                tokens.add(new Token(ch));
                spos++;
            } else {
                error("Unexpected character '" + ch + "'");
            }
        }
        return tokens;
    }
}
