package ast;

public abstract class Stmt extends Node {
    abstract public Stmt optimize();
}
