package ast;

public class Identifier extends Expr {
    public String name;

    public Identifier(String name) {
        this.name = name;
    }

    public void print(int indent) {
        Printer.print_with_indent("Identifier", indent);
        Printer.print_with_indent("name=" + name, indent + 1);
    }
    public String name_print(){
        return this.name;
    }

    public Identifier optimize() {
        return this;
    }

    public void codegen(CodegenState state) {
        state.addInstructionToMain("push dword [" + name + "]");
    }
}
