package ast;

class Printer {
    static public void print_with_indent(String s, int indent) {
        for (int i = 0; i < indent; ++i) {
            System.out.print("|   ");
        }
        System.out.println(s);
    }
}
