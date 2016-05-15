public class ExprNode {

    boolean isNumber;   // false, ���� ��� ���� �������� �������� � ����� ��������� ������; true, ���� �������� ���� � ������ (��� �������� �����)
    ExprNode left;      // ��� isNumber=false - ����� �������� ����
    char binop;         // ��� isNumber=false - ������ ��������
    ExprNode right;     // ��� isNumber=false - ������ �������� ����
    int value;          // ��� isNumber=true - ��������, ���������� � ����

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
