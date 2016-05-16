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
            if (ch != ' ' && ch != '\n' && ch != '\t')
                break;
            spos++;
        }
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private boolean isLetter(char ch) { return ch >= 'a' && ch <= 'z'; }

    public ArrayList<Token> parseTokens() {
        ArrayList<Token> tokens = new ArrayList<Token>();
        //ArrayList<String> values = new ArrayList<String>();
        while (true) {
            skipSpaces();
            if (spos >= str.length())
                break;
            char ch = str.charAt(spos);
            if (isDigit(ch)) {
                int x0 = spos;
                while (spos < str.length() && isDigit(str.charAt(spos))) {
                    spos++;
                }
                tokens.add(new Token(Integer.parseInt(str.substring(x0,spos))));

            } else if (isLetter(ch)) {
                int x0 = spos;
                while (spos < str.length() && (isLetter(ch) || isDigit(ch))) {
                    ch = str.charAt(++spos);
                }
                tokens.add(new Token(str.substring(x0,spos)));
                //values.add(str.substring(x0,spos));
            } else if ("+-*/()=;".indexOf(ch) >= 0) {
                tokens.add(new Token(ch));
                spos++;
            } else {
                error("Unexpected character '" + ch + "'");
            }
        }
        return tokens;
    }
}
