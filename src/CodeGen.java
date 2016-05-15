// todo: генерация ассемблерного кода
public class CodeGen {

    private ExprNode node;

    public CodeGen(ExprNode node) {
        this.node = node;
    }

    // Обход дерева в глубину и печать представления выражения в обратной польской записи
    // https://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D1%80%D0%B0%D1%82%D0%BD%D0%B0%D1%8F_%D0%BF%D0%BE%D0%BB%D1%8C%D1%81%D0%BA%D0%B0%D1%8F_%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D1%8C
    private void walkTree(ExprNode node) {
        if (node.isNumber) {
            System.out.printf("%d ", node.value);
        } else {
            walkTree(node.left);
            walkTree(node.right);
            System.out.printf("%c ", node.binop);
        }
    }

    public void generateCode() {
        walkTree(node);
        System.out.println();
    }
}
