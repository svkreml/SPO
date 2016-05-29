package ast;

public class CodegenState {
    private static String header =
            "section .text\r\n" +
            "global "+os_check.is_win()+"main\r\n" +
            "extern "+os_check.is_win()+"printf\r\n" +
            "extern "+os_check.is_win()+"scanf\r\n";
    StringBuilder dataSb = new StringBuilder();
    StringBuilder mainSb = new StringBuilder();
    int esp_offset = 0;

    public CodegenState() {
        dataSb.append(
                "section .data\r\n" +
                "format_in: db \"%d\", 0\r\n" +
                "format_out: db \"output = %d\", 10, 0\r\n");
        mainSb.append(os_check.is_win()+"main:\r\n");
    }

    public void addVariableToData(String var_name) {
        dataSb.append(var_name + ": dd 0\r\n");
    }

    public void addInstructionToMain(String line) {
        mainSb.append(line + "\r\n");
    }



    public String code() {
        return header + dataSb.toString() + mainSb.toString();
    }
}
