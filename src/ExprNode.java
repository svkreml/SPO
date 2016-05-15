public class ExprNode {

    boolean isNumber;   // false, если это узел бинарной операции с двумя дочерними узлами; true, если листовой узел с числом (без дочерних узлов)
    ExprNode left;      // для isNumber=false - левый дочерний узел
    char binop;         // для isNumber=false - символ операции
    ExprNode right;     // для isNumber=false - правый дочерний узел
    int value;          // для isNumber=true - значение, хранящееся в узле

    public ExprNode(ExprNode left, char binop, ExprNode right) {
        this.isNumber = false;
        this.left = left;
        this.binop = binop;
        this.right = right;
    }

    public ExprNode(int value) {
        this.isNumber = true;
        this.value = value;
    }
}
