package ast;

public class BinaryOp extends Expr {
    public Expr lhs;
    public Expr rhs;
    public char kind;

    public BinaryOp(Expr lhs, Expr rhs, char kind) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.kind = kind;
    }

    public void print(int indent) {
        Printer.print_with_indent("BinaryOp", indent);
        Printer.print_with_indent("kind=" + kind, indent + 1);
        lhs.print(indent + 1);
        rhs.print(indent + 1);
    }

    public Expr optimize() {
        BinaryOp opt = new BinaryOp(lhs.optimize(), rhs.optimize(), kind);
        if (opt.lhs instanceof Int && opt.rhs instanceof Int) {
            Int lhs = (Int) opt.lhs;
            Int rhs = (Int) opt.rhs;
            switch (opt.kind) {
                case '+': return new Int(lhs.value + rhs.value);
                case '-': return new Int(lhs.value - rhs.value);
                case '*': return new Int(lhs.value * rhs.value);
                case '/': return new Int(lhs.value / rhs.value);
                default: throw new IllegalStateException();
            }
        }
        return opt;
    }

    public void codegen(CodegenState state) {
        lhs.codegen(state);
        rhs.codegen(state);
        state.addInstructionToMain("pop ebx");
        state.addInstructionToMain("pop eax");
        switch (kind) {
            case '+': state.addInstructionToMain("add eax, ebx"); break;
            case '-': state.addInstructionToMain("sub eax, ebx"); break;
            case '*': state.addInstructionToMain("imul eax, ebx"); break;
            case '/': state.addInstructionToMain("idiv ebx"); break;
            default: throw new IllegalStateException();
        }
        state.addInstructionToMain("push eax");
    }
}