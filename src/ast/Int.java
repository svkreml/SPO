package ast;

public class Int extends Expr {
    public int value;
    public int line;
    public int pos;
    public Int(int value,int line,int pos) {
        this.line=line;
        this.value = value;
        this.pos =pos;
    }



    public void print(int indent) {
        Printer.print_with_indent("Int", indent);
        Printer.print_with_indent("value=" + String.valueOf(value), indent + 1);
    }

    public Int optimize() {
        return this;
    }

    public void codegen(CodegenState state) {
        state.addInstructionToMain("push " + value);
    }
}
