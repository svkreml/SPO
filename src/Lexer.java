import java.util.ArrayList;

public class Lexer {

    private String str;
    private int spos = 0;
    private int line =1;
    private int Prev_pos =0;
    private int pos;
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
            if(ch=='\r') {
                line++;
                Prev_pos=spos+2;// \r\n
            }
            if (ch != '\r'&&ch != ' ' && ch != '\n' && ch != '\t')
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
            pos=spos;
            skipSpaces();
            if (spos >= str.length())
                break;
            char ch = str.charAt(spos);
            if (isDigit(ch)) {
                int x0 = spos;
                while (spos < str.length() && isDigit(str.charAt(spos))) {
                    spos++;
                }
                tokens.add(new Token(Integer.parseInt(str.substring(x0,spos)), line, pos-Prev_pos));

            } else if (isLetter(ch)) {
                int x0 = spos;
                while (spos < str.length() && (isLetter(ch) || isDigit(ch))) {
                    ch = str.charAt(++spos);
                }
                tokens.add(new Token(str.substring(x0,spos), line, pos-Prev_pos));
            } else if ("+-*/()=;".indexOf(ch) >= 0) {
                tokens.add(new Token(ch, line, spos-Prev_pos));
                spos++;
            } else {
                error("Unexpected character '" + ch + "'"+"in line:"+line+", Pos:"+(pos-Prev_pos));
            }
        }
        return tokens;
    }
}
