package ast;

public abstract class Expr extends Node {
    abstract public Expr optimize();
}
