package ast;

public class CodegenState {
    private static String header =
            "section .text\n" +
            "global main\n" +
            "extern printf\n" +
            "extern scanf\n";
    StringBuilder dataSb = new StringBuilder();
    StringBuilder mainSb = new StringBuilder();
    int esp_offset = 0;

    public CodegenState() {
        dataSb.append(
                "section .data\n" +
                "format_in: db \"%d\", 0\n" +
                "format_out: db \"output = %d\", 10, 0\n");
        mainSb.append("main:\n");
    }

    public void addVariableToData(String var_name) {
        dataSb.append(var_name + ": dd 0\n");
    }

    public void addInstructionToMain(String line) {
        mainSb.append(line + "\n");
    }



    public String code() {
        return header + dataSb.toString() + mainSb.toString();
    }
}
