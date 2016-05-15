package ast;

import java.util.ArrayList;

public class Program extends Node {
    public ArrayList<Stmt> stmts;

    public Program(ArrayList<Stmt> stmts) {
        this.stmts = stmts;
    }

    public void print(int indent) {
        Printer.print_with_indent("Program", indent);
        for (Stmt stmt : stmts) {
            stmt.print(indent + 1);
        }
    }

    public Program optimize() {
        ArrayList<Stmt> opt_stmts = new ArrayList<>();
        for (Stmt stmt : this.stmts) {
            opt_stmts.add(stmt.optimize());
        }
        return new Program(opt_stmts);
    }

    public void codegen(CodegenState state) {
        for (Stmt stmt : stmts) {
            stmt.codegen(state);
        }
        state.addInstructionToMain("ret");
    }
}
