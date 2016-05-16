package ast;

/**
 * Created by master on 16.05.2016.
 */
public class os_check {
    public static String is_win(){
        if(System.getProperty("os.name").contains("Windows"))

            return "_";
        else
            return "";
    }
}
