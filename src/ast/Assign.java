package ast;

public class Assign extends Stmt {
    public Identifier id;
    public Expr expr;

    public Assign(Identifier id, Expr expr) {
        this.id = id;
        this.expr = expr;
    }

    public void print(int indent) {
        Printer.print_with_indent("Assign", indent);
        id.print(indent + 1);
        expr.print(indent + 1);
    }

    public Assign optimize() {
        return new Assign(id.optimize(), expr.optimize());
    }

    public void codegen(CodegenState state) {
        expr.codegen(state);
        state.addInstructionToMain("pop eax");
        state.addInstructionToMain("mov dword [" + id.name + "], eax");
    }
}
