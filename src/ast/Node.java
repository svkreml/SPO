package ast;

public abstract class Node {
    public abstract void print(int indent);
    public abstract void codegen(CodegenState state);
    public abstract Node optimize();
}
