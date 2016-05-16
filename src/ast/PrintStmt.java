package ast;

public class PrintStmt extends Stmt {
    public Expr expr;

    public PrintStmt(Expr expr) {
        this.expr = expr;
    }

    public void print(int indent) {
        Printer.print_with_indent("PrintStmt", indent);
        expr.print(indent + 1);
    }

    public PrintStmt optimize() {
        return new PrintStmt(expr.optimize());
    }

    public void codegen(CodegenState state) {
        expr.codegen(state);
        state.addVariableToData("format_out_"+expr.name_print() + " = %d\", 10, 0",0);
        state.addInstructionToMain("push format_out_"+expr.name_print());
        state.addInstructionToMain("call "+os_check.is_win()+"printf");
        state.addInstructionToMain("add esp, 8");
    }
}
