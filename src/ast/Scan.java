package ast;

public class Scan extends Stmt {
    public Identifier id;

    public Scan(Identifier id) {
        this.id = id;
    }

    public void print(int indent) {
        Printer.print_with_indent("ScanStmt", indent);
        id.print(indent + 1);
    }

    public Scan optimize() {
        return new Scan(id.optimize());
    }

    public void codegen(CodegenState state) {
        state.addInstructionToMain("push " + id.name);
        state.addInstructionToMain("push format_in");
        state.addInstructionToMain("call scanf");
        state.addInstructionToMain("add esp, 8");
    }
}
