package ast;

public class Let extends Stmt {
    public Identifier id;
    public Expr expr;

    public Let(Identifier id, Expr expr) {
        this.id = id;
        this.expr = expr;
    }

    public void print(int indent) {
        Printer.print_with_indent("Let", indent);
        id.print(indent + 1);
        expr.print(indent + 1);
    }

    public Let optimize() {
        return new Let(id.optimize(), expr.optimize());
    }

    public void codegen(CodegenState state) {
        state.addVariableToData(id.name);
        expr.codegen(state);
        state.addInstructionToMain("pop eax");
        state.addInstructionToMain("mov dword [" + id.name + "], eax");
    }
}
