package ast;

public class UnaryOp extends Expr {
    public Expr expr;
    public char kind;

    public UnaryOp(Expr expr, char kind) {
        this.expr = expr;
        this.kind = kind;
    }

    public void print(int indent) {
        Printer.print_with_indent("UnaryOp", indent);
        Printer.print_with_indent("kind=" + kind, indent + 1);
        expr.print(indent + 1);
    }

    public Expr optimize() {
        UnaryOp opt = new UnaryOp(expr.optimize(), kind);
        if (opt.expr instanceof Int) {
            Int expr = (Int) opt.expr;
            switch (opt.kind) {
                case '-': return new Int(0 - expr.value,expr.line);
                default: throw new IllegalStateException();
            }
        }
        return opt;
    }

    public void codegen(CodegenState state) {
        expr.codegen(state);
        state.addInstructionToMain("pop eax");
        switch (kind) {
            case '-': state.addInstructionToMain("neg eax"); break;
            default: throw new IllegalStateException();
        }
        state.addInstructionToMain("push eax");
    }
}
